package com.momo.domain;
//package com.momo.domain;
//
//import java.io.Serializable;
//import java.util.Collection;
//
//
//import javax.persistence.*;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Builder
//public class Member implements Serializable {
//
//    @Id
//    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long memNum;
//
//
//    @Column(nullable = false, unique = true)
//
//    private String memEmail;
//
//    @Column
//    private String memNickName;
//
//    @Column(nullable = false)
//    private String memPassword;
//
//    @Column
//    private String memMbti;
//
//    @Column
//    private String memBirth;
//    
//    @Enumerated(EnumType.STRING)
//    @Column
//    private Role memRoles; 
//    
//    @Column
//    private String memImg;
//    
//	}
//	
//
//
//
//
