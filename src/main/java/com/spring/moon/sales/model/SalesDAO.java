package com.spring.moon.sales.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SalesDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<SalesVO2> salesByLocNo(String d1, String d2, String locName) {

		String sql = "select locname, roomtype, roomprice, ci_date,"
				+ " count(*) as quantity, sum(roomPrice) as room_total_Price" + " from v_room_sales"
				+ " where ci_date between '"+d1+"' and  '"+d2+"'";

		if (!locName.equals("all")) {
			sql += " and locname =  '"+locName+"' ";
		}

		sql += " group by locname, roomtype,  roomprice, ci_date" + " order by locname, ci_date ";
		List<SalesVO2> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SalesVO2>(SalesVO2.class));
		return list;
	}

	public List<SalesVO2> salesByFac(String d1, String d2, String locName) {
		String sql = "select locname, facname, sum(adultno) as fac_adultNo, sum(kidsno) as fac_kidsNo,"
				+ " sum(adultprice) as fac_Adult_Price, sum(kidsprice) as fac_kids_Price, ci_date from v_fac_sales"
				+ " where ci_date between '"+d1+"' and  '"+d2+"'";

		if (!locName.equals("all")) {
			sql += " and locname =  '"+locName+"' ";
		}

		sql += " group by locname, facname, ci_date order by locname, ci_date ";
		List<SalesVO2> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SalesVO2>(SalesVO2.class));
		return list;
	}

}
