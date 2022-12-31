<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../inc/top.jsp"></jsp:include>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<div style="height: 800px;">
	<p style="font-size: 120%; text-align: center; margin-top:20px;">예약 내역</p>
	<div style="width: 800px; margin:auto; text-align: center">
		<form name="searMyReserv" method="post" action="myReservList_ok.jsp">
			<span style="margin: 0 10px 0 10px; font-weight: bold">투숙기간</span>
			<select class="form-select" style="width: 160px; display: inline;">
				<option>모든 예약 보기</option>
				<option>예약 완료</option>
				<option>예약 취소</option>
			</select>
			<input type="date" class="" name="startDate"> ~ <input type="date" name="endDate"> 
			<input type="submit" value="예약조회"> 
		</form>
	</div>
	<hr>
	<table class="table" style="width: 800px; margin:auto; text-align: center;">
	  <thead>
	    <tr>
	      <th>예약번호</th>
		  <th>호텔정보</th>
		  <th>이용일자</th>
		  <th>이용상태</th>	
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <td><a href="myReservDetail.jsp">10336951</a></td>
		  <td>더문호텔 1호점</td>
		  <td>2021년 09월 28일 - 2021년 09월 29일</td>
		  <td>이용 완료</td>	
	    </tr>
	    <tr>
	      <td><a href="myReservDetail.jsp">10336951</a></td>
		  <td>더문호텔 1호점</td>
		  <td>2021년 09월 28일 - 2021년 09월 29일</td>
		  <td>예약 취소</td>	
	    </tr>
	  </tbody>
</table>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>