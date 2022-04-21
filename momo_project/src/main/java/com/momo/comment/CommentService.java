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
import com.momo.domain.Comment;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberRepository;
import com.momo.place.PlaceRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	   private MemberRepository memberRepository;



	public List<Comment> findAll() {
		return this.commentRepository.findAll();
	}
	public List<Comment> findByBoardNum(int boardNum){

		Board board = boardRepository.findById((long) boardNum).get();
		return this.commentRepository.findByBoard(board);
	}
	
	public void save(String replyContent, int boardNum, String memEmail, String memNickname) {
	      Board board = boardRepository.findById((long) boardNum).get();
	      Member member = memberRepository.findByMemEmail(memEmail);
	      Member nick = memberRepository.findByMemNickName(memNickname);
	      Comment comment = new Comment();
	      comment.setReplyContent(replyContent);
	      comment.setBoard(board);
	      comment.setMember(member);
	      comment.setMember(nick);
	      this.commentRepository.save(comment);
	   }

	
	public void deleteComment(Long replyNum) {
        commentRepository.deleteById(replyNum);// 3
    }
	public void deleteComment02(Long replyNum) {
        commentRepository.deleteById(replyNum); // 3
    }
	 public ResponseEntity<?> update(Map<String,String> map) {
	      
	      Map<String, String> tempMap=new HashMap<>();
	   
	      String replyContent = map.get("replyContent");
	      String memEmail = map.get("memEmail");
	      String memNickname = map.get("memNickname");
	      int boardNum = Integer.parseInt(map.get("boardNum"));
	      this.save(replyContent, boardNum, memEmail,memNickname);
	      
	      try {      
	         tempMap.put("replyContent", map.get("replyContent"));          
	      } catch(Exception e) {
	         return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	      }
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
	
	public void deleteComment02(List<String> replyNumArray) {
		 for(int i=0; i<replyNumArray.size(); i++) {
	            String replyNum = replyNumArray.get(i);
	            Optional<Comment> optional = commentRepository.findById(Long.parseLong(replyNum));
	            
	        }
	    }

	
	
	
}
