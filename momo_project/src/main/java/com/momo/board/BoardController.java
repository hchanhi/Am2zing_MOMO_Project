package com.momo.board;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.domain.Board;
import com.momo.domain.Place;
import com.momo.place.PlaceService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@GetMapping("/board")
	public String board(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boards", boardList);
		return "Board/list";
	}
	
	@GetMapping("/postBoard")
	public String post() {
		return "Board/post";
	}
	
	//멤버는 일단 제거
	@PostMapping("/post")
	public String write(String boardTitle, Model model) {
		Board board = new Board();
		if (!Objects.isNull(boardTitle)&& !boardTitle.isBlank()) {
			board = this.boardService.save(boardTitle);
		}
		model.addAttribute("boardNum", board.getBoardNum());
		return "Board/addPlace";
		//나중에 장소추가하는 html로 변경.
	}
	
	 @GetMapping("/post/{boardNum}")
	    public String detail(@PathVariable("boardNum") Integer boardNum, Model model) {
	        Board board = boardService.getPost(boardNum);
	        model.addAttribute("board", board);
	        model.addAttribute("places", placeService.findByBoardNum(boardNum));
	        return "Board/detail";
	    }
	
	@GetMapping("/post/edit/{boardNum}")
	public String edit(@PathVariable("boardNum") Long boardNum, Model model) {
		Board board = boardRepository.findById(boardNum).get();
		List<Place> places = placeService.getPlaceList(boardNum.intValue());
		model.addAttribute("board", board);
		model.addAttribute("places", places);
		
		return "Board/edit";
	}
	
	//멤버는 잠시 제거
	@PutMapping("/post/edit/{boardNum}")
    public String update(Board board) {
        boardService.savePost(board);
        return "redirect:/post/{boardNum}";
    }
	
	@DeleteMapping("/post/{boardNum}")
    public String delete(@PathVariable("boardNum") Long boardNum) {
		boardService.deletePost(boardNum);
		
        return "redirect:/board";
    }
	////////////////////////////////////////////////////////////////////////
	@GetMapping("/addBoard")
	public String MapTest() {
		return "/Board/addBoard";
	}
	
	@RequestMapping("/writeBoard")
	public String addBoard(@RequestParam(required = false) String boardTitle, Model model) {
		Board board = new Board();
		if (!Objects.isNull(boardTitle)&& !boardTitle.isBlank()) {
			board = this.boardService.save(boardTitle);
		}

		model.addAttribute("boardNum", board.getBoardNum());

		return "Board/addPlace";

	}
	
	
}