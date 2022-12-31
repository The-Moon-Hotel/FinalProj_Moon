package com.spring.moon.reservRoom.model;

import lombok.Data;

@Data
public class ReservationVO {
	private int reservNo;
	private int guestNo;
	private int roomNo;
	private int adult;
	private int kids;
	private String ci_date;
	private String co_date;
	private int totalPrice;
}
