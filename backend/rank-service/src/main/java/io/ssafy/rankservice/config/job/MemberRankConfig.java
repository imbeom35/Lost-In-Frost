package io.ssafy.rankservice.config.job;

import io.ssafy.rankservice.entity.Member;
import io.ssafy.rankservice.enums.Role;
import io.ssafy.rankservice.rank.entity.Ranking;
import io.ssafy.rankservice.rank.repository.RankRepository;
import io.ssafy.rankservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MemberRankConfig {

    private final MemberRepository memberRepository;
    private final RankRepository rankRepository;
    private final ReentrantLock lock = new ReentrantLock(); // 락 생성

    @Bean
    public Job updateRankJob(JobRepository jobRepository, Step updateRankStep) {
        log.info("## 회원 랭크 업데이트 Job 생성");
        return new JobBuilder("updateRankJob", jobRepository)
                .start(updateRankStep)
                .build();
    }

    @Bean
    public Step updateRankStep(JobRepository jobRepository, Tasklet updateRankTasklet, PlatformTransactionManager transactionManager, TaskExecutor taskExecutor) {

        log.info("## Step 생성");
        return new StepBuilder("updateRankStep", jobRepository)
                .repository(jobRepository)
                .tasklet(updateRankTasklet, transactionManager)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean
    public Tasklet updateRankTasklet() {
        return (contribution, chunkContext) -> {
            if (lock.tryLock()) {
                try {
                    log.info("## Tasklet 실행");

                    List<Member> members = memberRepository.findByRoleIsNotOrderByLevelDescExperienceDesc(Role.ROLE_ADMIN);
                    List<Ranking> rankings = new ArrayList<>();

                    for (int i = 0; i < members.size(); i++) {
                        Member member = members.get(i);
                        if (member.getRole() == Role.ROLE_ADMIN) {
                            continue;
                        }
                        Optional<Ranking> optionalRanking = rankRepository.findById((long) i + 1);
                        Ranking ranking = optionalRanking.orElse(new Ranking());

                        // 개인별 랭크 업데이트
                        long datetime = System.currentTimeMillis();
                        Timestamp timestamp = new Timestamp(datetime);
                        // 신규 회원이 아닌경우
                        if (member.getRank() != 0 ){
                            if (member.getRank() < i + 1) {
                                member.updateRank(i + 1, timestamp, "DOWN");
                            } else if (member.getRank() > i + 1) {
                                member.updateRank(i + 1, timestamp, "UP");
                            } else {
                                member.updateRank(i + 1, timestamp, "SAME");
                            }
                        }
                        // 신규 회원인 경우
                        else {
                            member.updateRank(i + 1, timestamp, "NEW");
                        }
                        memberRepository.save(member);

                        ranking.updateRanking(member.getId(), member.getNickname(), member.getMyCostume().getCostumeImage(), member.getLevel(), member.getExperience(), member.getMessage(), member.getGamePlayCount());
//                        rankRepository.save(ranking);
                        rankings.add(ranking);

                    }

                    log.info("## Reader: {}", members.size());

                    rankRepository.saveAll(rankings);
                } finally {
                    lock.unlock();
                }
            }
            return RepeatStatus.FINISHED;
        };
    }


//    @Bean
//    public TaskExecutor taskExecutor() {
//        log.info("## TaskExecutor 등록");
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(1); // 적절한 스레드 풀 크기를 설정
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(25);
//        executor.setThreadNamePrefix("updateRank-");
//        executor.initialize();
//        return executor;
//    }

}
