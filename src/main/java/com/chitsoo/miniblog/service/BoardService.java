package com.chitsoo.miniblog.service;

import com.chitsoo.miniblog.dto.board.BoardRequest;
import com.chitsoo.miniblog.model.board.BoardRepository;
import com.chitsoo.miniblog.model.user.User;
import com.chitsoo.miniblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

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
}
