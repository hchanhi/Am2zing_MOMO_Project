package com.momo.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    @Column
    private String boardTitle;

    @Column
    private String memEmail;

    @Builder
	public Board(Long boardNum, String boardTitle, String memEmail) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.memEmail = memEmail;
	
	}
	}

