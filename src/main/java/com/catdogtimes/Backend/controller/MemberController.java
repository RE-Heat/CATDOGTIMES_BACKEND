package com.catdogtimes.Backend.controller;

import com.catdogtimes.Backend.dto.MemberDTO;
import com.catdogtimes.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/times/member/sign_up")
    public String signupForm(){
        return "sign_up";
    }

    @PostMapping("/times/member/sign_up")
    public String signup(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.signup");
        System.out.println("memberDTO = " + memberDTO);
        memberService.signup(memberDTO);

        return "index";
    }
}
