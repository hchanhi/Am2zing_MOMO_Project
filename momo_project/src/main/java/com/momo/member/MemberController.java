package com.momo.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.board.BoardRepository;
import com.momo.board.BoardService;
import com.momo.bookmark.BoardBookMarkService;
import com.momo.bookmark.BoardBookmarkRepository;
import com.momo.comment.CommentRepository;
import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
import com.momo.domain.Comment;
import com.momo.domain.Member;
import com.momo.place.PlaceService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;


@Controller
@RequiredArgsConstructor

public class MemberController {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private BoardBookMarkService boardBookMarkService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private BoardBookmarkRepository boardBookmarkRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	

//	//?????? ?????? ????????? -> ?????? ?????? ?????? ?????? 
//	@GetMapping({ "/member" })
//	public String index() {
//		return "member/index";
//	}

	//????????? ????????? ??????
	@GetMapping("/member/login")
	 public String goLogin(HttpServletRequest request) {
        //?????? ???????????? ??????
        String url = request.getHeader("Referer");
        if(!url.contains("member/member_loginForm")) {
            request.getSession().setAttribute("prevPage",request.getHeader("Referer"));
        }
		return "member/member_loginForm"; 
		} 
	
	/* ????????? ?????? */
    @PostMapping("member/login")
    public String loginError(HttpServletRequest request, Model model) {
        String loginFailMsg = (String) request.getAttribute("loginFailMsg");
        model.addAttribute("loginFailMsg",loginFailMsg);
        return "member/member_loginForm";
    }

	@GetMapping("member/join")
	public String join() {
		return "member/member_joinForm";
	}
	
	//???????????????
	@GetMapping("member/mypage")
    public String mypage(Authentication authentication, Model model) {
        Member member = memberService.mypage(authentication.getName());
        model.addAttribute("member",member);

        return "member/member_mypage";
    }
	

	//????????? ?????????
	@GetMapping("member/boardBookmark") 
	public String goMyboardBookmark(@AuthenticationPrincipal PrincipalDetails principal, Model model, @RequestParam(required = false, defaultValue = "1")
	  int page) {
		Member member = principal.getMember();
		model.addAllAttributes(boardBookMarkService.findAllByMember(member, page));
		return "member/boardBookmark"; 
		} 
	
	 //????????? ??????
    @PostMapping("member/myinfoEdit")
    public String memberUpdate(Member member) {
        memberService.updateMember(member);
        return "member/member_mypage";
    }

	//???????????? ??????
	@PostMapping("/joinProc")
	public String joinProc(Member member) {
		
		String rawPassword = member.getMemPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		member.setMemPassword(encPassword);
		member.setMemRole("ROLE_MEMBER");
		memberRepository.save(member);
		return "member/member_loginForm";
	}
	
	//???????????? ????????? ?????? ??????
	@ResponseBody
    @GetMapping("/member/emailChk")
    public HashMap<String, Object> memEmailCheck(@RequestParam(value = "memEmail", required = false) String memEmail) {
		System.out.println(memberService.memEmailCheck(memEmail));
        return memberService.memEmailCheck(memEmail);
    }
	
	//???????????? ????????? ?????? ??????
    @ResponseBody
    @GetMapping("/member/nicknameChk")
    public HashMap<String, Object> memNicknameCheck(@RequestParam(value = "memNickname", required = false) String memNickname) {
        return memberService.memNicknameCheck(memNickname);
    }
    
    //????????? ?????? ?????? ??????
    @ResponseBody
    @GetMapping("/member/nicknameEdit")
    public HashMap<String, Object> memNicknameEdit(@RequestParam(required = false) Long memId, String memNickname) {
        return memberService.memNicknameEdit(memNickname, memId);
    }
    
    
    //???????????? ???????????????
    @GetMapping("/member/pwdEdit")
    public String EditPassword(Authentication authentication, Model model) {
    	Member member = memberService.mypage(authentication.getName());
    	model.addAttribute("member",member);

        return "member/member_pwd_edit";
    }
    
    //???????????? ??????
    @ResponseBody
    @GetMapping("/member/pwdCheck")
    public HashMap<String, Object> pwCheck(@RequestParam(required = false) String original_Pw, Authentication authentication) {
        return memberService.pwCheck(authentication,original_Pw);
    }
    

    //???????????? ??????
    @PostMapping("/member/pwdEdit")
    public String pwdUpdate(Authentication authentication,Member member) {
        member.setMemEmail(authentication.getName());
        memberService.passwordUpdate(member);
        return "redirect:/logout";
    }

    
    //?????? ?????? ??????
    @PostMapping("/memeber/withdrawal")
    public String withdrawalMember(Member member, HttpSession session) {
        List<Board> boards = boardRepository.findByMember(member);
        for(Board board : boards) {
        	boardService.deletePost(board);
        }
        for(BoardBookmark boardBookmark :boardBookmarkRepository.findByMember(member)) {
        	boardBookmarkRepository.deleteById(boardBookmark.getBoardBookmarkNum());
        }
        for(Comment comment: commentRepository.findAllByMemberMemId(member.getMemId())) {
        	commentRepository.deleteById(comment.getCommentNum());
        }
    	memberService.deleteMember(member.getMemId());
        session.invalidate();
        return "redirect:/";
    }
    
    // ????????? ???
    @GetMapping("/member/board/{memId}")
	public String memBoard(Authentication authentication, @PathVariable("memId") Long memId, Model model,
			@RequestParam(required = false, defaultValue = "1") int page) {
		model.addAllAttributes(memberService.getMemBoardList(page, memId));
		Member member = memberService.mypage(authentication.getName());
        model.addAttribute("member",member);
		return "member/member_board_list";
	}
    
    // ????????? ??????
    @GetMapping("/member/comment/{memId}")
	public String memComment(Authentication authentication, @PathVariable("memId") Long memId, Model model,
			@RequestParam(required = false, defaultValue = "1") int page) {
		model.addAllAttributes(memberService.getMemComList(page, memId));
		 Member member = memberService.mypage(authentication.getName());
	      model.addAttribute("member",member);
		return "member/member_comment_list";
	}
  
    @GetMapping("/member/comment/comment-detail/{boardNum}")
	public String showcommentDetail(@PathVariable Integer boardNum, Model model) {
		Board board = boardService.getPost(boardNum);
		model.addAttribute("board", board);
		model.addAttribute("places", placeService.getPlaceList(boardNum));
		return "Board/board_detail";
	}
	
	
}
