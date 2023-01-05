<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/inc/top"></c:import>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../css/adminPage.css" />

<p style="font-size: 120%; 
	text-align: center; margin-top:20px;" >회원 예약 정보</p>

<table class="table" style="width: 800px; margin:auto; text-align: center;">
	  <thead>
	    <tr>
	      <th>예약번호</th>
		  <th>회원이름</th>
		  <th>호텔정보</th>
		  <th>이용일자</th>
		  <th>가격</th>	
		  <th>이용상태</th>
	    </tr>
</thead>
<tbody>
	<c:if test="${empty rlist }">
	  	<tr><th colspan="6" rowspan="2">예약내역이 없습니다.</th></tr>
	</c:if>
	<c:if test="${!empty rlist }">
		<c:forEach var="vo" items="${rlist}">
			<tr>
				<td><a href="/admin/guestReservDetail?reservNo=${vo.reservNo}">${vo.reservNo}</a></td>
				<td>${vo.name}</td>
				<td>${vo.locName}</td>
				<td>${vo.ci_date} - ${vo.co_date}</td>
				<td align="right"><fmt:formatNumber value="${vo.totalPrice }" pattern="#,###"/></td>
					<c:set var="co_date" value="${fn:replace(vo.co_date,'-','') }"></c:set>
					<c:set var="ci_date" value="${fn:replace(vo.ci_date,'-','') }"></c:set>
				<c:choose>
					<c:when test="${co_date - thisday>0 }">
						<td value="incomplete">${co_date - thisday }이용예정</td>
					</c:when>
					<c:when test="${ci_date - thisday<0 }">
						<td value="complete">${ci_date - thisday }이용완료</td>
					</c:when>
					<c:otherwise>
						<td value="ing">투숙중</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</c:if>
	  </tbody>
</table>
<div align="center">
	<a class="btn btn-secondary" role="button" id="reservList"
		 href="guestList.jsp">회원 목록</a>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>