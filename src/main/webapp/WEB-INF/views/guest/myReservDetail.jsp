<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top" />
<main class="container mt-4" style="min-height: 70vh;">
	<h2 class="text-center mb-4">예약 상세</h2>
	<dl class="row mx-auto" style="max-width: 600px;">
		<dt class="col-4">예약번호</dt><dd class="col-8">${reservation.reservNo}</dd>
		<dt class="col-4">호텔 및 객실</dt><dd class="col-8"><c:out value="${room.locName}" /> <c:out value="${room.roomType}" /></dd>
		<dt class="col-4">투숙 날짜</dt><dd class="col-8"><c:out value="${reservation.ci_date}" /> ~ <c:out value="${reservation.co_date}" /></dd>
		<dt class="col-4">투숙 인원</dt><dd class="col-8">성인 ${reservation.adult}명, 아동 ${reservation.kids}명</dd>
		<dt class="col-4">객실 요금</dt><dd class="col-8"><fmt:formatNumber value="${reservation.totalPrice}" />원</dd>
	</dl>
	<div class="text-center"><a class="btn btn-secondary" href="<c:url value='/guest/myReservList' />">예약 목록</a></div>
</main>
<jsp:include page="../inc/footer.jsp" />
