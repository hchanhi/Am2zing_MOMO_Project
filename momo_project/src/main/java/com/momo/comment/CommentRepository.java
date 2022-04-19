package com.momo.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.momo.domain.Board;
import com.momo.domain.Place;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByBoard(Board board);
	
}
