package com.momo.comment;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.servlet.ModelAndView;

import com.momo.board.BoardRepository;
import com.momo.board.BoardService;
import com.momo.domain.Board;
import com.momo.domain.Comment;
import com.momo.domain.Place;
import com.momo.member.PrincipalDetails;
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
	
	
	@PostMapping("/commentList/{boardNum}")
	public String commentList(@PathVariable("boardNum") int boardNum, @RequestBody Map<String,String> map,Model model) {
		commentService.update(map);
		List<Comment> commentList = commentService.getCommentList(boardNum);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("comments", commentList);
		return "comment/comment_commentList";
	}

	@PostMapping("/commentList02/{boardNum}")
	public String commentList02(@PathVariable("boardNum") int boardNum, Model model) {
		List<Comment> commentList = commentService.getCommentList(boardNum);
		model.addAttribute("comments", commentList);
		return "comment/comment_commentList";
	}
	

	 
	 @DeleteMapping("/deleteComment/{commentNum}")
	    public String delete(@PathVariable("commentNum") long commentNum, @RequestParam int boardNum,Model model) {
	       commentService.delete(commentNum);
	       List<Comment> comments = commentService.findByBoardNum(boardNum);
	       model.addAttribute("comments", comments);
	       return "comment/comment_commentList";
	    }
}


