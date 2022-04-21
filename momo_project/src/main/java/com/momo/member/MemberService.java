package com.momo.member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

	
	 @Autowired
	 private MemberRepository memberRepository;

	 //이메일 중복 검사
	 public HashMap<String, Object> memEmailOverlap(String memEmail) {
	        HashMap<String, Object> map = new HashMap<>();
	        map.put("result", memberRepository.existsByMemEmail(memEmail));
	        return map;
	    }

	    //닉네임 중복 검사
	    public HashMap<String, Object> memNicknameOverlap(String memNickname) {
	        HashMap<String, Object> map = new HashMap<>();
	        map.put("result", memberRepository.existsByMemNickname(memNickname));
	        return map;
	    }
}
