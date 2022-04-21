package com.momo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/loginProc")
			.usernameParameter("memEmail")
            .passwordParameter("memPassword")
			.defaultSuccessUrl("/")
		.and()
			.logout()
    		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 시 URL 재정의 
    		.logoutSuccessUrl("/member") // 로그아웃 성공 시 redirect 이동 
    		.invalidateHttpSession(true) // HTTP Session 초기화 
    		.deleteCookies("JSESSIONID") // 특정 쿠키 제거
    .and() // 403 예외처리 핸들링 
    	.exceptionHandling()
    		.accessDeniedPage("/denied")
    		
    .and()
			.csrf()
			.ignoringAntMatchers("/upload")
		    .ignoringAntMatchers("/placeList/*")
		    .ignoringAntMatchers("/deleteList/*")
		    .ignoringAntMatchers("/deletePlace/*")
		    .ignoringAntMatchers("/commentList/*")
		    .ignoringAntMatchers("/commentList02/*")
		    .ignoringAntMatchers("/test02/*")
		    .ignoringAntMatchers("/deleteComment/*")
		    .ignoringAntMatchers("/boardBookmark")
		    .ignoringAntMatchers("/unBoardBookmark");

		    	 
		
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
    	//web security 예외처리 해당 url은 인증이 없어도 접근 가능
        web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**"); 
    }
    
}





