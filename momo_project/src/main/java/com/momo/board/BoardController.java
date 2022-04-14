package com.momo.board;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/addBoard")
	public String MapTest() {
		return "/Board/addBoard";
	}
	
	@RequestMapping
	public String addBoard(@RequestParam(required = false) String boardTitle, Model model) {
		if (!Objects.isNull(boardTitle)&& !boardTitle.isBlank()) {
			this.boardService.save(boardTitle);
			
		}
		
		return "/Board/addBoard";
	}
	

}
