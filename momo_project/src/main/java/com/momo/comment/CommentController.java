package com.momo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/reply_write")
	public String replyWrite(@ModelAttribute Comment comment, Long boardNum) {
		return commentService.replyWrite(comment, boardNum);
	}
	/*
	 * @GetMapping("/test02/{boardNum}") public String MapTest02(@PathVariable
	 * Integer boardNum, Model model) { model.addAttribute("myplaces",
	 * this.placeService.findByBoardNum(boardNum)); return "view/test02"; }
	 */
}
