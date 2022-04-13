package com.momo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactController {
	
	@GetMapping("/api/hello")
	public String hello(){
	return "안녕하세요. Am2zing 팀의 프로젝트입니다.";
	}

}
