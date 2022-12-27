<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="/css/facility.css">
<link rel="stylesheet" href="/css/fac_Detail.css">


<style>
	table{
		width: 300px; 
		height: 90px;
		text-align: center;
	}
</style>
<section class = "faci">
	<span>
		<a href="/facility/facility">
			<input type="button" value="부대시설" class="btn btn-dark">
		</a>
		<a href="/facility/dining">
			<input type="button" value="다이닝" class="btn btn-dark">	
		</a>
	</span>
	<br><br>
	
	<div class="swim">
		<h3 class="title"><b>다이닝 M</b></h3>
		<img src="../images/dining.png">
		
		<h2 style="color:#C1B197">자연을 담은 모던한 감각의 올 데이 다이닝 레스토랑</h2>
		<p> 신선한 식재료를 이용하여 셰프들이 즉석에서 조리한 음식을 맛볼 수 있는 올 데이 다이닝 레스토랑입니다.<br>
				현대적인 감각의 인테리어와 다채로운 미각의 세계를 선보입니다. </p>
		<br>
		<pre><span>위치 </span> 본관 B1층  |  <span>좌석수 </span> 244석  |  <span>예약 및 문의 </span> 02-333-2210</pre>
		<br>
		<p>이용 시간 및 요금 안내
		<br>
		<pre style="font-size: 17px">조식     성인  40,000    어린이  17,000</pre>
		<hr><br>
	</div>
	
	<div>
		<h3 class="title"><b>비스트로 M</b></h3>
		<img src = "../images/bar.png">
		<br>
		<h2 style="color:#C1B197">시원한 전망이 펼쳐지는 캐주얼 레스토랑</h2>
		<p> 가벼운 음식 그리고 시원한 맥주와 칵테일, 와인을 즐기며 여유롭고 즐거운 시간을 보낼 수 있습니다.</p>
		<br>
		<pre><span>위치 </span> 레지던스 1층  |  <span>운영시간 </span> 11:00~22:00  |  <span>예약 및 문의 </span> 02-333-2217  |  <span>좌석수 </span> 총 96석 </pre>
		<br>
		
		<br><br>
		<hr><br>
	</div>

</section>

<div style="height: 100px"></div>
<jsp:include page="../inc/footer.jsp" ></jsp:include>