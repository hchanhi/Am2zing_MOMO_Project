package com.momo.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memNum;

    @Column
    private String memEmail;

    @Column
    private String memNickName;

    @Column
    private String memPassword;

    @Column
    private Board memMbti;

    @Column
    private String memBirth;
    
    @Column
    private String memLevel; 
    
    @Column
    private String memImg;
	
	}
	




