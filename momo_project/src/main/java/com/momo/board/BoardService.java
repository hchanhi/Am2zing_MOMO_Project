package com.momo.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.domain.Board;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	
	public void save(String boardTitle) {
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		this.boardRepository.save(board);
	}
	
	
}
