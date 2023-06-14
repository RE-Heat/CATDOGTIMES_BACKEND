package com.catdogtimes.Backend.controller;

import com.catdogtimes.Backend.dto.MemberDTO;
import com.catdogtimes.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/times/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/times/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    //로그인
    @GetMapping("/times/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/times/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult !=null){
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            //로그인 실패
            return "login";
        }
    }

    @GetMapping("/times/member")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/times/member/{id}")
    public String findById(@PathVariable Long id, Model model){ //경로상에 있는 id를 받아오겠다.
        MemberDTO memberDTO = memberService.findById(id); //한 개니까 list아님
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/times/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/times/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/times/member/" + memberDTO.getId();
    }

    @GetMapping("/times/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        //model에 담아서 가줘야하는데 안 담아가니 redirect로 findAll 불러와야
        return "redirect:/times/member/";
    }

    @GetMapping("/times/member/logout")
    public String logout(HttpSession session){
        session.invalidate(); // 로그인 무효화
        return "index";
    }
    
    //아이디 중복 확인
    @PostMapping("/times/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
//        if (checkResult !=null){
//            return "ok";
//        } else {
//            return "no";
//        }
    }
}
