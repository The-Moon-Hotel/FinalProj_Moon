package com.spring.moon.askBoard.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AskBoardVO {
	private String name;
	private int no;
	private int askNo;
	private int guestNo;
	private String a_title;
	private String a_content;
	private Timestamp a_regdate;
	
	//자료실 추가
	private String fileName;
	private long fileSize; 
	private int downCount;
    private String originalFileName; 
}
