package com.chitsoo.miniblog.service;

import com.chitsoo.miniblog.dto.user.UserRequest;
import com.chitsoo.miniblog.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // SecurityConfig Bean 등록함

    // insert, update, delete -> try catch 처리
    @Transactional // springframework의 트랜젝션
    public void 회원가입(UserRequest.JoinInDTO joinInDTO){
        try{
            // 1. 패스워드 암호화
            joinInDTO.setPassword(passwordEncoder.encode(joinInDTO.getPassword()));

            // 2. DB 저장
            userRepository.save(joinInDTO.toEntity());
        }catch (Exception e){
            throw new RuntimeException("회원가입 오류 : " + e.getMessage()); // 나중에 직접 Exception 만든 거로 교체 헤야함. Exception을 직접 제어해야 하기 때문.
        }
    } // 더티 체킹, DB 세션 종료(OSIV=false) => 컨트롤러에서는 더이상 select(lazy loading) 할 수 없음
}
