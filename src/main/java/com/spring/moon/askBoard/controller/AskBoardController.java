package com.spring.moon.askBoard.controller;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.moon.common.PagingVO;
import com.spring.moon.askBoard.model.AskBoardVO;
import com.spring.moon.askBoard.model.AskboardService;
import com.spring.moon.common.PaginationInfo;
import com.spring.moon.guest.model.GuestSerivce;
import com.spring.moon.guest.model.GuestVO;

@Controller
public class AskBoardController {
	static MultipartHttpServletRequest request=null;
	private static final String SAVEFOLER = request.getContextPath()+"/fileUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 5 * 1024 * 1024;
	
	@Autowired
	AskboardService askboardService;
	
	@Autowired
	GuestSerivce guestService;
	
//////////////////////////////G E T 방식 /////////////////////////////////////////////////////////////////////////////////////////	
	// 문의게시판 글쓰기 화면 보기
		@RequestMapping(value = "/askBoard/askWrite", method = RequestMethod.GET)
		public ModelAndView write(HttpServletRequest request, @RequestParam String userid) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);

			ModelAndView mav = new ModelAndView();


			mav.addObject("userid", userid);
			mav.setViewName("askBoard/askWrite");

			return mav;
		}
		// 글 목록 불러오기
		@RequestMapping(value = "/askBoard/askBoardList")
		public ModelAndView list(HttpServletRequest request,@RequestParam Map<String, Object> map,@RequestParam(value="currentPage", defaultValue = "1") int currentPage){
			HttpSession session = request.getSession();
			String userid=(String) session.getAttribute("userid");
			//System.out.println("글 목록 불러오기 map=" + map.get("keyWord") + map.get("keyField"));
			GuestVO g_vo = guestService.selectByUserid(userid);
			List<AskBoardVO> list=null;
			
			//일반회원이라면 (sys==1)
			 if(g_vo.getSys() == 1){
				list = this.askboardService.selectByGuestno(g_vo.getGuestNo()); 
				
			//관리자라면
			 }else{
				
				list = this.askboardService.selectAll(map.get("keyword").toString(),map.get("condition").toString());
			} 
			/*
			 * for (int i = 0; i < list.size(); i++) { System.out.println((String)
			 * list.get(i).get("uName")); }
			 */
			/////////////////////// 페이징 관련 속성 값 시작///////////////////////////
			// 페이징(Paging) = 페이지 나누기를 의미함
			int totalRecord = 0; // 전체 데이터 수(DB에 저장된 row 개수)
			int pageSize = 5; // 페이지당 출력하는 데이터 수(=게시글 숫자)
			int blockSize = 5; // 블럭당 표시되는 페이지 수의 개수
			int totalPage = 0; // 전체 페이지 수
			int totalBlock = 0; // 전체 블록수

			/*
			 * 페이징 변수값의 이해 totalRecord=> 200 전체레코드 numPerPage => 10 pagePerBlock => 5
			 * totalPage => 20 totalBlock => 4 (20/5 => 4)
			 */
			int nowBlock = 1; // 현재 (사용자가 보고 있는) 블럭

			int listSize = 0; // 1페이지에서 보여주는 데이터 수
			// 출력할 데이터의 개수 = 데이터 1개는 가로줄 1개

			// 게시판 검색 관련소스
			String keyField = ""; // DB의 컬럼명
			String keyWord = ""; // DB의 검색어

			if (map.get("keyWord") != null) {
				keyField = (String) map.get("keyField");
				keyWord = (String) map.get("keyWord");
			}

			/*
			 * select * from tblBoard order by num desc limit 10, 10; 데이터가 100개 => num : 100
			 * 99 98 97 ... 91 | 90 .... 2 1 start, end : 0 1 2 3.... 9 10 페이지당 출력할 데이터 수
			 * 10개 현재 페이지 1페이지라면 => 1페이지의 출력결과 100 ~ 91 2페이지(= currentPage가 2라는 의미) 90~81
			 * 3페이지 80~71
			 */

			totalRecord = 0;
			if (list != null) {
				totalRecord = list.size();
			}
			PagingVO pagingVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);
			// 전체 데이터 수 반환

			/////////////////////// 페이징 관련 속성 값 끝///////////////////////////

			ModelAndView mav = new ModelAndView();

			mav.addObject("list", list);
			mav.addObject("pagingVo", pagingVo);
			mav.addObject("g_vo", g_vo);

			mav.setViewName("/askBoard/askBoardList");

			return mav;

		}
		@GetMapping("/askBoard/askDetail")
		public ModelAndView askDetail(HttpServletRequest request,@RequestParam int no){
			ModelAndView mav= new ModelAndView();
			HttpSession session = request.getSession();
			String userid=(String) session.getAttribute("userid");
			GuestVO g_vo = guestService.selectByUserid(userid);
			
			AskBoardVO vo = this.askboardService.selectByAskNo(no);
			
			mav.addObject("sys", g_vo.getSys());
			mav.addObject("vo", vo);
			mav.setViewName("askBoard/askDetail");
			return mav;
		}
////////////////////////////// P O S T 방식 ///////////////////////////////////////////////////////////////////////////////////	
		@RequestMapping(value = "/askBoard/askWrite", method = RequestMethod.POST)
		public ModelAndView writePost(@RequestParam Map<String, Object> map) {
			ModelAndView mav = new ModelAndView();
			String userid=(String) map.get("userid");
			GuestVO vo=null;
			
			try {
				vo = guestService.selectByUserid(userid);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			AskBoardVO askBoardVO=new AskBoardVO();

			int guestno = vo.getGuestNo();
			map.put("guestno",guestno); 
			
			int cnt=0;
			
			try {
				cnt = askboardService.insert(askBoardVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String msg = "", url="";
			if (cnt>0) {
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
