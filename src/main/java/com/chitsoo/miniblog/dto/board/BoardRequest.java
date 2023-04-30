package com.chitsoo.miniblog.dto.board;

import com.chitsoo.miniblog.model.board.Board;
import com.chitsoo.miniblog.model.user.User;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter @Setter
    public static class SaveInDTO { // 나중에 Validation 추가.
        private String title;
        private String content;

        // insert 하는 거라서 toEntity 만들어야 함.
        public Board toEntity(User user){ // 연관관계가 필요하니깐 User을 받는다
            return Board.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .thumbnail(null)
                    .build();
        }
    }
}
