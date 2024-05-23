package io.ssafy.gameservice.game.file.repository;

import io.ssafy.gameservice.game.file.entity.GameFile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface GameFileRepository extends JpaRepository<GameFile, Long> {

    Optional<GameFile> findByTypeAndUseState (String type, Boolean useState);

    List<GameFile> findAllByHash (String hash);
}
