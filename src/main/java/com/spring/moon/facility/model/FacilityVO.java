package com.spring.moon.facility.model;

public class FacilityVO {
	private int facNo; 
	private int reservNo;
	private int facInfoNo;
	private int adultNo; 
	private int kidsNo;
	
	public FacilityVO() {
		super();
	}

	public FacilityVO(int facNo, int reservNo, int facInfoNo, int adultNo, int kidsNo) {
		super();
		this.facNo = facNo;
		this.reservNo = reservNo;
		this.facInfoNo = facInfoNo;
		this.adultNo = adultNo;
		this.kidsNo = kidsNo;
	}

	public int getFacNo() {
		return facNo;
	}

	public void setFacNo(int facNo) {
		this.facNo = facNo;
	}

	public int getReservNo() {
		return reservNo;
	}

	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}

	public int getFacInfoNo() {
		return facInfoNo;
	}

	public void setFacInfoNo(int facInfoNo) {
		this.facInfoNo = facInfoNo;
	}

	public int getAdultNo() {
		return adultNo;
	}

	public void setAdultNo(int adultNo) {
		this.adultNo = adultNo;
	}

	public int getKidsNo() {
		return kidsNo;
	}

	public void setKidsNo(int kidsNo) {
		this.kidsNo = kidsNo;
	}

	@Override
	public String toString() {
		return "FacilityVO [facNo=" + facNo + ", reservNo=" + reservNo + ", facInfoNo=" + facInfoNo + ", adultNo="
				+ adultNo + ", kidsNo=" + kidsNo + "]";
	} 
}
