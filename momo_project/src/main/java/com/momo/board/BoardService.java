package com.momo.board;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.place.PlaceRepository;
import com.momo.place.PlaceService;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired PlaceService placeService;
	
	public Board save(String boardTitle) {
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		this.boardRepository.save(board);
		
		return board;
	}
	
	@Transactional
    public Board savePost(Board board) {
        return boardRepository.save(board);
    }
	
	public List<Board> getBoardList(){
		List<Board> boardList = boardRepository.findAll();
		List<Board> boardArrayList = new ArrayList<>();
		
		for(Board board : boardList) {
//			board.getBoardNum();
//			board.getBoardTitle();
//			board.getMember();
			boardArrayList.add(board);
		}
		return boardArrayList;
	}
	
	@Transactional
    public Board getPost(Integer boardNum) {
        Board board = boardRepository.findById((long)boardNum).get();

        return board;
    }
	
	 @Transactional
	    public void deletePost(Long boardNum) {
		 List<Place> places = placeService.findByBoardNum(boardNum.intValue());
		 
		 for(Place place : places) {
			 Long placeNum = place.getPlaceNum();
			 placeRepository.deleteById(placeNum);
		 }
		 
		 boardRepository.deleteById(boardNum);
	    }
	
}
