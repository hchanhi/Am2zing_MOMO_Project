package com.momo.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Member implements UserDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNum;


    @Column(nullable = false, unique = true)

    private String memEmail;

    @Column
    private String memNickName;

    @Column(nullable = false)
    private String memPassword;

    @Column
    private Board memMbti;

    @Column
    private String memBirth;
    
    @Column(nullable = false)
    private String memRoles; 
    
    @Column
    private String memImg;
    
    @Builder
    public Member(String memEmail, String memPassword, String memRoles) {
    	this.memEmail = memEmail;
    	this.memPassword = memPassword;
    	this.memRoles = memRoles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
    	Set<GrantedAuthority> roles = new HashSet<>();
    	for (String role : memRoles.split(",")) {
    		roles.add(new SimpleGrantedAuthority(role));
    	}
    	return roles;
    }
    @Override 
    public String getUsername() { 
    	return memEmail; 
    	} 
    
    @Override 
    public String getPassword() { 
    	return memPassword; }
    
    
    @Override 
    public boolean isAccountNonExpired() { 
    	return true; 
    	} 
    
    @Override 
    public boolean isAccountNonLocked() { 
    	return true; 
    	} 
    
    @Override 
    public boolean isCredentialsNonExpired() { 
    	return true; 
    	} 
    
    @Override 
    public boolean isEnabled() { 
    	return true; 
    	}
    



	
	}
	




