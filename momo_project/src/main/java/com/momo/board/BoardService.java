package com.momo.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.domain.Board;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepositoty;
	
	public void save(String boardTitle) {
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		this.boardRepositoty.save(board);
	}
	
	
}
