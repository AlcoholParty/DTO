package com.study.dto.controller;

import com.study.dto.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "Main";
    }

    //로그인 화면으로 이동
    @GetMapping("/loginform")
    public String loginForm(Model model) {
        // 0. 사용할 DTO를 바인딩 한다.
        model.addAttribute("memberDTO", new Member.rqLoginMemeber());
        return "SignUp/LoginForm";
    }
}
