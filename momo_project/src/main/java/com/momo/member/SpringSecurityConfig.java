	package com.momo.member;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.builders.WebSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;
	
	@Configuration
	@EnableWebSecurity
	@AllArgsConstructor
	public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	    
		private MemberService memberService;
		
		// 비밀번호 암호화 Bean
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	    
		/**
		 * Security 설정 
		 * @param web FilterChainProxy 생성 필터 
		 * @throws Exception 
		 */
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	           http.authorizeRequests()
	                    .antMatchers("/admin").hasRole("ADMIN") // admin role 를 가지고 있어야만 해당 페이지에 접근
	                    .anyRequest().permitAll() // 어떤한 URI로 접근하든 인증 필요 설정
	                 // .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
	                .and()
	                    .formLogin() // 폼방식 로그인 사용
	              			.loginPage("/Member/login") // 로그인 페이지를 커스텀 페이지로 변경 -> 이경우 web security 예외처리 필수 (css, 이미지등 깨짐)
	                    	.loginProcessingUrl("/loginProc") // form에서 action="loginProc" 으로 주면 알아서 id 와 pw를 넘김
		                    .usernameParameter("memEmail")
		                    .passwordParameter("memPassword")
		                    .failureUrl("/login-error") // 로그인 실패 시 이동
		                    .defaultSuccessUrl("/index", true) // 로그인 성공시 이동할 페이지
		                    .permitAll()
	                .and()
	                	.logout()
	                		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 시 URL 재정의 
	                		.logoutSuccessUrl("/") // 로그아웃 성공 시 redirect 이동 
	                		.invalidateHttpSession(true) // HTTP Session 초기화 
	                		.deleteCookies("JSESSIONID") // 특정 쿠키 제거
	                .and() // 403 예외처리 핸들링 
	                	.exceptionHandling().accessDeniedPage("/denied");
	    }
	
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	    	//web security 예외처리 해당 url은 인증이 없어도 접근 가능
	        web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**"); 
	    }
	    
	    /*
	     * Spring Security 인증 
	     * AuthenticationManagerBuilder를 사용하여 AuthenticationManager 생성 
	     * @param auth
	     * @throws Exception 
	     */
	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	    }
	}