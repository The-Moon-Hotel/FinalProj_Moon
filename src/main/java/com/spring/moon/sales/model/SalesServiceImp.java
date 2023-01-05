package com.spring.moon.sales.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImp  implements SalesService{
	
	@Autowired
	private SalesDAO dao;
	
	public List<SalesVO2> salesByLocNo(String d1, String d2, String locName) {
		return this.dao.salesByLocNo(d1, d2, locName);
	}
	
	public List<SalesVO2> salesByFac(String d1, String d2, String locName) {
		return this.dao.salesByFac(d1, d2, locName);
	}
}
