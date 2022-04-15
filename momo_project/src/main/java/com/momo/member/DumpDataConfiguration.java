package com.momo.member;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.momo.domain.Member;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DumpDataConfiguration implements CommandLineRunner{

    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!memberRepository.findById(1L).isPresent()) {
                  Member member1 = memberRepository.save(Member.builder()
                    .memEmail("admin@admin.com")
                    .memNickName("admin")
                    .memPassword("admin")
                    .memLevel("9")
                    .build());
                    }
           }
}