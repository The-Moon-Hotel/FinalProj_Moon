package com.spring.moon;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;
import com.spring.moon.sales.model.SalesService;

@SpringBootTest
@AutoConfigureMockMvc
class AccountAccessTests {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private GuestService guestService;

	@MockBean
	private SalesService salesService;

	@Test
	void administratorPageRequiresLogin() throws Exception {
		mvc.perform(get("/admin/Sales"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/login/login"));
	}

	@Test
	void memberCannotUseAdministratorRoutes() throws Exception {
		GuestVO member = new GuestVO();
		member.setSys(GuestService.GUEST_ACCOUNT);
		when(guestService.selectByUserid("member")).thenReturn(member);

		mvc.perform(get("/admin/Sales/").sessionAttr("userid", "member"))
				.andExpect(status().isForbidden());
		mvc.perform(post("/noticeBoard/noticeWrite").sessionAttr("userid", "member"))
				.andExpect(status().isForbidden());
		mvc.perform(post("/askBoard/commentWrite").sessionAttr("userid", "member"))
				.andExpect(status().isForbidden());
	}

	@Test
	void administratorCanOpenSalesPage() throws Exception {
		GuestVO administrator = new GuestVO();
		administrator.setSys(GuestService.ADMIN_ACCOUNT);
		when(guestService.selectByUserid("admin")).thenReturn(administrator);

		mvc.perform(get("/admin/Sales").sessionAttr("userid", "admin"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/Sales"));
	}

	@Test
	void salesFiltersKeepSelectionAndUseTheSelectedCategory() throws Exception {
		GuestVO administrator = new GuestVO();
		administrator.setSys(GuestService.ADMIN_ACCOUNT);
		when(guestService.selectByUserid("admin")).thenReturn(administrator);

		mvc.perform(get("/admin/Sales").sessionAttr("userid", "admin")
						.param("SearchBranchType", "Half Moon").param("SearchSalesType", "2")
						.param("searchDate1", "2026-11-10").param("searchDate2", "2026-11-01"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("branch", "Half Moon"))
				.andExpect(model().attribute("showRooms", false))
				.andExpect(model().attribute("startDate", "2026-11-01"));
		verify(salesService).salesByFac("2026-11-01", "2026-11-10", "Half Moon");
	}

	@Test
	void invalidSalesDateShowsFeedbackWithoutRunningAQuery() throws Exception {
		GuestVO administrator = new GuestVO();
		administrator.setSys(GuestService.ADMIN_ACCOUNT);
		when(guestService.selectByUserid("admin")).thenReturn(administrator);

		mvc.perform(get("/admin/Sales").sessionAttr("userid", "admin")
						.param("searchDate1", "2026-02-30").param("searchDate2", "2026-03-01"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("dateError"));
		verifyNoInteractions(salesService);
	}
}
