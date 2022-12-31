<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="../css/reservCSS.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style>
#step1{
	width:33%;
	height: 80px;
	border: 1px solid white;
	background-color: black;
	color: white;
	font-weight:900;
}

#step2, #step3{
	width:33%;
	height: 80px;
	border: 1px solid black;
	color:black;
	background-color:white;
	font-weight:900;
}

#ci_date, #co_date, select{
	width: 180px;
	height: 30px;
}
</style>
<script type="text/javascript">
	$(function(){
		$('#submitBtn').click(function(){
			const checkin = new Date($('#ci_date').val());//2022-02-03
			const checkout = new Date($('#co_date').val());//2022-02-04

			if((checkin<checkout)!=true){
				alert('체크아웃 날짜를 다시 확인해주세요');
				event.preventDefault();
			}
		});
	});
</script>
<div style="margin-top: 100px; margin-bottom: 500px;">
	<p style="font-size: 30px; text-align: center; font-weight: bold;">객실예약</p>

	<table class="reservList1" align="center" >
		<tr>
			<td id="step1">날짜,인원 선택</td>
			<td id="step2">객실 선택</td>
			<td id="step3">옵션 선택</td>
		</tr>
	</table>

	<div>
		<form name="reservForm" method="post" action="/reservRoom/reservation2">
			<table class="reserv1" style="width: 900px" align="center">
				<tr style="color: black; font-weight: bold;">
					<td>지점</td>
					<td>체크인</td>
					<td>체크아웃</td>
					<td style="width: 80px;">성인</td>
					<td style="width: 80px;">어린이</td>
				</tr>
				<tr>
					<td>
						<select name="locName">
							<option value="Full Moon">Full Moon</option>
							<option value="Half Moon">Half Moon</option>
							<option value="Crescent Moon">Crescent Moon</option>
						</select>
					</td>
					<td><input type="date" name="ci_date" id="ci_date" min="${now}" required></td>
					<td><input type="date" name="co_date" id="co_date" required></td>
					<td><input type="number" name="adult" min="1" max="2" value="1" style="width:100px"></td>
					<td><input type="number" name="kids" min="0" max="2" value="0" style="width:100px"></td>
					<td><input type="submit" value="검색" id="submitBtn"></td>
				</tr>
				<tr>
					<td colspan="6" >예약을 원하는 날짜,인원을 선택해주세요</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>