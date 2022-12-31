package com.spring.moon.common;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utility {
	public static final String UPLOAD_DIR = "noticeUpload";
	public static final String TEST_DIR = "C:\\lecture";
	
	public static long calDate(String ci_date, String co_date) {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		long dateGap = 0;
		
		Date checkin, checkout;
		try {
			checkin = sdf.parse(ci_date);
			checkout = sdf.parse(co_date);
			
			dateGap 
			= (checkout.getTime()-checkin.getTime()) / (24*60*60*1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateGap;
	}
	
}
