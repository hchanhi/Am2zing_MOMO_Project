package com.momo.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.domain.Board;
import com.momo.domain.Member;

@Service
public class BoardBookMarkService {
	@Autowired
	private BoardBookmarkRepository boardBookmarkRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	public boolean isBoardBookmarkChecked(Member member, long boardNum) {
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		if(boardBookmarkRepository.findByMemberAndBoard(member, board) == null) {
			return true;
		} else return false;
	}
}
