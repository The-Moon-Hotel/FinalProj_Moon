<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="/css/reservCSS.css">

<style>
#step2{
	width:33%;
	height: 80px;
	border: 1px solid white;
	background-color: black;
	color: white;
	font-weight:900;
}

#step1, #step3{
	width:33%;
	height: 80px;
	border: 1px solid black;
	color:black;
	background-color:white;
	font-weight:900;
}

</style>
<div style="margin-top: 100px;">
	<p style="font-size: 30px; text-align: center; font-weight: bold;">객실예약</p>

	<table class="reservList1" align="center">
		<tr>
			<td id="step1">날짜,인원 선택</td>
			<td id="step2">객실 선택</td>
			<td id="step3">옵션 선택</td>
		</tr>
	</table>
	<form name="selectRoom" method="post" action="/reservRoom/reservation2">
		<table width="80%" align="center" class="reserv1">
			<tr align="center" id="list1"
				style="color: #a0a0a0; font-weight: bold;">
				<td>지점</td>
				<td>체크인</td>
				<td>체크아웃</td>
				<td width="10%" align="center"><img src="../images/night.png"
					width="30" height="30"></td>
				<td width="10%">성인</td>
				<td width="10%">어린이</td>
			</tr>
			<tr align="center">
				<td><input type="text" name="locName" readonly
					value="${param.locName}"></td>
				<td><input type="text" name="ci_date" readonly
					value="${param.ci_date}"></td>
				<td><input type="text" name="co_date" readonly
					value="${param.co_date}"></td>
				<td><input type="text" name="dateGap" size="10"
					value="${param.gapDate}" readonly="readonly" style="width: 100px"></td>
				<td><input type="text" name="adult" size="10"
					value="${param.adult}" style="width: 100px"></td>
				<td><input type="text" name="kids" size="10" value="${param.kids}"
					style="width: 100px"></td>
			</tr>
		</table>
		<table align="center" > 
		<c:if test="${empty rlist }">
		<tr>
				<td align="center">예약하실 수 있는 방이 없습니다.</td>
			</tr>
			<tr><td align="center"><a href="/reservRoom/reservation1" >다시 선택하기</a></td></tr>
		</c:if>
		<c:if test="${!empty rlist }">
			<c:forEach var="roomVo" items="${rlist }">
				<tr align="center" id="line" >
						<td width="30%" id="line">
						<img src="/images/${roomVo.roomImage}" style="width: 300px; height: 200px"></td>
						<td id="line">
							<table style="width: 200px" >
								<tr>
									<th>룸 타입: <input type="text" name="roomType" 
									value="${roomVo.roomType}" readonly style="width: 120px; font-size:100%;"></th>
								</tr>
								<tr>
									<td>최대 수용 인원 : 2명</td>
								</tr>
							</table>
						</td>
						
						<td width="25%" id="line">
							1 박: <input type="text" name="roomPrice" value="<fmt:formatNumber value="${roomVo.roomPrice }" pattern="#,###"/>" 
								readonly style="width: 100px; font-size:100%;"> 원<br>
							총 가격: <input type="text" name="totalPrice" value="<fmt:formatNumber value="${roomVo.roomPrice* gapDate }" pattern="#,###"/>"
							readonly style="width: 100px; font-size:100%;"> 원
						</td>
						<td id="line">
							<!-- <input type="submit" id="submitBtn" value="예약하기" > -->
							<input type="button" id="submitBtn2" value="예약하기" 
	onclick="location.href='/reservRoom/reservation3?ci_date=${param.ci_date }&co_date=${param.co_date }&adult=${param.adult }&kids=${param.kids }&roomType=${roomVo.roomType }&roomImage=${roomVo.roomImage }&totalPrice=<fmt:formatNumber value="${roomVo.roomPrice* gapDate }" pattern="#,###"/>&locName=${param.locName }'">
						</td>
					</tr>
			</c:forEach>
		</c:if>
		</table>
	</form>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>