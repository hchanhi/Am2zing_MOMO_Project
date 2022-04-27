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
    	String encPassword1 = bCryptPasswordEncoder.encode("1234");
    	Member member1 = memberRepository.save(Member.builder()
            .memEmail("bbbb@bbbb.com")
            .memNickname("bbbb")
            .memMbti("ENFP")
            .memPassword(encPassword1)
            .memBirth("2000-01-17")
            .memRole("ROLE_MEMBER")
            .build());
        
        Member member2 = memberRepository.save(Member.builder()
                .memEmail("cccc@cccc.com")
                .memNickname("cccc")
                .memMbti("ESFJ")
                .memPassword(encPassword1)
                .memBirth("1999-01-11")
                .memRole("ROLE_MEMBER")
                .build());
        
        Member member3 = memberRepository.save(Member.builder()
                .memEmail("dddd@dddd.com")
                .memNickname("dddd")
                .memMbti("ISTJ")
                .memPassword(encPassword1)
                .memBirth("1997-02-20")
                .memRole("ROLE_MEMBER")
                .build());
        
        Member member4 = memberRepository.save(Member.builder()
                .memEmail("eeee@eeee.com")
                .memNickname("eeee")
                .memMbti("ENTJ")
                .memPassword(encPassword1)
                .memBirth("1997-02-20")
                .memRole("ROLE_MEMBER")
                .build());
        
        Member member5 = memberRepository.save(Member.builder()
                .memEmail("ffff@ffff.com")
                .memNickname("ffff")
                .memMbti("ESFJ")
                .memPassword(encPassword1)
                .memBirth("1993-02-1")
                .memRole("ROLE_MEMBER")
                .build());
        
        Member[] member = {member1, member2, member3, member4, member5};
        
        for(int k=0;k<4;k++) {
	        for(int i=1;i<9;i++) {
		        Board board = boardRepository.save(Board.builder()
			        .boardTitle(i+"번")
			        .member(member[k])
			        .placeCnt(1000+i*100)
			        .build());
		        
		        
		        Place place = placeRepository.save(Place.builder()
	        		.placeTitle("스타벅스 강남점")
	        		.placeContent("조용해요")
	        		.placeId("12345")
	        		.placeLat("37.506229157876774")
	        		.placeLng("127.05688880326076")
	        		.board(board)
	        		.member(member[k])
	        		.build());
	        }  
        }
    }
}