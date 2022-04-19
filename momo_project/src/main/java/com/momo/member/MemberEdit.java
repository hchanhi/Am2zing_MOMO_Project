package com.momo.member;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberEdit {
	
	    @NotEmpty(message = "닉네임은 필수입니다.")
	    private String memMickname;
	    
	
}
