package com.chitsoo.miniblog.config;

import com.chitsoo.miniblog.core.auth.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;

@Slf4j // Logger 객체를 생성하지 않아도 되므로 코드의 가독성이 향상
@Configuration// IoC 컨테이너에 등록하려면 컴포넌트 스캔이 되어야 함. 설정 정보를 제공하는 클래스에 붙임.
public class SecurityConfig {

    @Bean // IoC에 등록. 싱글톤 객체로 관리.
    BCryptPasswordEncoder passwordEncoder(){ // 회원가입할 때 사용
        return new BCryptPasswordEncoder();
    }

    // filter chain 만들기
    // - username, password, authentication filter 커스터마이징
    // - aware filter에게 알려줄 권한 주소설정
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. CSRF 해제
        http.csrf().disable();

        // 2. frame option 해제 (시큐리티 h2-console 접속 허용을 위해)
        http.headers().frameOptions().disable(); // X-Frame-Options 보안 기능을 비활성화 - H2 데이터베이스 콘솔을 사용하기 위해서는 <iframe> 태그를 사용해야 하기 때문.

        // 3. Form 로그인 설정
        http.formLogin()
                .loginPage("/loginForm") // 로그인 페이지 주소 설정
                .loginProcessingUrl("/login") // (MyUserDetailsService 호출, Post, x-www-urlencoded) 로그인 폼에서 입력받은 정보를 처리 - 로그인 폼에서 입력받은 정보가 전송되면 해당 URL로 요청
                .successHandler(((request, response, authentication) -> { // authentication 객체는 security context holder에 꼽혀있는 객체
                    log.debug("디버그 : 로그인 성공"); // @Slf4j

                    // View에서 사용하려고!!
                    MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", myUserDetails); // 세션 하나 더 만듦

                    response.sendRedirect("/");
                }))
                .failureHandler(((request, response, exception) -> { // 인증과 권한 실패시 항상 여기로.
                    log.debug("디버그 : 로그인 실패 : " + exception.getMessage()); // @Slf4j
                    response.sendRedirect("/loginForm");
                }));

        // 3. 인증, 권한 필터 설정 (이 프로젝트에서는 권한 처리 안하고 인증만) (인증 = 로그인)
        http.authorizeRequests( // HTTP 요청에 대한 접근 권한 설정
                authorize -> authorize.antMatchers("/s/**").authenticated() // 이 경로에 접근하려면 인증이 필요 - 만약 인증되지 않은 요청이 들어오면, 로그인 페이지로 리다이렉트
                        .anyRequest().permitAll() // 이외의 모든 요청은 인증된 사용자만 접근이 가능
        );

        return http.build();
    }
}
