package com.spring.moon.noticeBoard.model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class NoticeBoardVO {
	private int noticeNo;
	private int guestNo;
	private String n_title;
	private Timestamp n_regdate;
	private String n_content;
	private String userid;
	private String n_fileName;
	private long n_fileSize;
	
}
