package com.chitsoo.miniblog;

import com.chitsoo.miniblog.model.user.User;
import com.chitsoo.miniblog.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MiniblogApplication {

    @Profile("dev") // 이렇게 하면 dev 프로파일에서만 작동함
    // @SpringBootApplication이 어노테이션이 자동으로 빈으로 등록해주지만 @Bean을 명시해주면 좋음.
    // CommandLineRunner은 인터페이스 - 구현하면, 실행될 때 run() 메소드가 실행, 명령어 라인 인자(argument)들을 처리
    @Bean
    CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            User ssar = User.builder()
                    .username("ssar")
                    .password(passwordEncoder.encode("1234")) // 더미데이터를 만들 때도 인코드를 해서 넣어야 한다.
                    .email("ssar@nate.com")
                    .role("USER")
                    .profile("person.png")
                    .build();
            userRepository.save(ssar);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniblogApplication.class, args);
    }

}
