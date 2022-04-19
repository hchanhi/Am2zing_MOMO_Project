package com.momo.comment;

import java.util.List;

public interface CommentService02 {
	
	
	void deleteComment(Long replyNum);
	List<Comment2> getAllComments();
	Comment2 createComment(Comment2 comment);
	
}
