<%@page import="com.moon.guest.model.GuestSerivce"%>
<%@page import="com.moon.guest.model.GuestVO"%>
<%@page import="com.moon.room.model.RoomVO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.moon.reservation.model.ReservationVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="reservService" 
	class="com.moon.reservation.model.ReservationService" scope="session"></jsp:useBean>
<jsp:useBean id="roomService" 
	class="com.moon.room.model.RoomService" scope="session"></jsp:useBean>
<jsp:useBean id="reservVo" 
	class="com.moon.reservation.model.ReservationVO" scope="page"></jsp:useBean>
<jsp:useBean id="guestSerivce" class="com.moon.guest.model.GuestSerivce" scope="session"></jsp:useBean>
<jsp:useBean id="guestVO" class="com.moon.guest.model.GuestVO" scope="page"></jsp:useBean>
<jsp:useBean id="facService" class="com.moon.facility.model.FacilityService" scope="session"></jsp:useBean>
<jsp:useBean id="facVo" class="com.moon.facility.model.FacilityVO" scope="page"></jsp:useBean>
<%
	String userid = (String)session.getAttribute("userid");
	
	request.setCharacterEncoding("utf-8");
	
	/* 예약 정보 받아오기 */
	GuestVO guestVo = guestSerivce.selectByUserid(userid);
	int guestNo = guestVo.getGuestNo();
	
	String locName = request.getParameter("locName");
	String ci_date = request.getParameter("ci_date");
	String co_date = request.getParameter("co_date");
	String adult = request.getParameter("adult");
	String kids = request.getParameter("kids");
	String roomType = request.getParameter("roomType");
	String totalPrice = request.getParameter("totalPrice");
	String roomImage = request.getParameter("roomImage");
	
	totalPrice = totalPrice.replaceAll(",", "");
	
	/* 조식 인원 */
	String b_adult = request.getParameter("b_adult");
	String b_kids = request.getParameter("b_kids");
	
	/* 수영장 인원 */
	String p_adult = request.getParameter("p_adult");
	String p_kids = request.getParameter("p_kids");
	
	/* 사우나 인원 */
	String s_adult = request.getParameter("s_adult");
	
	/* 헬스장 인원 */
	String g_adult = request.getParameter("g_adult");
	
	//2. db 작업
	
	//RoomNo 정보 읽어오기
	RoomVO roomVo = new RoomVO();
	try{
		roomVo = roomService.selectRoomByType(locName, roomType);
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	//객실 예약하기
	reservVo.setGuestNo(guestNo);
	reservVo.setRoomNo(roomVo.getRoomNo());
	reservVo.setAdult(Integer.parseInt(adult));
	reservVo.setKids(Integer.parseInt(kids));
	reservVo.setCi_date(ci_date);
	reservVo.setCo_date(co_date);
	reservVo.setTotalPrice(Integer.parseInt(totalPrice));
	
	String msg="예약에 실패했습니다.", url="reservation1.jsp";
	try{
		int cnt = reservService.insertReservation(reservVo);
		
		if(cnt>0){

			int reservNo = reservService.searchReservNo(guestNo);
			
			if(b_adult!=null || !b_adult.isEmpty()){ //조식 예약
				facVo.setAdultNo(Integer.parseInt(b_adult));
				facVo.setKidsNo(Integer.parseInt(b_kids));
				facVo.setFacInfoNo(1);
				
				int facCnt = facService.insertFacility(facVo, reservNo);
			}
			if(p_adult!=null || !p_adult.isEmpty()){ //수영장 예약
				facVo.setAdultNo(Integer.parseInt(p_adult));
				facVo.setKidsNo(Integer.parseInt(p_kids));
				facVo.setFacInfoNo(2);
				int facCnt = facService.insertFacility(facVo, reservNo);

			}
			if(s_adult!=null || !s_adult.isEmpty()){ //사우나 예약
				facVo.setAdultNo(Integer.parseInt(s_adult));
				facVo.setKidsNo(0);
				
				facVo.setFacInfoNo(3);
				int facCnt = facService.insertFacility(facVo, reservNo);

			}
			if(g_adult!=null || !g_adult.isEmpty()){ //헬스장 예약
				facVo.setAdultNo(Integer.parseInt(g_adult));
				facVo.setKidsNo(0);
				facVo.setFacInfoNo(4);
				int facCnt = facService.insertFacility(facVo, reservNo);
			}
			
			msg="예약되었습니다.";
			url="reservConfirm.jsp";
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);
%>
<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>