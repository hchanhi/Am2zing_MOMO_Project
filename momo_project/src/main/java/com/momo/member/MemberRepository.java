package com.momo.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.momo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
