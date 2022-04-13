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
public class Place implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeNum;

    @Column
    private String placeTitle;

    @Column
    private String memEmail;

    @Column
    private String placeContent;

    @Column
    private String BoardNum;

    @Column
    private String placeId;
    
    @Column
    private String placeLat; //위도
    
    @Column
    private String placeLng; //경도
    
    @Column
    private Blob placeImg;

    @Builder
	public Place(Long placeNum, String placeTitle, String memEmail, String placeContent, String BoardNum,
			String placeId, String placeLat, String placeLng, Blob placeImg) {
		super();
		this.placeNum = placeNum;
		this.placeTitle = placeTitle;
		this.memEmail = memEmail;
		this.placeContent = placeContent;
		this.BoardNum = BoardNum;
		this.placeId = placeId;
		this.placeLat = placeLat;
		this.placeLng = placeLng;
		this.placeImg = placeImg;
	
	}
	}

