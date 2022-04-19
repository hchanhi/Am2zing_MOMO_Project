package com.momo.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.momo.common.util.pagination.Paging;
import com.momo.domain.Board;
import com.momo.domain.Member;


@Service
public class AdminService {
	
	@Autowired
	private AdminMemberRepository adminMemberRepository;
	
	@Autowired
	private AdminBoardRepository adminBoardRepository;
	
	//회원 전체 조회
	public Map<String,Object> findAllMembers(int page){
		
		int cntPerPage = 10;
		List<Member> members = adminMemberRepository
				.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "memNum"))
				.getContent();
		
		Paging paging = Paging.builder()
				.url("/admin/member/member-list")
				.total((int)adminMemberRepository.count())
				.cntPerPage(cntPerPage)
				.curPage(page)
				.blockCnt(1)
				.build();
		
		return Map.of("members",members,"paging",paging);
	}
	
	//해당 회원 조회
	public Member findMemberByMemNum(int memNum) {
		Member member = adminMemberRepository.findById((long) memNum).get();
		
		return member;
	}
	
	public Map<String, Object> findAllBoards(int page){
		int cntPerPage = 9;
		List<Board> boards = adminBoardRepository
				.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "BoardNum"))
				.getContent();
		
		Paging paging = Paging.builder()
				.url("/admin/board/board-list")
				.total((int)adminBoardRepository.count())
				.cntPerPage(cntPerPage)
				.curPage(page)
				.blockCnt(1)
				.build();
		
		return Map.of("boards",boards,"paging",paging);
		
	}
}
