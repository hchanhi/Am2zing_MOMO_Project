package com.momo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.board.BoardService;
import com.momo.domain.Board;
import com.momo.place.PlaceService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("member/member-list")
	public void searchAllMembers(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllMembers(page));
	}
	
	@GetMapping("member/member-detail/{memId}")
	public String showMemberDetail(@PathVariable Integer memId, Model model) {
		model.addAttribute("member", this.adminService.findMemberByMemNum(memId));
		return "admin/member/member-detail";
	}
	
	@GetMapping("board/board-list")
	public void searchAllBoards(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllBoards(page));
	}
	
	@GetMapping("board/board-detail/{boardNum}")
	public String showboardDetail(@PathVariable Integer boardNum, Model model) {
		Board board = boardService.getPost(boardNum);
		model.addAttribute("board", board);
		model.addAttribute("places", this.placeService.findByBoardNum(boardNum));
		return "Board/detail";
	}
	
	@GetMapping("comment/comment-list")
	public void searchAllComments(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllComments(page));
	}
	
	@GetMapping("comment/comment-detail/{boardNum}")
	public String showcommentDetail(@PathVariable Integer boardNum, Model model) {
		Board board = boardService.getPost(boardNum);
		model.addAttribute("board", board);
		model.addAttribute("places", this.placeService.findByBoardNum(boardNum));
		return "Board/detail";
	}
	

}
