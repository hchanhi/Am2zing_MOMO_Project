package com.momo.board;

import java.util.List;

import org.springframework.data.domain.Pageable;

<<<<<<< HEAD


=======
import org.springframework.data.domain.PageRequest;
>>>>>>> 345a3a6787ae3a0f37bb878fb48eb953fa217779

import org.springframework.data.jpa.repository.JpaRepository;


import com.momo.domain.Board;
import com.momo.domain.Member;

public interface BoardRepository extends JpaRepository<Board, Long>{
	List<Board> findByBoardNum(long boardNum);
	
	List<Board> findByMember(Member member);
	
	List<Board> findByMemberMemMbti(String memMbti, Pageable pageale);


}
	
	
