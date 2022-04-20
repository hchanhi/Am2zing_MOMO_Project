package com.momo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.comment.Comment;

public interface AdminCommentRepository extends JpaRepository<Comment, Long>{

}
