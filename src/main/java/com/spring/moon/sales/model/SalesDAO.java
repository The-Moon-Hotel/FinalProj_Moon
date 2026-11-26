package com.spring.moon.sales.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SalesDAO {
	private final JdbcTemplate jdbcTemplate;

	public SalesDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<SalesVO2> salesByLocNo(String from, String to, String branch) {
		String sql = "select m.locName as branch_name, r.ci_date as check_in_date, m.roomType as room_type,"
				+ " m.roomPrice as room_rate, count(*) as reservation_count, sum(r.totalPrice) as room_revenue"
				+ " from reservation r join room m on m.roomNo = r.roomNo"
				+ " where r.ci_date between ? and ?";
		String grouping = " group by r.ci_date, m.locName, m.roomType, m.roomPrice"
				+ " order by r.ci_date desc, m.locName, m.roomType";
		return queryByBranch(sql, grouping, from, to, branch);
	}

	public List<SalesVO2> salesByFac(String from, String to, String branch) {
		String sql = "select m.locName as branch_name, r.ci_date as check_in_date, i.facName as facility_name,"
				+ " sum(coalesce(f.adultNo, 0)) as adults, sum(coalesce(f.kidsNo, 0)) as children,"
				+ " sum(coalesce(f.adultNo, 0) * i.adultPrice) as adult_revenue,"
				+ " sum(coalesce(f.kidsNo, 0) * i.kidsPrice) as child_revenue"
				+ " from facility f join facilityinfo i on i.facInfoNo = f.facInfoNo"
				+ " join reservation r on r.reservNo = f.reservNo join room m on m.roomNo = r.roomNo"
				+ " where r.ci_date between ? and ?";
		String grouping = " group by r.ci_date, m.locName, i.facName"
				+ " having sum(coalesce(f.adultNo, 0) + coalesce(f.kidsNo, 0)) > 0"
				+ " order by r.ci_date desc, m.locName, i.facName";
		return queryByBranch(sql, grouping, from, to, branch);
	}

	private List<SalesVO2> queryByBranch(String sql, String grouping, String from, String to, String branch) {
		List<Object> parameters = new ArrayList<>(List.of(from, to));
		if (!"all".equals(branch)) {
			sql += " and m.locName = ?";
			parameters.add(branch);
		}
		return jdbcTemplate.query(sql + grouping, new BeanPropertyRowMapper<>(SalesVO2.class), parameters.toArray());
	}
}
