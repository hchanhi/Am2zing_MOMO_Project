package com.momo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.place.PlaceService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PlaceService placeService;
	
	@GetMapping("member/member-list")
	public void searchAllMembers(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllMembers(page));
	}
	
	@GetMapping("member/member-detail/{memNum}")
	public String showMemberDetail(@PathVariable Integer memNum, Model model) {
		model.addAttribute("member", this.adminService.findMemberByMemNum(memNum));
		return "admin/member/member-detail";
	}
	
	@GetMapping("board/board-list")
	public void searchAllBords(Model model
					, @RequestParam(required = false, defaultValue = "1")
					  int page) {
		
		model.addAllAttributes(adminService.findAllBoards(page));
	}
	
	@GetMapping("board/board-detail/{boardNum}")
	public String showboardDetail(@PathVariable Integer boardNum, Model model) {
		model.addAttribute("myplaces", this.placeService.findByBoardNum(boardNum));
		return "view/test02";
	}
}
