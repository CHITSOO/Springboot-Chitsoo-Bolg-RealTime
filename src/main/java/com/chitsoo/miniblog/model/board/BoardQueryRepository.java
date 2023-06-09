package com.chitsoo.miniblog.model.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final EntityManager em;
    private final int SIZE = 8; // 상수는 대문자

//    public Page<Board> findAll(Pageable pageable){
    public Page<Board> findAll(int page){
        int startPosition = page * SIZE; // 첫번째는 0 두번째는 8

//        List<Board> boardListPS = em.createQuery("select b from Board b join fetch b.user")
        List<Board> boardListPS = em.createQuery("select b from Board b join fetch b.user order by b.id desc")
//                .setFirstResult(pageable.getPageNumber())
                .setFirstResult(startPosition) // 시작번호
//                .setMaxResults(pageable.getPageSize())
                .setMaxResults(SIZE) // 개수
                .getResultList();

        Long totalCount = em.createQuery("select count(b) from Board b", Long.class).getSingleResult();
//        return new PageImpl<>(boardListPS, pageable, boardListPS.size());
        return new PageImpl<>(boardListPS, PageRequest.of(page, SIZE), totalCount); // 마지막 인자는 총 개수
    }
}
