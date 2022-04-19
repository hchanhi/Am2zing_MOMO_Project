package com.momo.comment;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		return "comment/test03";
	}
	
	@GetMapping("/addComment/{boardNum}")
	public String MapTest03(@PathVariable Integer boardNum, Model model) {
		model.addAttribute("mycomment", this.commentService.findByBoardNum(boardNum));
		return "comment/test03"; 
	}
	
	@PostMapping("/commentList/{boardNum}")
	public String commentList(@PathVariable("boardNum") int boardNum, @RequestBody Map<String,String> map, Model model) {
		commentService.update(map);
		List<Comment> commentList = commentService.getCommentList(boardNum);
		model.addAttribute("comments", commentList);
		return "comment/commentList";
	}
	@PostMapping("/commentList02/{boardNum}")
	public String commentList02(@PathVariable("boardNum") int boardNum, Model model) {
		List<Comment> commentList = commentService.getCommentList(boardNum);
		model.addAttribute("comments", commentList);
		return "comment/commentList";
	}
	@RequestMapping(value = "test02/comment/delete/{replyNum}")
	public String deleteComment(@PathVariable Long replyNum, @PathVariable("boardNum") String boardNum, Model model) { // 8
		/*
		 * List<Comment> commentList = commentService.getCommentList(boardNum);
		 * model.addAttribute("comments", commentList);
		 */
		commentService.deleteComment(replyNum); 
	    return "view/test02/"+boardNum; // 9
	}
	/*
	 * @RequestMapping("/test02") public ModelAndView list(ModelAndView mav) {
	 * mav.setViewName("test02"); //뷰의 이름 List<Comment> list=commentService.list();
	 * mav.addObject("test02", list); //뷰에 전달할 데이터 return mav; //뷰로 이동 (화면 출력함) }
	 */
	
	/*
	 * @PostMapping("/commentList02/{boardNum}") public String
	 * commentList02(@PathVariable("boardNum") int
	 * boardNum, @PathVariable("replyNum") int replyNum,@RequestBody
	 * Map<String,String> map, Model model) { String num = String.valueOf(boardNum);
	 * String num2 = String.valueOf(replyNum); commentService.update(map);
	 * List<Comment> commentList = commentService.getCommentList(boardNum);
	 * model.getAttribute(num2); return "comment/commentList"; }
	 */
	/*
	 * @PostMapping("/commentList02/{boardNum}") public String
	 * commentList02(@PathVariable("boardNum") int boardNum, @RequestBody
	 * Map<String,String> map, Model model) { commentService.update(map);
	 * List<Comment> commentList = commentService.getCommentList(boardNum);
	 * model.addAttribute("comments", commentList); return "view/test02"; }
	 */
}
/*	@GetMapping(value="/pages/{boardNum}/{page}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Comment>> getListWithPage(@PathVariable("page") int page, @PathVariable("boardNum") int boardNum){
		return new ResponseEntity<>
	}
	*/
	/*
	 * @DeleteMapping("/commentList/{replyNum}") public String
	 * deleteStudent(@PathVariable int replyNum) { commentService.delete(replyNum);
	 * return "comment/commentList"; }
	 */
	
	/*
	 * @PostMapping("/writeComment") public String addComment(@RequestParam String
	 * replyContent, int boardNum, Model model) { if(!Objects.isNull(boardNum)&&
	 * !replyContent.isBlank()) { this.commentService.save(replyContent, boardNum);
	 * 
	 * } Board board = new Board(); model.addAttribute("boardNum",
	 * board.getBoardNum()); return replyContent; }
	 */
	
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping(value="/addComment",produces = MediaType.APPLICATION_JSON_VALUE)
	 * public Comment addComment(@RequestBody Comment comment) {
	 * 
	 * commentRepository.save(comment); return comment; } }
	 */

