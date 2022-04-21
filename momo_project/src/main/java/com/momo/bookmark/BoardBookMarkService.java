package com.momo.bookmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.board.BoardRepository;
import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
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
	
	public List<BoardBookmark> findAllByMember(Member member){
		return boardBookmarkRepository.findAllByMember(member);
	}
	
	public void save(Member member, long boardNum) {
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		BoardBookmark boardBookmark = new BoardBookmark();
		boardBookmark.setMember(member);
		boardBookmark.setBoard(board);
		
		boardBookmarkRepository.save(boardBookmark);
	}
	
	public void deleteBoardBookMark(Member member, long boardNum) {
		System.out.println(boardNum);
		Board board = boardRepository.findByBoardNum(boardNum).get(0);
		long boardBookmarkNum = boardBookmarkRepository.findByMemberAndBoard(member, board).getBoardBookmarkNum();
		
		boardBookmarkRepository.deleteById(boardBookmarkNum);
	}
}
