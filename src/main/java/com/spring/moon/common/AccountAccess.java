package com.spring.moon.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

@Configuration
public class AccountAccess implements WebMvcConfigurer, HandlerInterceptor {
	public static final String CURRENT_GUEST = "currentGuest";

	private final GuestService guestService;

	public AccountAccess(GuestService guestService) {
		this.guestService = guestService;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod method = (HandlerMethod) handler;
		AccountRequired access = method.getMethodAnnotation(AccountRequired.class);
		if (access == null) {
			access = AnnotatedElementUtils.findMergedAnnotation(method.getBeanType(), AccountRequired.class);
		}
		if (access == null) {
			return true;
		}

		GuestVO guest = currentGuest(request);
		if (guest == null) {
			response.sendRedirect(request.getContextPath() + "/login/login");
			return false;
		}
		if (access.admin() && guest.getSys() != GuestService.ADMIN_ACCOUNT) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 접근할 수 있습니다.");
			return false;
		}
		return true;
	}

	public GuestVO currentGuest(HttpServletRequest request) {
		GuestVO cachedGuest = (GuestVO) request.getAttribute(CURRENT_GUEST);
		if (cachedGuest != null) {
			return cachedGuest;
		}
		HttpSession session = request.getSession(false);
		String userid = session == null ? null : (String) session.getAttribute("userid");
		if (userid == null || userid.isEmpty()) {
			return null;
		}
		try {
			GuestVO guest = guestService.selectByUserid(userid);
			if (guest.getOutdate() == null) {
				request.setAttribute(CURRENT_GUEST, guest);
				return guest;
			}
		} catch (EmptyResultDataAccessException ignored) {
			// The account may have been removed while this session was active.
		}
		session.removeAttribute("userid");
		return null;
	}
}
