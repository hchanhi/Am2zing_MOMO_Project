package com.momo.comment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.momo.domain.Board;


public interface ReplyRepository extends CrudRepository<Comment, Long>{
	List<Comment> findByBoard(Board board);

}
