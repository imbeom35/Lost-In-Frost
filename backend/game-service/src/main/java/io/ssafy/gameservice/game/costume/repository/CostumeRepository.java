package io.ssafy.gameservice.game.costume.repository;


import io.ssafy.gameservice.game.costume.entity.Costume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CostumeRepository extends JpaRepository<Costume, Long> {

    Optional<List<Costume>> findAllByIsDeleted (Boolean isDeleted);
    List<Costume> findAllByGrade (String grade);
}
