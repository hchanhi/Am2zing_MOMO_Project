package com.momo.member;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.momo.domain.Member;

@RestController
public class OptionalControllerTest {

	@Autowired
	private MemberRepository userRepository;
	
	@GetMapping("/test/user/{id}")
	public Member 옵셔널_유저찾기(@PathVariable int id) {
//		Optional<User> userOptional = userRepository.findById(id);
//		User user;
//		if(userOptional.isPresent()) {
//			user = userOptional.get();
//		}else {
//			user = new User();
//		}
		
//		User user = userRepository.findById(id).orElseGet(()-> {
//				return User.builder().id(5).username("아무개").email("아무개@naver.com").build();
//		});

		Member member = userRepository.findById(id)
				.orElseThrow(()-> {

						return new NullPointerException("없어 값");
					
					
				});
		
		return member;
	}
}




