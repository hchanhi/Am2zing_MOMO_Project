package com.momo.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.domain.Board;
import com.momo.domain.Place;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
    private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	public List<Comment> findByBoardNum(long boardNum){

		Board board = boardRepository.findById((long) boardNum).get();
		return this.replyRepository.findByBoard(board);
	}
	
	public String replyWrite(Comment comment, Long boardNum){

     
        Optional<Board> findBoard = boardRepository.findById(boardNum);

       comment.setBoard(findBoard.get());
 
        replyRepository.save(comment);

        return "/comment/test03";
    }
	
	
}
