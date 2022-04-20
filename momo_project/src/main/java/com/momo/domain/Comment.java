package com.momo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class Comment {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyNum; 
	
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String replyContent; // 댓글 내용 
	
	/*
	 * @Column(name = "created_date")
	 * 
	 * @CreatedDate private String createdDate;
	 * 
	 * @Column(name = "modified_date")
	 * 
	 * @LastModifiedDate private String modifiedDate;
	 */

	 @ManyToOne
	 @JoinColumn(name = "board_num")
	  private Board board;
	 
	 @ManyToOne
	 private Member member;
	 /*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id") private User user; // 작성자
	 */
	


}
