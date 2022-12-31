<%@page import="com.moon.facility.model.FacilityVO"%>
<%@page import="com.moon.reservation.model.ReservationVO"%>
<%@page import="com.moon.guest.model.GuestVO"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../inc/top.jsp"></jsp:include>
<link rel="stylesheet" href="../css/reservCSS.css">
<jsp:useBean id="reservService" 
	class="com.moon.reservation.model.ReservationService" scope="session"></jsp:useBean>
<jsp:useBean id="roomService" 
	class="com.moon.room.model.RoomService" scope="session"></jsp:useBean>
<jsp:useBean id="reservVo" 
	class="com.moon.reservation.model.ReservationVO" scope="page"></jsp:useBean>
<jsp:useBean id="roomVo" 
	class="com.moon.room.model.RoomVO" scope="page"></jsp:useBean>
<jsp:useBean id="guestSerivce" class="com.moon.guest.model.GuestSerivce" scope="session"></jsp:useBean>
<jsp:useBean id="facService" class="com.moon.facility.model.FacilityService" scope="session"></jsp:useBean>

<%
	String userid = (String)session.getAttribute("userid"); //로그인 정보
	
	GuestVO guestVo = guestSerivce.selectByUserid(userid);
	int guestNo = guestVo.getGuestNo();
	FacilityVO bVo = new FacilityVO();
	FacilityVO pVo = new FacilityVO();
	FacilityVO sVo = new FacilityVO();
	FacilityVO gVo = new FacilityVO();
	
	//예약 정보 받아오기
	try{
		reservVo = reservService.searchReserv(guestNo);
		int reservNo = reservVo.getReservNo();
		roomVo = roomService.selectRoomByRoomNo(reservVo.getRoomNo());
		bVo = facService.selectBreakFastInfo(reservNo);
		pVo = facService.selectPoolInfo(reservNo);
		sVo = facService.selectSaunaInfo(reservNo);
		gVo = facService.selectGymInfo(reservNo);
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	
%>
<div style="margin-top: 100px; margin-bottom: 800px;">
	<p style="font-size: 30px; text-align: center; font-weight: bold;">예약확인</p>
		<table style="width: 900px" align="center" class="reserveOption" >
			<tr>
				<td colspan="4" align="center" 
				style="font-size: 25px; font-weight: bold; border-top:2px solid #e3e3e3;">
					객실정보
				</td>
			</tr>
			<tr id="line">
				<td align="center" id="line">
				<img src="../images/<%=roomVo.getRoomImage() %>" style="width:280px; height: 200px"></td>
				<td colspan="3" id="line">
					<table style="width: 100%">
						<tr align="left">
							<td>지점 : <b><%=roomVo.getLocName() %></b></td>
							<td>예약 객실 : <b><%=roomVo.getRoomType() %></b> 
							</td>	
						</tr>
						<tr align="left">
							<td>체크인날짜 : <b><%=reservVo.getCi_date() %></b></td>
							<td>체크아웃 날짜 : <b><%=reservVo.getCo_date() %></b></td>				
						</tr>
						<tr align="left">
							<td>성인 투숙객 수 : <b><%=reservVo.getAdult() %></b>명</td>
							<td>아동 투숙객 수 : <b><%=reservVo.getKids() %></b>명</td>
						</tr>				
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" 
				style="font-size: 25px; font-weight: bold; border-top:2px solid #e3e3e3;">옵션 확인</td>
			</tr>
			<tr id="line">
				<td colspan="4" id="line">
					<table style="width: 100%; height: 80px;">
						<tr align="center">
							<td colspan="2" style="width: 50%"></td>
							<td>성인</td>
							<td>아동</td>
						</tr>
						<tr align="center">
							<td colspan="2">조식 인원 수 (1인당 40,000원 / 17,000원) :</td>
							<td><%=bVo.getAdultNo() %></td>
							<td><%=bVo.getKidsNo() %></td>
						</tr>
						<tr align="center">
							<td colspan="2">수영장 인원 수 (1인당 45,000원 / 28,000원) : </td>
							<td><%=pVo.getAdultNo() %></td>
							<td><%=pVo.getKidsNo() %></td>
						</tr>
						<tr align="center">
							<td colspan="2">사우나 인원 수 (1인당 35,000원) : </td>
							<td><%=sVo.getAdultNo() %></td>
							<td>-</td>
						</tr>
						<tr align="center">
							<td colspan="2">헬스장 인원 수 (1인당 20,000원) : </td>
							<td><%=gVo.getAdultNo() %></td>
							<td>-</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">총 금액 :
					<b> <%=reservVo.getTotalPrice() +(bVo.getAdultNo()*40000)+(bVo.getKidsNo()*170000)+(pVo.getAdultNo()*45000)
					+(pVo.getKidsNo()*28000)+(sVo.getAdultNo()*35000)+(gVo.getAdultNo()*20000) %>원 </b>
				</td>
			</tr>
			<tr style="border-bottom:2px solid #e3e3e3;">
				<td colspan="4" align="center" id="line"> 
					<input type="button" id="submitBtn" value="메인페이지" onclick="location.href='../index.jsp'">
				</td>
			</tr>
		</table>
</div>

<jsp:include page="../inc/footer.jsp"></jsp:include>