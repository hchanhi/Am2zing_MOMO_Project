package com.momo.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
