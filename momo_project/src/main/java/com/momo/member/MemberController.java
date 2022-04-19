package com.momo.member;

import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.domain.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping({ "/member" })
	public String index() {
		return "member/index";
	}

	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("Principal : " + principal);
//		System.out.println("OAuth2 : "+principal.getUser().getProvider());
		// iterator 순차 출력 해보기
		Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
		while (iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			System.out.println(auth.getAuthority());
		}

		return "유저 페이지입니다.";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "어드민 페이지입니다.";
	}
	
	//@PostAuthorize("hasRole('ROLE_MANAGER')")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	@Secured("ROLE_MANAGER")
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "매니저 페이지입니다.";
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
	
	@GetMapping("/memberEditForm")
    public String myPageHome(Model model, @AuthenticationPrincipal Member currentMember) {
        List<Category> categoryList = categoryService.findAll();

        MemberForm memberForm = new MemberForm();
        memberForm.setName(currentMember.getUsername());
        memberForm.setEmail(currentMember.getEmail());
        
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("memberForm", memberForm);
        return "member/editMember";
    }
}
