package com.chitsoo.miniblog.model.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @EntityGraph(attributePaths = "user") // user를 바로 땡겨줄래?
//    Page<Board> findAll(Pageable pageable);
}
