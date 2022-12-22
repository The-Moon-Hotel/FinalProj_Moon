package com.spring.moon.askBoard.model;

import java.sql.Timestamp;

public class AskBoardVO {
	private int askNo;
	private int guestNo;
	private String a_title;
	private String a_content;
	private Timestamp a_regdate;

	public AskBoardVO() {
		super();
	}

	public AskBoardVO(int askNo, int guestNo, String a_title, String a_content, Timestamp a_regdate) {
		super();
		this.askNo = askNo;
		this.guestNo = guestNo;
		this.a_title = a_title;
		this.a_content = a_content;
		this.a_regdate = a_regdate;
	}

	public int getAskNo() {
		return askNo;
	}

	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public String getA_title() {
		return a_title;
	}

	public void setA_title(String a_title) {
		this.a_title = a_title;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public Timestamp getA_regdate() {
		return a_regdate;
	}

	public void setA_regdate(Timestamp a_regdate) {
		this.a_regdate = a_regdate;
	}

	@Override
	public String toString() {
		return "AskBoardVO [askNo=" + askNo + ", guestNo=" + guestNo + ", a_title=" + a_title + ", a_content=" + a_content
				+ ", a_regdate=" + a_regdate + "]";
	}
	

}
