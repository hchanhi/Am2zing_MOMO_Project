package com.momo.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.bookmark.BoardBookmarkRepository;
import com.momo.comment.CommentRepository;
import com.momo.common.util.pagination.Paging;
import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
import com.momo.domain.Comment;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberCustomRepository;
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
	private MemberCustomRepository memberCustomRepository;
	
	@Autowired
	private BoardBookmarkRepository boardBookmarkRepository;
	
	public Board save(String boardTitle, String memEmail) {
		Board board = new Board();
		Member member = memberCustomRepository.findByMemEmail(memEmail);
		board.setBoardTitle(boardTitle);
		board.setMember(member);
		boardRepository.save(board);
		
		return board;
	}
	
	@Transactional
    public Board editTitle(Long boardNum, String boardTitle) {
		Board board = boardRepository.findById(boardNum).get();
		board.setBoardTitle(boardTitle);
		return board;
    }
	
	public Map<String, Object> getBoardList(int page){
		List<Board> boardList = boardRepository.findAll(PageRequest.of(page-1, 6, Direction.DESC, "boardNum"))
				.getContent();
		Paging paging = Paging.builder()
				.url("/board")
				.total((int)boardRepository.count())
				.cntPerPage(6)
				.curPage(page)
				.blockCnt(5)
				.build();
		
		return Map.of("boards", boardList, "paging",paging);
	}
	
	@Transactional
    public Board getPost(Integer boardNum) {
        Board board = boardRepository.findById((long)boardNum).get();
        int count = board.getPlaceCnt()+1;
        board.setPlaceCnt(count);
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
			 Long commentNum = comment.getCommentNum();
			 commentRepository.deleteById(commentNum);
		 }
		 List<BoardBookmark> bookmarks = boardBookmarkRepository.findByBoard(board);
		 for(BoardBookmark bookmark : bookmarks) {
			 Long bookarkNum = bookmark.getBoard().getBoardNum();
			 boardBookmarkRepository.deleteById(bookarkNum);
		 }
		 
		 boardRepository.deleteById(board.getBoardNum());
	    }
	 
	 //검색
//	 @Transactional
//	 public Map<String, Object> searchmbti(int page,String memMbti){
//			List<Board> boardList = boardRepository.findByMemberMemMbti(memMbti, PageRequest.of(page-1, 6, Direction.DESC, "boardNum"));
//			Paging paging = Paging.builder()
//					.url("/board")
//					.total((int)boardRepository.count())
//					.cntPerPage(6)
//					.curPage(page)
//					.blockCnt(5)
//					.build();
//			
//			return Map.of("boards", boardList, "paging",paging);
//		}
	 
	 //내가쓴 게시글
	 public Map<String, Object> getMbtiBoardList(int page, String memMbti){
			List<Board> boardList = boardRepository.findByMemberMemMbti(memMbti, 
					PageRequest.of(page-1, 6, Direction.DESC, "boardNum"));
			
			Paging paging = Paging.builder()
					.url("/board/"+memMbti)
					.total((int)boardRepository.count())
					.cntPerPage(6)
					.curPage(page)
					.blockCnt(5)
					.build();
			
			return Map.of("boards", boardList, "paging",paging);
		}
	 
//	 public List<Board> getTop3Board(){
//		 return boardRepository.findTop3ByPlaceCnt();
//	 }
}
