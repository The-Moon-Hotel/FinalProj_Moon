package com.spring.moon.askBoard.model;

import java.sql.Timestamp;


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


public class CommentVO {
	private int no;
	private String name;
	private Timestamp regdate;
	private String content;
	private int askno;
	
	
	public CommentVO() {
		super();
	}


	public CommentVO(int no, String name, Timestamp regdate, String content, int askno) {
		super();
		this.no = no;
		this.name = name;
		this.regdate = regdate;
		this.content = content;
		this.askno = askno;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getAskno() {
		return askno;
	}


	public void setAskno(int askno) {
		this.askno = askno;
	}


	@Override
	public String toString() {
		return "CommentVO [no=" + no + ", name=" + name + ", regdate=" + regdate + ", content="
				+ content + ", askno=" + askno + "]";
	}
	
	
	
}
