package com.momo.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private CommentService commentService;
	


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
	
	public void deleteComment(Long replyNum) {
        commentRepository.deleteById(replyNum);// 3
    }
	public void deleteComment02(Long replyNum) {
        commentRepository.deleteById(replyNum); // 3
    }
	public ResponseEntity<?> update(Map<String,String> map) {
		//System.out.println(map.toString());
		Map<String, String> tempMap=new HashMap<>();
		//DB처리
		String replyContent = map.get("replyContent");
		int boardNum = Integer.parseInt(map.get("boardNum"));
		this.save(replyContent, boardNum);
		
		try {
			
			//DB처리 후 서비스에서 Place PK를 반환 받아옴
			tempMap.put("replyContent", map.get("replyContent")); //DB에 넣으려는 제목
			//DB에 저장되고 난 후 placeNo(PK) int값 String.valeuOf 로 타입변환
			
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		//타이틀이랑 PK를 Map으로 view로 내려보냄
		return new ResponseEntity<Map<String, String>>(tempMap, HttpStatus.OK);
	}
	public void delete(Long replyNum) {
		commentRepository.deleteById((long) replyNum);
	}
	public List<Comment> getCommentList(int boardNum){
		Board board = boardRepository.findById((long) boardNum).get();
		List<Comment> commentList = this.commentRepository.findByBoard(board);
		return commentList;
	}
	/*
	 * public List<Comment> list() { // TODO Auto-generated method stub return
	 * commentRepository.list(); }
	 */
	/*
	 * public void saveReply(Comment comment) { Comment comment = new Comment();
	 * comment.setReplyContent(replyContent); comment.setBoard(board);
	 * 
	 * this.commentRepository.save(comment);
	 * 
	 * }
	 */
	public void deleteComment02(List<String> replyNumArray) {
		 for(int i=0; i<replyNumArray.size(); i++) {
	            String replyNum = replyNumArray.get(i);
	            Optional<Comment> optional = commentRepository.findById(Long.parseLong(replyNum));
	            
	        }
	    }

	
	
	
}
