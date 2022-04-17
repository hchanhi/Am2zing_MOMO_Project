package com.momo.comment;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.domain.Board;
import com.momo.domain.Place;
import com.momo.place.PlaceRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Comment> findAll() {
		return this.commentRepository.findAll();
	}
	public List<Comment> findByBoardNum(int boardNum){

		Board board = boardRepository.findById((long) boardNum).get();
		return this.commentRepository.findByBoard(board);
	}
	
	public void save(String replyContent, int boardNum) {
		Board board = boardRepository.findById((long) boardNum).get();

		Comment comment = new Comment();
		comment.setReplyContent(replyContent);
		comment.setBoard(board);
		
		this.commentRepository.save(comment);
	}

	

	
	
	
}
