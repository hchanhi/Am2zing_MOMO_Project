package com.momo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	/*
	 * @GetMapping("/test222") private String insertComment(@RequestParam("idx") int
	 * idx, @RequestParam("content") String content) throws Exception { Comment
	 * comment = new Comment(); comment.setContent(content); comment.setIdx(idx);
	 * commentService.insertComment(comment);
	 * 
	 * }
	 */
}
