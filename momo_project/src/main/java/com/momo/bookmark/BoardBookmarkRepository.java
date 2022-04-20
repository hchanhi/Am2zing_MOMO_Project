package com.momo.bookmark;

import org.springframework.data.repository.CrudRepository;

import com.momo.domain.Board;
import com.momo.domain.BoardBookmark;
import com.momo.domain.Member;

public interface BoardBookmarkRepository extends CrudRepository<BoardBookmark, Long>{
	BoardBookmark findByMemberAndBoard(Member member, Board board);
}
