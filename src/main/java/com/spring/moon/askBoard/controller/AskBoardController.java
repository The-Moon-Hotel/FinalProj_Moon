package com.spring.moon.askBoard.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.askBoard.model.AskBoardVO;
import com.spring.moon.askBoard.model.AskboardService;
import com.spring.moon.askBoard.model.CommentService;
import com.spring.moon.askBoard.model.CommentVO;
import com.spring.moon.common.AccountAccess;
import com.spring.moon.common.AccountRequired;
import com.spring.moon.common.ConstUtil;
import com.spring.moon.common.FileUploadUtil;
import com.spring.moon.common.PagingVO;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

@Controller
@AccountRequired
public class AskBoardController {
	private final FileUploadUtil fileUploadUtil;
	private final AskboardService askboardService;
	private final CommentService commentService;

	public AskBoardController(FileUploadUtil fileUploadUtil, AskboardService askboardService, CommentService commentService) {
		this.fileUploadUtil = fileUploadUtil;
		this.askboardService = askboardService;
		this.commentService = commentService;
	}

	@GetMapping("/askBoard/askWrite")
	public ModelAndView write() {
		return new ModelAndView("askBoard/askWrite");
	}

	@RequestMapping("/askBoard/askBoardList")
	public ModelAndView list(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest,
			@RequestParam Map<String, String> filters, @RequestParam(defaultValue = "1") int currentPage) {
		boolean admin = guest.getSys() == GuestService.ADMIN_ACCOUNT;
		List<AskBoardVO> inquiries = admin
				? askboardService.selectAll(filters.get("condition"), filters.get("keyword"))
				: askboardService.selectByGuestno(guest.getGuestNo());
		ModelAndView mav = new ModelAndView("askBoard/askBoardList");
		mav.addObject("list", inquiries);
		mav.addObject("map", filters);
		mav.addObject("admin", admin);
		mav.addObject("pageVo", new PagingVO(Math.max(1, currentPage), inquiries.size(), 5, 10));
		return mav;
	}

	@GetMapping("/askBoard/askDetail")
	public ModelAndView askDetail(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo) {
		AskBoardVO inquiry = requireInquiry(askNo);
		boolean admin = guest.getSys() == GuestService.ADMIN_ACCOUNT;
		boolean owner = inquiry.getGuestNo() == guest.getGuestNo();
		if (!admin && !owner) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		ModelAndView mav = new ModelAndView("askBoard/askDetail", "vo", inquiry);
		mav.addObject("admin", admin);
		mav.addObject("owner", owner);
		return mav;
	}

	@GetMapping("/askBoard/askEdit")
	public ModelAndView askEdit(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo) {
		return new ModelAndView("askBoard/askEdit", "vo", requireOwnInquiry(guest, askNo));
	}

	@Transactional
	@PostMapping("/askBoard/askDelete")
	public String askDelete(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo) {
		requireOwnInquiry(guest, askNo);
		commentService.deleteAskNoComment(askNo);
		askboardService.deleteAskboard(askNo);
		return "redirect:/askBoard/askBoardList";
	}

	@AccountRequired(admin = true)
	@GetMapping("/askBoard/commentsWrite")
	public ModelAndView commentsWrite(@RequestParam int askNo) {
		requireInquiry(askNo);
		return new ModelAndView("askBoard/commentsWrite", "askNo", askNo);
	}

	@GetMapping("/askBoard/commentsList")
	public ModelAndView commentsList(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo) {
		if (guest.getSys() != GuestService.ADMIN_ACCOUNT) {
			requireOwnInquiry(guest, askNo);
		} else {
			requireInquiry(askNo);
		}
		ModelAndView mav = new ModelAndView("askBoard/commentsList", "list", commentService.selectComment(askNo));
		mav.addObject("askNo", askNo);
		mav.addObject("admin", guest.getSys() == GuestService.ADMIN_ACCOUNT);
		return mav;
	}

	@AccountRequired(admin = true)
	@PostMapping("/askBoard/commentDelete")
	public String commentsDelete(@RequestParam int no, @RequestParam int askNo) {
		if (commentService.deleteComment(no, askNo) == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return "redirect:/askBoard/askDetail?askNo=" + askNo;
	}

	@PostMapping("/askBoard/askWrite")
	public String writePost(HttpServletRequest request, @RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest,
			@RequestParam("a_title") String title, @RequestParam("a_content") String content) throws IOException {
		AskBoardVO inquiry = new AskBoardVO();
		inquiry.setGuestNo(guest.getGuestNo());
		inquiry.setA_title(title);
		inquiry.setA_content(content);
		if (request instanceof MultipartHttpServletRequest) {
			for (Map<String, Object> file : fileUploadUtil.fileUpload(request, ConstUtil.UPLOAD_FILE_FLAG)) {
				inquiry.setFileName((String) file.get("fileName"));
				inquiry.setOriginalFileName((String) file.get("originalFileName"));
				inquiry.setFileSize((long) file.get("fileSize"));
			}
		}
		askboardService.insert(inquiry);
		return "redirect:/askBoard/askBoardList";
	}

	@PostMapping("/askBoard/askEdit")
	public String askEditPost(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo,
			@RequestParam("a_title") String title, @RequestParam("a_content") String content) {
		AskBoardVO inquiry = requireOwnInquiry(guest, askNo);
		inquiry.setA_title(title);
		inquiry.setA_content(content);
		askboardService.updateAskboard(inquiry);
		return "redirect:/askBoard/askDetail?askNo=" + askNo;
	}

	@AccountRequired(admin = true)
	@PostMapping("/askBoard/commentWrite")
	public String commentsWritePost(@RequestAttribute(AccountAccess.CURRENT_GUEST) GuestVO guest, @RequestParam int askNo,
			@RequestParam("c_content") String content) {
		requireInquiry(askNo);
		CommentVO reply = new CommentVO();
		reply.setName(guest.getName());
		reply.setContent(content);
		reply.setAskNo(askNo);
		commentService.insertcomment(reply);
		return "redirect:/askBoard/askDetail?askNo=" + askNo;
	}

	private AskBoardVO requireInquiry(int askNo) {
		try {
			return askboardService.selectByAskNo(askNo);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	private AskBoardVO requireOwnInquiry(GuestVO guest, int askNo) {
		AskBoardVO inquiry = requireInquiry(askNo);
		if (inquiry.getGuestNo() != guest.getGuestNo()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return inquiry;
	}
}
