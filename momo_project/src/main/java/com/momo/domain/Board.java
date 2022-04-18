package com.momo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.momo.comment.Comment;

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
public class Board implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private String boardTitle;

    
    @ManyToOne
    private Member member;
    
    @Column
    private int placeCnt;
    
    
  

  
    
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Comment> comments;
	
	}
	

	

