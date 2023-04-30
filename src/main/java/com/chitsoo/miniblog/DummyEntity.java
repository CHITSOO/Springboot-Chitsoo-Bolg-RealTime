package com.chitsoo.miniblog;

import com.chitsoo.miniblog.model.board.Board;
import com.chitsoo.miniblog.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DummyEntity {
    // protected 니깐 extends 안하면 아무나 못 씀
    protected User newUser(String username, BCryptPasswordEncoder passwordEncoder){
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .email(username + "@nate.com")
                .role("USER")
                .profile("person.png")
                .build();
    }

    protected Board newBoard(String title, User user){
        return Board.builder()
                .title(title)
                .content(title + "에 대한 내용입니다")
                .user(user)
                .thumbnail("/upload/person.png")
                .build();
    }
}
