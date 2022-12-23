package com.spring.moon.askBoard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.PagingVO;
import com.spring.moon.common.UtilMgr;
import com.spring.moon.askBoard.model.AskBoardVO;
import com.spring.moon.askBoard.model.AskboardService;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AskBoardController {
	private static final String SAVEFOLER = "C:/lecture/workspace_list/ezenGit_ws/FinalProj_Moon/src/main/resources/static/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;

	@Autowired
	AskboardService askboardService;

	@Autowired
	GuestService guestService;

//////////////////////////////G E T 방식 /////////////////////////////////////////////////////////////////////////////////////////	
	// 문의게시판 글쓰기 화면 보기
	@GetMapping("/askBoard/askWrite")
	public ModelAndView write(HttpServletRequest request, @RequestParam String userid) {
		/*
		 * HttpSession session = request.getSession();
		 * session.setAttribute("userid",userid);
		 */
		ModelAndView mav = new ModelAndView();

		mav.addObject("userid", userid);
		mav.setViewName("askBoard/askWrite");

		return mav;
	}

	// 글 목록 불러오기
	@RequestMapping(value = "/askBoard/askBoardList")
	public ModelAndView list(HttpServletRequest request, @RequestParam Map<String, Object> map,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		// System.out.println("글 목록 불러오기 map=" + map.get("keyWord") +
		// map.get("keyField"));
		GuestVO g_vo = new GuestVO();
		if (userid != null && !userid.isEmpty()) {
			g_vo = guestService.selectByUserid(userid);
		}
		List<AskBoardVO> list = null;

		// 일반회원이라면 (sys==1)
		if (g_vo.getSys() == 1) {
			list = this.askboardService.selectByGuestno(g_vo.getGuestNo());
			// 관리자라면
		} else {
			list = this.askboardService.selectAll((String) map.get("keyword"), (String) map.get("condition"));
		}
		/*
		 * for (int i = 0; i < list.size(); i++) { System.out.println((String)
		 * list.get(i).get("uName")); }
		 */
		// 게시판 검색 관련소스
		String keyField = ""; // DB의 컬럼명
		String keyWord = ""; // DB의 검색어

		if (map.get("keyWord") != null) {
			keyField = (String) map.get("keyField");
			keyWord = (String) map.get("keyWord");
		}
		/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
		// paging 처리 관련

		int currentPage = 1;
		String strCurPage = request.getParameter("currentPage");
		if (strCurPage != null) {
			currentPage = Integer.parseInt(strCurPage);
		}

		int totalRecord = 0;
		if (list != null) {
			totalRecord = list.size();
		}
		int pageSize = 5;
		int blockSize = 10;
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);

		/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

		ModelAndView mav = new ModelAndView();

		mav.addObject("list", list);
		mav.addObject("pagingVo", pagingVo);
		mav.addObject("g_vo", g_vo);

		mav.setViewName("/askBoard/askBoardList");

		return mav;

	}

	@GetMapping("/askBoard/askDetail")
	public ModelAndView askDetail(HttpServletRequest request, @RequestParam int no) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		GuestVO g_vo = guestService.selectByUserid(userid);

		AskBoardVO vo = this.askboardService.selectByAskNo(no);

		mav.addObject("sys", g_vo.getSys());
		mav.addObject("vo", vo);
		mav.setViewName("askBoard/askDetail");
		return mav;
	}

////////////////////////////// P O S T 방식 ///////////////////////////////////////////////////////////////////////////////////	
	@PostMapping("/askBoard/askWrite")
	public ModelAndView writePost(HttpServletRequest request, @RequestParam Map<String, Object> map)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		/*
		 * MultipartRequest multi = null;
		 * 
		 * int fileSize = 0; String fileName = null; File file = new File(SAVEFOLER);
		 * 
		 * if (!file.exists()) { file.mkdirs(); }
		 * 
		 * multi = new MultipartRequest(request, SAVEFOLER, maxSize, encType, new
		 * DefaultFileRenamePolicy());
		 * 
		 * if (multi.getFilesystemName("fileName") != null) { fileName =
		 * multi.getFilesystemName("fileName"); fileSize = (int)
		 * multi.getFile("fileName").length();
		 * 
		 * }
		 * 
		 * String a_title = multi.getParameter("a_title"); String a_content =
		 * multi.getParameter("a_content");
		 * 
		 * if (multi.getParameter("contentType").equalsIgnoreCase("TEXT")) { //
		 * ignoreCase, 대소문자 무시, tExt == TEXT => true a_content =
		 * UtilMgr.replace(a_content, "<", "&lt;"); // a1, a2, a3 // 입력값 가정 =>
		 * ABC<p>가나다</p> // UtilMgr.replace( ) 실행 후 content에 저장되는 값 ABC&lt;p>가나다&lt;p> }
		 */
		String userid = (String) map.get("userid");
		GuestVO vo = guestService.selectByUserid(userid);

		AskBoardVO askBoardVO = new AskBoardVO();
		askBoardVO.setGuestNo(vo.getGuestNo());
		// askBoardVO.setA_title(a_title);
		// askBoardVO.setA_content(a_content);
		askBoardVO.setA_title((String) map.get("a_title"));
		askBoardVO.setA_content((String) map.get("a_content"));
		;
		askBoardVO.setA_regdate((Timestamp) map.get("a_regdate"));

		int cnt = askboardService.insert(askBoardVO);

		String msg = "", url = "";
		if (cnt > 0) {
			msg = "글이 등록되었습니다.";
			url = "/askBoard/askBoardList";
		} else {
			msg = "글 등록 실패!";
			url = "javascript:history.back()";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}
}
