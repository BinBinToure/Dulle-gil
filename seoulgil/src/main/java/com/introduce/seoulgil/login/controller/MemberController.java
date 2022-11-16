package com.introduce.seoulgil.login.controller;


import com.introduce.seoulgil.login.dto.MemberDto;
import com.introduce.seoulgil.login.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    /**
     * 메인 페이지 이동
     * @return
     */
    @GetMapping("/")
    public String main() {
        return "index";
    }

    /**
     * 로그인 페이지 이동
     * @return
     */
    @GetMapping("/user/login")
    public String goLogin() {
        return "login/login";
    }

    /**
     * 로그인 에러
     * @param model
     * @return
     */
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "index";
    }

    /**
     * 회원가입 페이지 이동
     * @return
     */
    @GetMapping("/signup")
    public String goSignup() {
        return "member/join";
    }

    /**
     * 회원가입 처리
     * @param memberDto
     * @return
     */
    @PostMapping("/signup")
    public String signup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/";
    }

    /**
     * 접근 거부 페이지 이동
     * @return
     */
    @GetMapping("/denied")
    public String doDenied() {
        return "login/denied";
    }

    /**
     * 내 정보 페이지 이동
     * @return
     */
    @GetMapping("/info")
    public String goMyInfo() {
        return "login/myinfo";
    }

    /**
     * Admin 페이지 이동
     * @return
     */
    @GetMapping("/admin")
    public String goAdmin() {
        return "login/admin";
    }

    @GetMapping("/join")
    public String join(){
        return "member/join";
    }
}