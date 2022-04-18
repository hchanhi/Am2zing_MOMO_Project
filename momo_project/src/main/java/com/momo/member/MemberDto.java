package com.momo.member;

import com.momo.domain.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
	private String memEmail;
	private String memPassword;
	private String memAuth;
	
	public Member toEntity() {
		return Member.builder()
				.memEmail(memEmail)
				.memPassword(memPassword)
				.memRoles(memAuth)
				.build();
	}

	@Builder
	public MemberDto(String memEmail, String memPassword, String memAuth) {
		this.memEmail = memEmail;
		this.memPassword = memPassword;
		this.memAuth = memAuth;
	}
	
}
