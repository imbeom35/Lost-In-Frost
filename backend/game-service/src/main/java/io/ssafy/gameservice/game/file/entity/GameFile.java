package io.ssafy.gameservice.game.file.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_game_file_type", columnList = "game_file_type"),
        @Index(name = "idx_game_file_use_state", columnList = "game_file_use_state"),
        @Index(name = "idx_game_file_hash", columnList = "game_file_hash")
})
public class GameFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_file_seq")
    @Comment("게임 파일 식별자")
    private Long seq;

    @Column(name = "game_file_version")
    @Comment("게임 파일 버전")
    private String version;

    @Column(name = "game_file_type")
    @Comment("게임 파일 타입 : launcher, exe")
    private String type;

    @Column(name = "game_file_name")
    @Comment("게임 파일 이름")
    private String name;

    @Column(name = "game_file_use_state")
    @Comment("게임 파일 사용 여부")
    private boolean useState;

    @Column(name = "game_file_extension")
    @Comment("게임 파일 확장자")
    private String extension;

    @Column(name = "game_file_hash", length = 512)
    @Comment("게임 파일 해시")
    private String hash;
}
