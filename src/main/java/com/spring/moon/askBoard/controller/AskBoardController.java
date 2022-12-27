package com.spring.moon.askBoard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.ConstUtil;
import com.spring.moon.common.FileUploadUtil;
import com.spring.moon.common.PagingVO;
import com.spring.moon.common.UtilMgr;
import com.spring.moon.askBoard.model.AskBoardVO;
import com.spring.moon.askBoard.model.AskboardService;
import com.spring.moon.askBoard.model.CommentService;
import com.spring.moon.askBoard.model.CommentVO;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AskBoardController {
	private static final String SAVEFOLER = "C:/lecture/workspace_list/ezenGit_ws/FinalProj_Moon/src/main/resources/static/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;
	
	private static final Logger logger
	= LoggerFactory.getLogger(AskBoardController.class);

	@Autowired
	FileUploadUtil fileUploadUtil;

	@Autowired
	AskboardService askboardService;

	@Autowired
	GuestService guestService;
	
	@Autowired
	CommentService commentService;

//////////////////////////////G E T 방식 /////////////////////////////////////////////////////////////////////////////////////////	
	// 문의게시판 글쓰기 화면 보기
	@GetMapping("/askBoard/askWrite")
	public ModelAndView write(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String userid=(String)session.getAttribute("userid");
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
		System.out.println("글 목록 불러오기 map=" + map.get("keyword") +map.get("condition"));
		GuestVO g_vo = new GuestVO();
		if (userid != null && !userid.isEmpty()) {
			g_vo = guestService.selectByUserid(userid);
		}
		
		List<AskBoardVO> list = null;
		System.out.println("회원 or 관리자= "+g_vo.getSys());
		// 일반회원이라면 (sys==1)
		if (g_vo.getSys() == 1) {
			list = this.askboardService.selectByGuestno(g_vo.getGuestNo());
			// 관리자라면
		} else {
			list = this.askboardService.selectAll((String) map.get("condition"),(String) map.get("keyword"));
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

		int totalRecord = 0;
		if (list != null) {
			totalRecord = list.size();
		}
		int pageSize = 5;
		int blockSize = 10;
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);

		/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

		ModelAndView mav = new ModelAndView();

		mav.addObject("map", map);
		mav.addObject("list", list);
		mav.addObject("pageVo", pageVo);
		mav.addObject("g_vo", g_vo);

		mav.setViewName("askBoard/askBoardList");

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
	// 문의게시판 글수정 화면 보기
		@GetMapping("/askBoard/askEdit")
		public ModelAndView askEdit(HttpServletRequest request,@RequestParam int askNo) {
			
			HttpSession session = request.getSession();
			String userid=(String)session.getAttribute("userid");
			ModelAndView mav = new ModelAndView();
			AskBoardVO vo =  this.askboardService.selectByAskNo(askNo);
			mav.addObject("userid", userid);
			mav.addObject("vo", vo);
			mav.setViewName("askBoard/askEdit");

			return mav;
		}
		
		@GetMapping("/askBoard/commentsWrite")
		public ModelAndView commentsWrite(HttpServletRequest request) {			
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();
			String userid=(String)session.getAttribute("userid");
			
			mav.addObject("userid", userid);
			mav.setViewName("askBoard/commentsWrite");

			return mav;
		}
////////////////////////////// P O S T 방식 ///////////////////////////////////////////////////////////////////////////////////	
	@PostMapping("/askBoard/askWrite")
	public ModelAndView writePost(HttpServletRequest request, @RequestParam Map<String, Object> map)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		
		//파일 업로드 처리
		String fileName="", originFileName="";
		long fileSize=0;
		try {
			List<Map<String, Object>> fileList
			=fileUploadUtil.fileUpload(request, 
					ConstUtil.UPLOAD_FILE_FLAG);

			for(Map<String, Object> fileMap : fileList) {
				//다중 파일 업로드 처리 해야 함!

				originFileName=(String) fileMap.get("originalFileName");
				fileName=(String) fileMap.get("fileName");
				fileSize= (long) fileMap.get("fileSize");				
			}//for

			logger.info("파일 업로드 성공, fileName={}, fileSize={}", fileName,
					fileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String userid = (String) map.get("userid");
		GuestVO vo = guestService.selectByUserid(userid);

		AskBoardVO askBoardVO = new AskBoardVO();
		askBoardVO.setGuestNo(vo.getGuestNo());
		askBoardVO.setFileName(fileName);
		askBoardVO.setOriginalFileName(originFileName);
		askBoardVO.setFileSize(fileSize);
		askBoardVO.setA_title((String) map.get("a_title"));
		askBoardVO.setA_content((String) map.get("a_content"));

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
	// 문의게시판 글수정 화면 보기
	@PostMapping("/askBoard/askEdit")
	public ModelAndView askEditPost(HttpServletRequest request,@RequestParam Map<String,Object> map) {
		ModelAndView mav = new ModelAndView();
		
		String a_title = (String) map.get("a_title");
		String a_content = (String) map.get("a_content");
		String askno = (String) map.get("askno");

		AskBoardVO vo = new AskBoardVO();

		vo.setA_title(a_title);
		vo.setA_content(a_content);
		vo.setAskNo(Integer.parseInt(askno));

		int cnt = this.askboardService.updateAskboard(vo);
		String msg = "", url = "";
		if (cnt > 0) {
			msg = "문의글이 수정되었습니다.";
			url = "askDetail.jsp?askno="+askno;
		} else {
			msg = "문의글 수정 실패!";
			url = "javascript:history.back()";
		}
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
		return mav;
	}
	@PostMapping("/askBoard/commentsWrite")
	public ModelAndView commentsWritePost(HttpServletRequest request,@RequestParam Map<String,Object> map) {			
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid=(String)session.getAttribute("userid");

		GuestVO vo = this.guestService.selectByUserid(userid);

		String askNo = (String)map.get("askNo");
		String content = (String)map.get("c_content");

		CommentVO c_vo = new CommentVO();
		
		c_vo.setName(vo.getName());
		c_vo.setContent(content);
		c_vo.setAskno(Integer.parseInt(askNo));
		int cnt = this.commentService.insertcomment(c_vo);
		String msg = "", url = "";
		if (cnt > 0) {
			msg = "문의글이 수정되었습니다.";
			url = "/askBoard/askDetail?askno="+askNo;
		} else {
			msg = "답변 등록 실패!";
			url = "javascript:history.back()";
		}	
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");

		return mav;
	}
}
