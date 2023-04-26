package com.chitsoo.miniblog.controller;

import com.chitsoo.miniblog.dto.user.UserRequest;
import com.chitsoo.miniblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // 의존성 주입해야 하니 - final 필드를 생성자 매개변수를 통해 주입
@Controller
public class UserController {

    private final UserService userService;

    // 인증이 되지 않은 상태에서 인증과 관련된 주소는 앞에 엔티티 적지 마세요.
    // write (post) : /리소스/식별자(pk, uk)/save or delete or update
    // read (get) : /리소스/식별자(pk, uk)
    @PostMapping("/join")
    public String join(UserRequest.JoinInDTO joinInDTO){ // x-www-form-urlencoded (앞에 RequestBody 없으니 json 아님)
        userService.회원가입(joinInDTO);
        return "redirect:/loginForm"; // 클라이언트에게 302 HTTP 상태 코드와 함께 지정된 URL로 다시 요청하도록 유도
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }
}
