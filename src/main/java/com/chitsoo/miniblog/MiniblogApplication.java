package com.chitsoo.miniblog;

import com.chitsoo.miniblog.model.board.Board;
import com.chitsoo.miniblog.model.board.BoardRepository;
import com.chitsoo.miniblog.model.user.User;
import com.chitsoo.miniblog.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MiniblogApplication extends DummyEntity{

    @Profile("dev") // 이렇게 하면 dev 프로파일에서만 작동함
    // @SpringBootApplication이 어노테이션이 자동으로 빈으로 등록해주지만 @Bean을 명시해주면 좋음.
    // CommandLineRunner은 인터페이스 - 구현하면, 실행될 때 run() 메소드가 실행, 명령어 라인 인자(argument)들을 처리
    @Bean
    CommandLineRunner init(BoardRepository boardRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            User ssar = newUser("ssar", passwordEncoder);
            User cos = newUser("cos", passwordEncoder);
            userRepository.saveAll(Arrays.asList(ssar, cos));

            List<Board> boardList = new ArrayList<>();
            for(int i = 1; i < 11; i++){
                boardList.add(newBoard("제목" + i, ssar));
            }
            for(int i = 11; i < 21; i++){
                boardList.add(newBoard("제목" + i, cos));
            }
            boardRepository.saveAll(boardList);

//            User ssar = User.builder()
//                    .username("ssar")
//                    .password(passwordEncoder.encode("1234")) // 더미데이터를 만들 때도 인코드를 해서 넣어야 한다.
//                    .email("ssar@nate.com")
//                    .role("USER")
//                    .profile("person.png")
//                    .build();
//            User cos = User.builder()
//                    .username("cos")
//                    .password(passwordEncoder.encode("1234"))
//                    .email("cos@nate.com")
//                    .role("USER")
//                    .profile("person.png")
//                    .build();
//            userRepository.save(ssar);
//            userRepository.saveAll(Arrays.asList(ssar, cos));
//
//            Board b1 = Board.builder()
//                    .title("제목 1")
//                    .content("내용1")
//                    .user(ssar)
//                    .thumbnail("/upload/person.png")
//                    .build();
//            Board b2 = Board.builder()
//                    .title("제목 2")
//                    .content("내용2")
//                    .user(ssar)
//                    .thumbnail("/upload/person.png")
//                    .build();
//            boardRepository.saveAll(Arrays.asList(b1, b2));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniblogApplication.class, args);
    }

}
