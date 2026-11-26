package com.spring.moon.sales.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.AccountRequired;
import com.spring.moon.sales.model.SalesService;
import com.spring.moon.sales.model.SalesVO2;

@Controller
@AccountRequired(admin = true)
public class SalesController {
	private static final List<String> BRANCHES = List.of("Full Moon", "Half Moon", "Crescent Moon");
	private final SalesService salesService;

	public SalesController(SalesService salesService) {
		this.salesService = salesService;
	}

	@RequestMapping(value = "/admin/Sales", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sales(@RequestParam Map<String, String> filters) {
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
		LocalDate monthStart = today.withDayOfMonth(1);
		String branch = filters.getOrDefault("SearchBranchType", "all");
		if (!BRANCHES.contains(branch)) {
			branch = "all";
		}
		String category = filters.getOrDefault("SearchSalesType", "3");
		if (!List.of("1", "2", "3").contains(category)) {
			category = "3";
		}
		boolean showRooms = !"2".equals(category);
		boolean showFacilities = !"1".equals(category);
		String startInput = filters.getOrDefault("searchDate1", "");
		String endInput = filters.getOrDefault("searchDate2", "");
		if (startInput.isBlank() && endInput.isBlank()) {
			startInput = monthStart.toString();
			endInput = today.toString();
		}

		ModelAndView view = new ModelAndView("admin/Sales");
		view.addObject("branches", BRANCHES);
		view.addObject("branch", branch);
		view.addObject("branchLabel", "all".equals(branch) ? "전체 지점" : branch);
		view.addObject("category", category);
		view.addObject("showRooms", showRooms);
		view.addObject("showFacilities", showFacilities);
		view.addObject("startDate", startInput);
		view.addObject("endDate", endInput);
		view.addObject("presets", List.of(
				preset("최근 7일", today.minusDays(6), today),
				preset("이번 달", monthStart, today),
				preset("지난달", monthStart.minusMonths(1), monthStart.minusDays(1))));

		LocalDate start;
		LocalDate end;
		try {
			start = LocalDate.parse(startInput);
			end = LocalDate.parse(endInput);
		} catch (DateTimeParseException e) {
			view.addObject("dateError", "시작일과 종료일을 올바르게 입력해 주세요.");
			return view;
		}
		if (start.isAfter(end)) {
			LocalDate earlier = end;
			end = start;
			start = earlier;
		}
		view.addObject("startDate", start.toString());
		view.addObject("endDate", end.toString());

		List<SalesVO2> rooms = showRooms ? salesService.salesByLocNo(start.toString(), end.toString(), branch) : List.of();
		List<SalesVO2> facilities = showFacilities ? salesService.salesByFac(start.toString(), end.toString(), branch) : List.of();
		Map<String, BranchRevenue> branchRevenue = new LinkedHashMap<>();
		for (String name : "all".equals(branch) ? BRANCHES : List.of(branch)) {
			branchRevenue.put(name, new BranchRevenue());
		}
		long roomRevenue = 0;
		long reservationCount = 0;
		for (SalesVO2 room : rooms) {
			branchRevenue.computeIfAbsent(room.getBranchName(), name -> new BranchRevenue()).rooms += room.getRoomRevenue();
			roomRevenue += room.getRoomRevenue();
			reservationCount += room.getReservationCount();
		}
		long facilityRevenue = 0;
		long visitorCount = 0;
		for (SalesVO2 facility : facilities) {
			branchRevenue.computeIfAbsent(facility.getBranchName(), name -> new BranchRevenue()).facilities += facility.getFacilityRevenue();
			facilityRevenue += facility.getFacilityRevenue();
			visitorCount += facility.getAdults() + facility.getChildren();
		}
		long total = roomRevenue + facilityRevenue;
		view.addObject("rooms", rooms);
		view.addObject("facilities", facilities);
		view.addObject("roomRevenue", roomRevenue);
		view.addObject("facilityRevenue", facilityRevenue);
		view.addObject("totalRevenue", total);
		view.addObject("reservationCount", reservationCount);
		view.addObject("visitorCount", visitorCount);
		view.addObject("roomShare", percentage(roomRevenue, total));
		view.addObject("facilityShare", total == 0 ? 0 : 100 - percentage(roomRevenue, total));
		view.addObject("branchChart", chartRows(branchRevenue));
		return view;
	}

	private static Map<String, String> preset(String label, LocalDate start, LocalDate end) {
		return Map.of("label", label, "start", start.toString(), "end", end.toString());
	}

	private static List<Map<String, Object>> chartRows(Map<String, BranchRevenue> revenue) {
		long largest = revenue.values().stream().mapToLong(BranchRevenue::total).max().orElse(0);
		List<Map<String, Object>> rows = new ArrayList<>();
		revenue.forEach((branch, amount) -> rows.add(Map.of(
				"label", branch, "total", amount.total(), "width", percentage(amount.total(), largest),
				"roomShare", percentage(amount.rooms, amount.total()))));
		return rows;
	}

	private static int percentage(long part, long total) {
		return total > 0 ? (int) Math.round(100.0 * Math.max(0, Math.min(part, total)) / total) : 0;
	}

	private static class BranchRevenue {
		long rooms;
		long facilities;

		long total() {
			return rooms + facilities;
		}
	}
}
