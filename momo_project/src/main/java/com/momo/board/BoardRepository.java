package com.momo.board;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import com.momo.domain.Board;
import com.momo.domain.Member;

public interface BoardRepository extends JpaRepository<Board, Long>{
	List<Board> findByBoardNum(long boardNum);
}
	
	
