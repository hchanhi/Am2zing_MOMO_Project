package com.momo.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService02{
	
	@Autowired
	private CommentRepository02 commentRepository02;
	
	@Override
	public Comment2 createComment(Comment2 comment2) {
		return commentRepository02.save(comment2);
	}
	
	@Override
    public void deleteComment(Long replyNum) {
        commentRepository02.deleteById(replyNum); // 3
    }

    @Override
    public List<Comment2> getAllComments() {
        return commentRepository02.findAll(); // 4
    }
	
}
