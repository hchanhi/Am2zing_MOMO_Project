package com.momo.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Person {
	@Id // 4
    @GeneratedValue // 5
    private Long id;

    @Column // 6
    private String name;

    @Column
    private String password;
}
