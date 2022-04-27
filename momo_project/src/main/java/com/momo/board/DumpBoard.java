package com.momo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.momo.domain.Board;
import com.momo.domain.Member;
import com.momo.domain.Place;
import com.momo.member.MemberRepository;
import com.momo.place.PlaceRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DumpBoard implements CommandLineRunner{

    private final BoardRepository boardRepository;
    
    private final MemberRepository memberRepository;
    
    private final PlaceRepository placeRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
    	String encPassword1 = bCryptPasswordEncoder.encode("bbbb");
        Member member1 = memberRepository.save(Member.builder()
            .memEmail("bbbb@bbbb.com")
            .memNickname("bbbb")
            .memMbti("ENFP")
            .memPassword(encPassword1)
            .memBirth("2000-01-17")
            .memRole("ROLE_MEMBER")
            .build());
        	
        Board board1 = boardRepository.save(Board.builder()
	        .boardTitle("1번")
	        .member(member1)
	        .placeCnt(1000)
	        .build());
        
        Place place1 = placeRepository.save(Place.builder()
        		.placeTitle("스타벅스 강남점")
        		.placeContent("조용해요")
        		.placeId("12345")
        		.placeLat("37.506229157876774")
        		.placeLng("127.05688880326076")
        		.board(board1)
        		.member(member1)
        		.build());
    }
}