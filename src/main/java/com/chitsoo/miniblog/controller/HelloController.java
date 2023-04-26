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
        return "/WEB-INF/views/user/temp.jsp";
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        System.out.println("출력");
        return "/WEB-INF/views/user/joinForm.jsp";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/WEB-INF/views/user/loginForm.jsp";
    }

    @GetMapping("/user/updateForm")
    public String userUpdateForm() {
        return "/WEB-INF/views/user/updateForm.jsp";
    }

    @GetMapping({ "/", "/board" })
    public String main() {
        return "/WEB-INF/views/board/main.jsp";
    }

    @GetMapping("/board/{id}")
    public String boardDetail() {
        return "/WEB-INF/views/board/detail.jsp";
    }

    @GetMapping("/board/saveForm")
    public String boardSaveForm() {
        return "/WEB-INF/views/board/saveForm.jsp";
    }

    @GetMapping("/board/{id}/updateForm")
    public String boardUpdateForm() {
        return "/WEB-INF/views/board/updateForm.jsp";
    }

//    @GetMapping("/user/profileUpdateForm")
//    public String profileUpdateForm(){
//        return "/WEB-INF/views/user/profileUpdateForm.jsp";
//    }

}
