package com.chitsoo.miniblog.controller;

import com.chitsoo.miniblog.core.auth.MyUserDetails;
import com.chitsoo.miniblog.dto.board.BoardRequest;
import com.chitsoo.miniblog.model.board.Board;
import com.chitsoo.miniblog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// @Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // RestAPI 주소 설계 규칙에서 자원에는 복수를 붙인다. 정석은 boards!!
    @GetMapping({"/", "/board"})
    // public String main(@AuthenticationPrincipal MyUserDetails myUserDetails){ // @AuthenticationPrincipal : 현재 로그인된 사용자의 Principal 정보(= 인증(Authentication)을 수행한 사용자의 정보)를 주입
        // log.debug("디버그 : " + myUserDetails.getUsername());

//    public @ResponseBody Page<Board> main(@RequestParam(defaultValue = "0") Integer page){ // 쿼리스트링 -> 안 넣어줄 수도 있으니 default값 넣어주기, null 값 확인할 수 있는 Integer가 더 나음
    public String main(@RequestParam(defaultValue = "0") Integer page, Model model){ // Model -  Spring MVC에서 컨트롤러와 뷰 사이의 데이터를 전달하는 데 사용되는 객체
        PageRequest pageRequest = PageRequest.of(page, 8, Sort.by("id").ascending());
        Page<Board> boardPG = boardService.글목록보기(pageRequest); // 영속화 된게 아님 - 서비스 요청하고 나서 컨트롤러 돌아오는 순간 OSIV가 꺼져있으니깐(db세션 닫힘) 비영속. db세션에 연결 안됨. db랑 상관없음 더이상.
        // 영속성 컨택스트에는 남아있지만, (데이터베이스)세션이 종료되었기 때문에 조회가 안됨. lazy loading이 안됨. 그래서 개념상 비영속이라고 봄.

        model.addAttribute("boardPG", boardPG); // 개념상 request.setAttribute와 비슷.
        return "board/main";
    }

    @GetMapping("/s/board/saveForm") // 앞에 s가 붙으면 인증된 사용자만
    public String saveForm(){
        return "board/saveForm";
    }

    @PostMapping("/s/board/save")
    public String save(BoardRequest.SaveInDTO saveInDTO, @AuthenticationPrincipal MyUserDetails myUserDetails){ // User정보도 넣어주어야 하므로. SecurityContextHolder에서 받아옴.
        boardService.글쓰기(saveInDTO, myUserDetails.getUser().getId());
        return "redirect:/"; // 완료하면 메인페이지로 이동
    }
}
