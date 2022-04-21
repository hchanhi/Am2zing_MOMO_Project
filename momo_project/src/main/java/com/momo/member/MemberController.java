package com.momo.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.bookmark.BoardBookMarkService;
import com.momo.domain.Member;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberRepository userRepository;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private BoardBookMarkService boardBookMarkService;

	@GetMapping({ "/member" })
	public String index() {
		return "member/index";
	}


	@GetMapping("/loginForm")
	public String goLogin() {
		return "Member/Login"; 
		} 
	
	/* 로그인 에러 *
	  @param model 
	*/ 
	@GetMapping("/login-error") 
	public String loginError(Model model) {
		model.addAttribute("loginError", true); 
		return "Member/Login"; } 

	@GetMapping("/joinForm")
	public String join() {
		return "Member/joinForm";
	}
	
	@GetMapping("/info") 
	public String goMyInfo() { 
		return "Member/mypage"; 
		} 
	
	@GetMapping("bookmark/board") 
	public String goMyboardBookmark(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		Member member = principal.getMember();
		model.addAttribute("boardBookmarks", boardBookMarkService.findAllByMember(member));
		return "Member/boardBookmark"; 
		} 

	@PostMapping("/joinProc")
	public String joinProc(Member member) {
		System.out.println("회원가입 진행 : " + member);
		String rawPassword = member.getMemPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		member.setMemPassword(encPassword);
		member.setMemRole("ROLE_MEMBER");
		userRepository.save(member);
		return "Member/Login";
	}
	
	//회원가입 이메일 중복 체크
	@ResponseBody
    @GetMapping("/member/emailCheck")
    public HashMap<String, Object> memEmailOverlap(@RequestParam(value = "memEmail", required = false) String memEmail) {
		System.out.println(memberService.memEmailOverlap(memEmail));
        return memberService.memEmailOverlap(memEmail);
    }
	
	//회원가입 닉네임 중복 체크
    @ResponseBody
    @GetMapping("/member/NicknameChk")
    public HashMap<String, Object> nicknameOverlap(@RequestParam(value = "memNickname", required = false) String memNickname) {
        return memberService.memNicknameOverlap(memNickname);
    }
    
//    //닉네임 수정 중복 체크
//    @ResponseBody
//    @PostMapping("/api/nicknameModify")
//    public HashMap<String, Object> nicknameModify(@RequestParam(required = false) Long id, String nickname) {
//        return memberService.nicknameModify(nickname, id);
//    }

	
	
}
