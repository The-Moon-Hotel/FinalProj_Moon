package com.spring.moon.reviewBoard.model;

import java.sql.Timestamp;

public class ReviewBoardVO {
	private int reviewNo;
	private int guestNo;
	private String r_title;
	private Timestamp r_regdate;
	private String r_content;
	private String userid;
	
	public ReviewBoardVO() {
		super();
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public Timestamp getR_regdate() {
		return r_regdate;
	}

	public void setR_regdate(Timestamp r_regdate) {
		this.r_regdate = r_regdate;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public ReviewBoardVO(int reviewNo, int guestNo, String r_title, Timestamp r_regdate, String r_content, String userid) {
		super();
		this.reviewNo = reviewNo;
		this.guestNo = guestNo;
		this.r_title = r_title;
		this.r_regdate = r_regdate;
		this.r_content = r_content;
		this.userid = userid;
	}
	public ReviewBoardVO( int guestNo, String r_title,  String r_content, String userid) {
		super();
		this.guestNo = guestNo;
		this.r_title = r_title;
		this.r_content = r_content;
		this.userid = userid;
	}
	
	
	
}
