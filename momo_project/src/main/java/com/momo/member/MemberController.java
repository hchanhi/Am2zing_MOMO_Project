package com.momo.member;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


public class MemberController {
	private MemberService memberService;
	
	//메인 페이지 이동 
	
	@GetMapping("/member") 
	public String MemberMain() { 
		return "/Member/index"; 
		} 
	
	// 로그인 페이지 이동 
	
	@GetMapping("/user/login")
	public String goLogin() {
		return "Member/Login"; 
		} 
	
	/* 로그인 에러 *
	  @param model 
	*/ 
	@GetMapping("/login-error") 
	public String loginError(Model model) {
		model.addAttribute("loginError", true); 
		return "/Memebr/Login"; } 
	
	//회원가입 페이지 이동
	@GetMapping("/signup") 
	public String goSignup() { 
		return "Memebr/joinForm"; 
		} 
	
	// 회원가입 처리 @param memberDto 
	
	@PostMapping("/signup") 
	public String signup(MemberDto memberDto) { 
		memberService.joinUser(memberDto);
		return "redirect:/Memebr/Login"; } 
	
	// 접근 거부 페이지 이동
	@GetMapping("/denied") 
	public String doDenied() { 
		return "Memeber/denied"; 
		} 
		
	// 내 정보 페이지 이동
	@GetMapping("/info") 
	public String goMyInfo() { 
		return "Member/mypage"; 
		} 
	
	// Admin 페이지 이동 
	@GetMapping("/admin") 
	public String goAdmin() {
		return "Memebr/admin"; 
		}


}
