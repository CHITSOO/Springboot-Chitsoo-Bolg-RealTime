package com.chitsoo.miniblog.service;

import com.chitsoo.miniblog.dto.board.BoardRequest;
import com.chitsoo.miniblog.model.board.Board;
import com.chitsoo.miniblog.model.board.BoardQueryRepository;
import com.chitsoo.miniblog.model.board.BoardRepository;
import com.chitsoo.miniblog.model.user.User;
import com.chitsoo.miniblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardQueryRepository boardQueryRepository; // join-fetch 했기 때문에 user를 미리 땡겨옴

    @Transactional
    public void 글쓰기(BoardRequest.SaveInDTO saveInDTO, Long userId){ // 유저 객체를 받지 않고 id만 받는다 - 실제 있는지 조회해봐야 하기 때문 - 세션 정보는 영속화 되지 않음
        try{
            // 1. 유저 존재 확인
            User userPS = userRepository.findById(userId).orElseThrow(
                    ()->new RuntimeException("유저를 찾을 수 없습니다")
            );

            // 2. 게시글 쓰기
            boardRepository.save(saveInDTO.toEntity(userPS)); // save할때는 영속화된 객체만 넣는다.
        }catch (Exception e){
            throw new RuntimeException("글쓰기 실패 : " + e.getMessage());
        }
    }

    @Transactional(readOnly = true) // 변경감지 하지마. 고립성(REPEATABLE READ) = 트랜잭션이 시작되고 나서 누군가에 의해 데이터가 변경되어도 무시함. 나는 변경 전의 데이터를 읽음
    public Page<Board> 글목록보기(Pageable pageable) { // Pageable은 인터페이스
        // CSR은 DTO로 변경해서 돌려줘야 함.

//        Page<Board> boardPGPS = boardRepository.findAll(pageable); // JPA가 page객체를 받으면 그렇게 맞게 뽑아줌 -> 그럼 리턴 타입이 Page.
//        return boardPGPS; // 여기까지는 영속화됨.

        // 1. 모든 전략은 Lazy : 이유는 필요할 때만 가져오려고
        // 2. 필요할 때는 직접 fetch join을 사용해라
        return boardQueryRepository.findAll(pageable);
    }
}
