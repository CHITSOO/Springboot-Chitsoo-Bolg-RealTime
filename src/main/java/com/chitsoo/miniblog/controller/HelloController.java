package com.chitsoo.miniblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Controller
public class HelloController {
    @GetMapping("/temp")
    public String temp_test() {
        System.out.println("temp출력");
        return "user/temp";
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        System.out.println("출력");
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String userUpdateForm() {
        return "user/updateForm";
    }

    @GetMapping({ "/", "/board" })
    public String main() {
        return "board/main";
    }

    @GetMapping("/board/{id}")
    public String boardDetail() {
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String boardSaveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String boardUpdateForm() {
        return "board/updateForm";
    }

    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(){
        return "user/profileUpdateForm";
    }

}
