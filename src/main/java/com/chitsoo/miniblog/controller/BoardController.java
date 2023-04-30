package com.chitsoo.miniblog.controller;

import com.chitsoo.miniblog.core.auth.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    // RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다. 정석은 boards!!
    @GetMapping({"/", "/board"})
    // public String main(@AuthenticationPrincipal MyUserDetails myUserDetails){ // @AuthenticationPrincipal : 현재 로그인된 사용자의 Principal 정보(= 인증(Authentication)을 수행한 사용자의 정보)를 주입
        // log.debug("디버그 : " + myUserDetails.getUsername());
    public String main(){
        return "board/main";
    }

    @GetMapping("/s/board/saveForm") // 앞에 s가 붙으면 인증된 사용자만
    public String saveForm(){
        return "board/saveForm";
    }
}
