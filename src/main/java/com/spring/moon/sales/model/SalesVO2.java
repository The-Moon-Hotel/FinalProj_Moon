package com.spring.moon.sales.model;

import lombok.Data;

@Data
public class SalesVO2 {
	private String branchName;
	private String checkInDate;
	private String roomType;
	private int roomRate;
	private long reservationCount;
	private long roomRevenue;
	private String facilityName;
	private long adults;
	private long children;
	private long adultRevenue;
	private long childRevenue;

	public long getFacilityRevenue() {
		return adultRevenue + childRevenue;
	}
}
