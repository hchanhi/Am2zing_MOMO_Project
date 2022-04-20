package com.momo.board;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.comment.Comment;
import com.momo.comment.CommentRepository;
import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberRepository;
import com.momo.place.PlaceRepository;
import com.momo.place.PlaceService;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public Board save(String boardTitle, String memEmail) {
		Board board = new Board();
		Member member = memberRepository.findByMemEmail(memEmail);
		board.setBoardTitle(boardTitle);
		board.setMember(member);
		boardRepository.save(board);
		
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
	    public void deletePost(Board board) {
		 List<Place> places = placeService.findByBoardNum(board.getBoardNum().intValue());
		 for(Place place : places) {
			 Long placeNum = place.getPlaceNum();
			 placeRepository.deleteById(placeNum);
		 }
		 List<Comment> comments = commentRepository.findByBoard(board);
		 for(Comment comment : comments) {
			 Long replyNum = comment.getReplyNum();
			 commentRepository.deleteById(replyNum);
		 }
		 boardRepository.deleteById(board.getBoardNum());
	    }
	
}
