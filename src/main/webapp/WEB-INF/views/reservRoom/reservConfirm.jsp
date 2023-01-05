<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="../css/reservCSS.css">

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
				<img src="../images/${roomVo.roomImage}" style="width:280px; height: 200px"></td>
				<td colspan="3" id="line">
					<table style="width: 100%">
						<tr align="left">
							<td>지점 : <b>${roomVo.locName }</b></td>
							<td>예약 객실 : <b>${roomVo.roomType }</b> 
							</td>	
						</tr>
						<tr align="left">
							<td>체크인날짜 : <b>${reservVoc.ci_date }</b></td>
							<td>체크아웃 날짜 : <b>${reservVo.co_date }</b></td>				
						</tr>
						<tr align="left">
							<td>성인 투숙객 수 : <b>${reservVo.adult }</b>명</td>
							<td>아동 투숙객 수 : <b>${reservVo.kids }</b>명</td>
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
							<td>${bVo.adultNo }</td>
							<td>${bVo.kidsNo }</td>
						</tr>
						<tr align="center">
							<td colspan="2">수영장 인원 수 (1인당 45,000원 / 28,000원) : </td>
							<td>${pVo.adultNo }</td>
							<td>${pVo.kidsNo }</td>
						</tr>
						<tr align="center">
							<td colspan="2">사우나 인원 수 (1인당 35,000원) : </td>
							<td>${sVo.adultNo }</td>
							<td>-</td>
						</tr>
						<tr align="center">
							<td colspan="2">헬스장 인원 수 (1인당 20,000원) : </td>
							<td>${gVo.adultNo }</td>
							<td>-</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">총 금액 :
					<b> ${reservVo.totalPrice +(bVo.adultNo*40000)+(bVo.kidsNo*170000)+(pVo.adultNo*45000)
					+(pVo.kidsNo*28000)+(sVo.adultNo*35000)+(gVo.adultNo*20000)}원 </b>
				</td>
			</tr>
			<tr style="border-bottom:2px solid #e3e3e3;">
				<td colspan="4" align="center" id="line"> 
					<input type="button" id="submitBtn" value="메인페이지" onclick="location.href='/'">
				</td>
			</tr>
		</table>
</div>

<jsp:include page="../inc/footer.jsp"></jsp:include>