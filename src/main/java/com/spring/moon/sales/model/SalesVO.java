package com.spring.moon.sales.model;

import java.sql.Date;

public class SalesVO {
	private String locName; //지점명
	private String roomType; //객실 타입
	private int reservNo;	//예약번호
	private int guestNo;	//회원번호
	private int roomNo; //객실번호
	private int facNo;//부대시설예약번호
	private int adult;//어른수
	private int kids;//어린이수
	private String ci_date;//체크인날짜
	private String co_date;//체크아웃날짜
	private int roomPrice;//객실 이용가격
	private int facInfoNo;	//부대시설정보번호
	private int fac_adultNo; //부대시설 이용 성인수
	private int fac_kidsNo;	//부대시설 이용 어린이수
	private int fac_Adult_Price;//부대시설 이용 성인 요금
	private int fac_kids_Price;//부대시설 이용 어린이 요금
	private int fac_total_Price;//부대시설 이용 요금
	private int totalAmount;//객실 이용가격+부대시설이용요금(totalPrice+fac_total_Price)
	
	public SalesVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SalesVO(String locName, String roomType, int reservNo, int guestNo, int roomNo, int facNo, int adult,
			int kids, String ci_date, String co_date, int totalPrice, int facInfoNo, int fac_adultNo, int fac_kidsNo,
			int fac_Adult_Price, int fac_kids_Price, int fac_total_Price, int totalAmount) {
		super();
		this.locName = locName;
		this.roomType = roomType;
		this.reservNo = reservNo;
		this.guestNo = guestNo;
		this.roomNo = roomNo;
		this.facNo = facNo;
		this.adult = adult;
		this.kids = kids;
		this.ci_date = ci_date;
		this.co_date = co_date;
		this.roomPrice = roomPrice;
		this.facInfoNo = facInfoNo;
		this.fac_adultNo = fac_adultNo;
		this.fac_kidsNo = fac_kidsNo;
		this.fac_Adult_Price = fac_Adult_Price;
		this.fac_kids_Price = fac_kids_Price;
		this.fac_total_Price = fac_total_Price;
		this.totalAmount = totalAmount;
	}
	//부대시설 이용요금 합계
	
	
	
	
	

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getReservNo() {
		return reservNo;
	}

	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}

	public int getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getFacNo() {
		return facNo;
	}

	public void setFacNo(int facNo) {
		this.facNo = facNo;
	}

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getKids() {
		return kids;
	}

	public void setKids(int kids) {
		this.kids = kids;
	}

	public String getCi_date() {
		return ci_date;
	}

	public void setCi_date(String ci_date) {
		this.ci_date = ci_date;
	}

	public String getCo_date() {
		return co_date;
	}

	public void setCo_date(String co_date) {
		this.co_date = co_date;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getFacInfoNo() {
		return facInfoNo;
	}

	public void setFacInfoNo(int facInfoNo) {
		this.facInfoNo = facInfoNo;
	}

	public int getFac_adultNo() {
		return fac_adultNo;
	}

	public void setFac_adultNo(int fac_adultNo) {
		this.fac_adultNo = fac_adultNo;
	}

	public int getFac_kidsNo() {
		return fac_kidsNo;
	}

	public void setFac_kidsNo(int fac_kidsNo) {
		this.fac_kidsNo = fac_kidsNo;
	}

	public int getFac_Adult_Price() {
		return fac_Adult_Price;
	}

	public void setFac_Adult_Price(int fac_Adult_Price) {
		this.fac_Adult_Price = fac_Adult_Price;
	}

	public int getFac_kids_Price() {
		return fac_kids_Price;
	}

	public void setFac_kids_Price(int fac_kids_Price) {
		this.fac_kids_Price = fac_kids_Price;
	}

	public int getFac_total_Price() {
		return fac_total_Price;
	}

	public void setFac_total_Price(int fac_total_Price) {
		this.fac_total_Price = fac_total_Price;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	
	
	
	
}
