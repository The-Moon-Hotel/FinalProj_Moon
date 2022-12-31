package com.spring.moon.guest.model;

import java.sql.Timestamp;

import lombok.Data;

public class GuestVO {
	private int guestNo;
	private String name;
	private String userid;
	private String pwd;
	private String email;
	private String email1;
	private String email2;
	private String tel;
	private String tel1;
	private String tel2;
	private String tel3;
	private Timestamp joindate;
	private Timestamp outdate;
	private int sys;

	public GuestVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public GuestVO(int guestNo, String name, String userid, String pwd, String email, String email1, String email2,
			String tel, String tel1, String tel2, String tel3, Timestamp joindate, Timestamp outdate, int sys) {
		super();
		this.guestNo = guestNo;
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.email = email;
		this.email1 = email1;
		this.email2 = email2;
		this.tel = tel;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.joindate = joindate;
		this.outdate = outdate;
		this.sys = sys;
	}


	public GuestVO(int guestNo, String name, String userid, String pwd, String email, String tel, Timestamp joindate,
			Timestamp outdate, int sys) {
		super();
		this.guestNo = guestNo;
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.email = email;
		this.tel = tel;
		this.joindate = joindate;
		this.outdate = outdate;
		this.sys = sys;
	}
	
	public GuestVO(String name, String userid, String pwd, String email, String tel) {
		super();
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.email = email;
		this.tel = tel;
	}


	public int getGuestNo() {
		return guestNo;
	}


	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmail1() {
		return email1;
	}


	public void setEmail1(String email1) {
		this.email1 = email1;
	}


	public String getEmail2() {
		return email2;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getTel1() {
		return tel1;
	}


	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}


	public String getTel2() {
		return tel2;
	}


	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}


	public String getTel3() {
		return tel3;
	}


	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}


	public Timestamp getJoindate() {
		return joindate;
	}


	public void setJoindate(Timestamp joindate) {
		this.joindate = joindate;
	}


	public Timestamp getOutdate() {
		return outdate;
	}


	public void setOutdate(Timestamp outdate) {
		this.outdate = outdate;
	}


	public int getSys() {
		return sys;
	}


	public void setSys(int sys) {
		this.sys = sys;
	}


	@Override
	public String toString() {
		return "GuestVO [guestNo=" + guestNo + ", name=" + name + ", userid=" + userid + ", pwd=" + pwd + ", email="
				+ email + ", email1=" + email1 + ", email2=" + email2 + ", tel=" + tel + ", tel1=" + tel1 + ", tel2="
				+ tel2 + ", tel3=" + tel3 + ", joindate=" + joindate + ", outdate=" + outdate + ", sys=" + sys + "]";
	}
	
}
