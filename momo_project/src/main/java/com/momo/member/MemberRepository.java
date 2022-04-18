package com.momo.member;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Member;



public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByMemEmail(String memEmail);
}
