package io.ssafy.rankservice.config.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
@Component
@Slf4j
@EnableScheduling
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job updateRankJob;
    private final ReentrantLock lock = new ReentrantLock();

//    @Scheduled(cron = "0 * * * * ?") // 매 분마다
    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        if (lock.tryLock()) {
            try{
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis()) // 시간 기반 파라미터 추가
                        .toJobParameters();
                JobExecution jobExecution = jobLauncher.run(updateRankJob, jobParameters);
                BatchStatus batchStatus = jobExecution.getStatus();
                if (batchStatus == BatchStatus.COMPLETED) {
                    // 작업이 성공적으로 완료되었을 때 실행할 로직을 추가하세요.
                    // 예: 로그 메시지, 이메일 알림 등
                    log.info("작업 성공적으로 완료");
                } else {
                    // 작업이 실패한 경우 처리할 로직을 추가하세요.
                    // 예: 오류 로그 기록, 알림 메시지 전송 등
                    log.warn("작업 실패");
                }
            }
            finally {
                lock.unlock();
            }
        }

    }

}
