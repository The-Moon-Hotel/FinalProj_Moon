package com.spring.moon.askBoard.model;

import java.sql.Timestamp;

import lombok.Data;


/*
 CREATE TABLE comments (
    no                number        primary key,    --번호
    name             varchar2(20)    not null,            --이름    
    regdate         date        default  sysdate,    --작성일
    content         varchar2(2000)         null,            --내용
    askno           number references askboard(askno) on delete cascade not null        --원본 게시판 글번호    
);

create sequence comments_seq
increment by 1
start with 1
nocache;
 */

@Data
public class CommentVO {
	private int no;
	private String name;
	private Timestamp regdate;
	private String content;
	private int askNo;
	
	
	
}
