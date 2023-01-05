package com.spring.moon.sales.model;

import java.util.List;

public interface SalesService {
	List<SalesVO2> salesByLocNo(String d1, String d2, String locName);
	
	List<SalesVO2> salesByFac(String d1, String d2, String locName);
}
