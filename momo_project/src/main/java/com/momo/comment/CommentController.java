package com.momo.comment;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.momo.board.BoardRepository;
import com.momo.board.BoardService;
import com.momo.domain.Board;
import com.momo.domain.Place;
import com.momo.place.PlaceRepository;
import com.momo.place.PlaceService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/addComment")
	public String MapTest05() {
		return "/comment/test03";
	}
	
	@GetMapping("/test04/{boardNum}")
	public String MapTest03(@PathVariable Integer boardNum, Model model) {
		model.addAttribute("mycomment", this.commentService.findByBoardNum(boardNum));
		return "comment/test05"; 
	}
	
	
	
	@Transactional
	 @PostMapping("/writeComment") public String addComment(@RequestParam String
	 replyContent, int boardNum, Model model) { if(!Objects.isNull(boardNum)&&
	 !replyContent.isBlank()) { this.commentService.save(replyContent, boardNum);
	 
	  } Board board = new Board(); model.addAttribute("boardNum",
	 board.getBoardNum());
	  return "comment/test04"; }
	
	
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping(value="/addComment",produces = MediaType.APPLICATION_JSON_VALUE)
	 * public Comment addComment(@RequestBody Comment comment) {
	 * 
	 * commentRepository.save(comment); return comment; }
	 */
}
