package com.spring.moon.reviewBoard.controller;

import java.io.IOException;
import java.text.ParseException;
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

import com.spring.moon.askBoard.controller.AskBoardController;
import com.spring.moon.common.ConstUtil;
import com.spring.moon.common.FileUploadUtil;
import com.spring.moon.common.PagingVO;
import com.spring.moon.guest.model.GuestService;
import com.spring.moon.guest.model.GuestVO;
import com.spring.moon.reviewBoard.model.ReviewBoardService;
import com.spring.moon.reviewBoard.model.ReviewBoardVO;

@Controller
public class ReviewBoardController {

	private static final Logger logger
	= LoggerFactory.getLogger(AskBoardController.class);
	
	@Autowired
	GuestService guestService;
	@Autowired
	FileUploadUtil fileUploadUtil;
	@Autowired
	ReviewBoardService reviewBoardService; 
	
	@GetMapping("/reviewBoard/reviewWrite") 
	public ModelAndView reviewWrite(HttpServletRequest request) throws ParseException { 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			
			GuestVO guestVo = guestService.selectByUserid(userid);
			int guestNo = guestVo.getGuestNo();
			
			mav.addObject("guestNo", guestNo);

			mav.setViewName("reviewBoard/reviewWrite");
		}
		return mav;
	}

	@RequestMapping(value = "/reviewBoard/reviewBoardList")
	public ModelAndView reviewBoardList(HttpServletRequest request, @RequestParam Map<String, Object> map,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		List<ReviewBoardVO> list = reviewBoardService.selectAll((String) map.get("condition"), (String) map.get("keyword"));
		
		// 게시판 검색 관련소스
		String keyField = ""; // DB의 컬럼명
		String keyWord = ""; // DB의 검색어
		
		if (map.get("keyWord") != null) {
			keyField = (String) map.get("condition");
			keyWord = (String) map.get("keyword");
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


		mav.addObject("map", map);
		mav.addObject("list", list);
		mav.addObject("pageVo", pageVo);

		mav.setViewName("reviewBoard/reviewBoardList");

		
		return mav;
	}
	@GetMapping("/reviewBoard/detail") 
	public ModelAndView detail(HttpServletRequest request, @RequestParam String no){ 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (no == null) {
			String msg = "잘못된 url입니다.", url = "/reviewBoard/reviewBoardList";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			int gno =0;
			GuestVO guestVo = new GuestVO();
			
			if (userid != null && !userid.isEmpty()) {
				guestVo = guestService.selectByUserid(userid);
				gno = guestVo.getGuestNo();
			}
			
			ReviewBoardVO vo = reviewBoardService.selectByNo(Integer.parseInt(no));

			mav.addObject("vo", vo);
			mav.addObject("gno", gno);
			
			mav.setViewName("reviewBoard/detail");
		}
		return mav;
	}
	@GetMapping("/reviewBoard/BoardDelete") 
	public ModelAndView BoardDelete(HttpServletRequest request){ 
	ModelAndView mav= new ModelAndView();
	HttpSession session = request.getSession();
	String userid = (String) session.getAttribute("userid");

	if (userid == null) {
		String msg = "로그아웃 되었습니다.", url = "/login/login";
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		mav.setViewName("common/message");
	} else {
		String reviewNo = request.getParameter("reviewNo");
		ReviewBoardVO vo = reviewBoardService.selectByNo(Integer.parseInt(reviewNo));
		
		mav.addObject("vo", vo);

		mav.setViewName("reviewBoard/BoardDelete");
	}
	return mav;
}					
	@GetMapping("/reviewBoard/reviewEdit") 
	public ModelAndView reviewEdit(HttpServletRequest request){ 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			String reviewNo = request.getParameter("reviewNo");
			ReviewBoardVO vo = reviewBoardService.selectByNo(Integer.parseInt(reviewNo));
			
			mav.addObject("vo", vo);

			mav.setViewName("reviewBoard/reviewEdit");
		}
		return mav;
	}
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	////////////////////////////////P O S T//////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	@PostMapping("/reviewBoard/reviewWrite")
	public ModelAndView reviewWritePost(HttpServletRequest request, @RequestParam Map<String, Object> map){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			String fileName="", originFileName="";
			long fileSize=0;
			String msg = "", url = "";
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
				
				GuestVO vo = guestService.selectByUserid(userid);
	
				String r_title = (String) map.get("title");
				String r_content = (String) map.get("content");	
				int guestNo = Integer.parseInt( map.get("guestNo").toString());	
				String r_fileName = fileName;	
				long r_fileSize = fileSize;
				
				ReviewBoardVO reviewBoardVO =new ReviewBoardVO(guestNo, r_title, r_content, userid);
			
				int cnt = reviewBoardService.insertReview(reviewBoardVO);
	
				
				if (cnt > 0) {
					msg = "글이 등록되었습니다.";
					url = "/reviewBoard/reviewBoardList";
				} else {
					msg = "글 등록 실패!";
					url = "javascript:history.back()";
				}
			} catch (IOException e) {
				msg = "2M 이상의 파일은 업로드 할수 없다.";
				url = "javascript:history.back()";
				e.printStackTrace();
			}
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		}
		return mav;
	}
	@PostMapping("/reviewBoard/BoardDelete") 
	public ModelAndView BoardDeletePost(HttpServletRequest request){ 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			String reviewNo = request.getParameter("no");
			String guestNo = request.getParameter("gno");
			
			int cnt = reviewBoardService.deletereviewBoard(Integer.parseInt(reviewNo));
				
			String msg="삭제 에러 확인요망",url="javascript:history.back()";
			if(cnt>0){
				msg="삭제가 완료되었습니다.";
				url="/reviewBoard/reviewBoardList";
			}
			
			mav.addObject("msg", msg);
			mav.addObject("url", url);

			mav.setViewName("reviewBoard/reviewBoardList");
		}
		return mav;
	}
	@PostMapping("/reviewBoard/reviewEdit")
	public ModelAndView reviewEditPost(HttpServletRequest request, @RequestParam Map<String, Object> map){
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			String fileName="", originFileName="";
			long fileSize=0;
			String msg = "", url = "";
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
				
				System.out.println("userid="+userid);
				GuestVO vo = guestService.selectByUserid(userid);
	
				String r_title = (String) map.get("title");
				String r_content = (String) map.get("content");	
				int guestNo = Integer.parseInt( map.get("guestNo").toString());	
				String r_fileName = fileName;	
				long r_fileSize = fileSize;
				
				ReviewBoardVO reviewBoardVO =new ReviewBoardVO(guestNo, r_title, r_content, userid);
				String reviewNo = (String)map.get("reviewNo");
				int cnt = reviewBoardService.updatereviewBoard(reviewBoardVO,Integer.parseInt(reviewNo));
	
				if (cnt > 0) {
					msg = "글수정 되었습니다.";
					url = "/reviewBoard/reviewEdit";
				} else {
					msg = "글 등록 실패!";
					url = "javascript:history.back()";
				}
			} catch (IOException e) {
				msg = "2M 이상의 파일은 업로드 할수 없다.";
				url = "javascript:history.back()";
				e.printStackTrace();
			}
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		}
		return mav;
	}
	/*
	@RequestMapping(value="/reviewBoard/Sales") 
	public ModelAndView Sales(HttpServletRequest request){ 
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			String msg = "로그아웃 되었습니다.", url = "/login/login";
			mav.addObject("msg", msg);
			mav.addObject("url", url);
			mav.setViewName("common/message");
		} else {
			
			GuestVO guestVo = guestSerivce.selectByUserid(userid);
			int guestNo = guestVo.getGuestNo();
			
			mav.addObject("date1", date1);
			mav.addObject("date2", date2);

			mav.setViewName("reviewBoard/Sales");
		}
		return mav;
	}												
	 */
}