<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top" />
<main class="container mt-4" style="min-height: 70vh;">
	<h2 class="text-center mb-4">내 예약 내역</h2>
	<div class="table-responsive">
		<table class="table text-center">
			<thead>
				<tr><th>예약번호</th><th>호텔</th><th>투숙 날짜</th><th>객실 요금</th><th>이용상태</th></tr>
			</thead>
			<tbody>
				<c:if test="${empty reservations}">
					<tr><td colspan="5">예약 내역이 없습니다.</td></tr>
				</c:if>
				<c:forEach var="reservation" items="${reservations}">
					<c:url var="detailUrl" value="/guest/myReservDetail">
						<c:param name="reservNo" value="${reservation.reservNo}" />
					</c:url>
					<tr>
						<td><a href="${detailUrl}">${reservation.reservNo}</a></td>
						<td><c:out value="${reservation.locName}" /></td>
						<td><c:out value="${reservation.ci_date}" /> ~ <c:out value="${reservation.co_date}" /></td>
						<td><fmt:formatNumber value="${reservation.totalPrice}" />원</td>
						<td>
							<c:choose>
								<c:when test="${today lt reservation.ci_date}">이용 예정</c:when>
								<c:when test="${today ge reservation.co_date}">이용 완료</c:when>
								<c:otherwise>투숙 중</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>
<jsp:include page="../inc/footer.jsp" />
