package com.study.dto.controller;

import com.study.dto.entity.Member;
import com.study.dto.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    SignUpService signUpService;

    //로그인
    @PostMapping("/loginform/login")
    public String login(Member.rqLoginMemeber memberRq) { // 1. DTO로 form값을 다 받아온다.
        System.out.println("0 : " + memberRq);
        // 2. 받아온 DTO를 서비스에 넘겨준다.
        Member.rpLoginMember member = signUpService.loginEmailId(memberRq);
        System.out.println("4 : " + member);
        // 8. 반환받은 DTO가 존재하는지 체크한다.
        // 8-1. 반환받은 DTO가 없는 경우
        if ( member == null ) {
            System.out.println("아이디가 틀렸습니다");
            // 다시 로그인 페이지로 이동
            return "redirect:/loginform";
        }
        // 8-2. 반환받은 DTO가 있는 경우
        // 9. DTO로 가져온 값중 비밀번호값과 입력한 비밀번호값을 비교한다.
        if ( !( memberRq.getPwd().equals( member.getPwd() ) ) ) {
            // 9-1. 비교한 비밀번호가 다른 경우
            System.out.println("비밀번호가 틀렸습니다");
            // 다시 로그인 페이지로 이동
            return "redirect:/loginform";
        }
        // 9-1. 비교한 비밀번호가 같은 경우
        // 메인 페이지로 이동
        return "Main";
    }

    @GetMapping("/joinform")
    public String joinForm(Model model) {
        // 0. 사용할 DTO를 바인딩 한다.
        model.addAttribute("memberDTO", new Member.rqJoinMember());
        return "SignUp/JoinForm";
    }

    //회원가입
    @PostMapping("/joinform/join")
    public String join(Member.rqJoinMember rqJoinMember, Model model) { // 1. DTO로 form값을 다 받아온다.
        // 2. 받아온 DTO를 서비스에 넘겨준다.
        Member.rpJoinMember member = signUpService.join(rqJoinMember);
        System.out.println(member);
        // 8. 반환받은 DTO를 바인딩 한다.
        model.addAttribute("nickname", member.getNickname());
        // 환영 페이지로 이동
        return "SignUp/Welcome";
    }
}
