package com.momo.board;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.bookmark.BoardBookMarkService;
import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.PrincipalDetails;
import com.momo.place.PlaceRepository;
import com.momo.place.PlaceService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired 
	private BoardBookMarkService boardBookmarkService;
	
	
	@GetMapping("/board")
	public String board(Model model, @RequestParam(required = false, defaultValue = "1")
	  int page) {
		List<Board> boardList = boardRepository.findAll();
		for(Board board : boardList) {
			if(placeService.findByBoardNum(board.getBoardNum().intValue()).isEmpty()) {
				this.delete(board.getBoardNum());
			}
		}
		model.addAllAttributes(boardService.getBoardList(page));
		return "Board/board_list";
	}
	
	@GetMapping("/postBoard")
	public String post(@AuthenticationPrincipal PrincipalDetails principal) {
		if(principal == null) {
			return "Member/member_loginForm";
		}
		return "Board/board_post";
	}
	
	//멤버는 일단 제거
	@PostMapping("/post")
	public String write(String boardTitle, String memEmail, Model model) {
		if (!Objects.isNull(boardTitle)&& !boardTitle.isBlank()) {
			Board board = boardService.save(boardTitle,memEmail);
			System.out.println(board.getMember().getMemMbti());
			model.addAttribute("boardNum", board.getBoardNum());
			return "Board/board_addplace";
		} else {
		return "redirect:/board";
	}
}
	
	 @GetMapping("/post/{boardNum}")
	    public String detail(@PathVariable("boardNum") Integer boardNum, @AuthenticationPrincipal PrincipalDetails principal, Model model) {
		 	Board board = boardService.getPost(boardNum);
	    if(!placeService.findByBoardNum(boardNum).isEmpty()) {
	        if(principal == null) {
	        	model.addAttribute("boardBookmarkCheck", true);
	        } else {
	        	Member member = principal.getMember();
	        	model.addAttribute("boardBookmarkCheck", boardBookmarkService.isBoardBookmarkChecked(member, (long) boardNum));
	        }
	        model.addAttribute("board", board);
	        model.addAttribute("places", placeService.findByBoardNum(boardNum));
	        
	        return "Board/board_detail";
	    } else {
	    	this.delete((long)boardNum);
	    	return "redirect:/board";
	    }
}
	
	@GetMapping("/post/edit/{boardNum}")
	public String edit(@PathVariable("boardNum") Long boardNum, Model model) {
		Board board = boardRepository.findById(boardNum).get();
		List<Place> places = placeService.getPlaceList(boardNum.intValue());
		model.addAttribute("board", board);
		model.addAttribute("places", places);
		
		return "Board/board_edit";
	}
	
	//멤버는 잠시 제거
	@PutMapping("/post/edit/{boardNum}")
    public String update(Long boardNum, String boardTitle) {
		if(placeService.findByBoardNum(boardNum.intValue()).isEmpty()) {
			return "Board/board_list";
		} else {
		boardService.editTitle(boardNum, boardTitle);
        return "redirect:/post/{boardNum}";
		}
    }
	
	@DeleteMapping("/post/{boardNum}")
    public String delete(@PathVariable("boardNum") Long boardNum) {
		List<Board> boards = boardRepository.findByBoardNum(boardNum);
		for(Board board:boards) {
		boardService.deletePost(board);
		}
        return "redirect:/board";
    }
	////////////////////////////////////////////////////////////////////////
	@GetMapping("/addBoard")
	public String MapTest() {
		return "/Board/board_addBoard";
	}
	
	@RequestMapping("/writeBoard")
	public String addBoard(@RequestParam(required = false) String boardTitle, String memEmail, Model model) {
		Board board = new Board();
		if (!Objects.isNull(boardTitle)&& !boardTitle.isBlank()) {
			board = this.boardService.save(boardTitle, memEmail);
		}

		model.addAttribute("boardNum", board.getBoardNum());

		return "Board/board_addPlace";
	}
	@GetMapping("/cancel/{boardNum}")
    public String cancel(@PathVariable("boardNum") Long boardNum) {
		List<Board> boards = boardRepository.findByBoardNum(boardNum);
		for(Board board:boards) {
		boardService.deletePost(board);
		}
        return "redirect:/board";
    }
	//검색
//	@GetMapping("/board/search")
//	public String search(@RequestParam(value="keyword") String keyword, Model model,@RequestParam(required = false, defaultValue = "1")
//	  int page) {
//		model.addAllAttributes(boardService.searchmbti(page,keyword));
//		return "Board/list";
//	}
	
	@GetMapping("/board/{memMbti}")
	public String mbtiBoard(@PathVariable("memMbti")String memMbti, Model model,
			@RequestParam(required = false, defaultValue = "1") int page) {
		model.addAllAttributes(boardService.getMbtiBoardList(page, memMbti));
		return "Board/board_list";
	}
	
//	@GetMapping("/top3")
//	public List<Board> index() {
//		List<Board> boards = boardService.getTop3Board();
//		return boards;
//	}
}
