package com.momo.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM - Object Relation Mapping

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memId;
	private String memEmail;
	private String memPassword;
	private String memMbti;
	private String memBirth;
	private String memNickname;
	private String memImg;
	private String memRole; //ROLE_USER, ROLE_ADMIN
	// OAuth를 위해 구성한 추가 필드 2개
	private String provider;
	private String providerId;
	@CreationTimestamp
	private Timestamp createDate;
}
