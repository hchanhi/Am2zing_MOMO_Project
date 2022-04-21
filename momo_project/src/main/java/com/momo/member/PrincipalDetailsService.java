package com.momo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.momo.domain.Member;




@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemEmail(memEmail);
		if(member == null) {
			return null;
		}else {
			return new PrincipalDetails(member);
		}
		
	}

}
