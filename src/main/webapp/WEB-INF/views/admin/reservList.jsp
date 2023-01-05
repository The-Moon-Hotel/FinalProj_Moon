<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/inc/top"></c:import>
<style>
	p{
		font-size: 200%; 
		text-align: center; 
		margin-top:20px;
		font-weight: bold;	
	}
</style>
<div style="height: 800px;">
	<p style="font-size: 120%; text-align: center; margin-top:100px;">고객 예약 조회</p>
	<div style="width: 800px; margin:auto; text-align: center">
		<form name="searReserv" method="post" action="/admin/reservList">
			<span style="margin: 0 10px 0 10px; font-weight: bold">투숙기간</span>
			<select class="form-select" name="searchCondition" value = 'condition' style="width: 160px; display: inline;">
				<option value="all"
				<c:if test="${map.condition=='all' }">
		        	selected="selected"
			    </c:if>
				>모든 예약 보기</option>
				<option value="complete"
				<c:if test="${map.condition=='complete' }">
		        	selected="selected"
			    </c:if>
				>이용완료</option>
				<option value="incomplete"
				<c:if test="${map.condition=='incomplete' }">
		        	selected="selected"
			    </c:if>
				>이용예정</option>
				<option value="ing"
				<c:if test="${map.condition=='ing' }">
		        	selected="selected"
			    </c:if>
				>투숙중</option>
			</select>
			<input type="date" class="" name="startDate"> ~ <input type="date" name="endDate"> 
			<input type="submit" value="예약조회" > 
		</form>
	</div>
	<hr>
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
	  	<tr><th colspan="4" rowspan="2">예약내역이 없습니다.</th></tr>
	  </c:if>
	  <c:if test="${!empty rlist }">
	  	<c:set var="num" value="${pageVo.num }" />
		<c:set var="curPos" value="${pageVo.curPos}" />
		<c:forEach var="i" begin="1" end="${pageVo.pageSize }">
			<c:if test="${num>=1 }">
				<c:set var="vo" value="${rlist[curPos]}" />
				<c:set var="curPos" value="${curPos+1 }" />
				<c:set var="num" value="${num-1 }" />
				<tr>
				<td><a href="/admin/reservDetail?reservNo=${vo.reservNo}">${vo.reservNo}</a></td>
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
			</c:if>
		</c:forEach>
	  </c:if>
	  
	  </tbody>
</table>
<div align="center">
<!-- 이전블럭으로 이동 -->
		<c:if test="${pageVo.firstPage>1 }">
			<a
				href="<c:url value='/admin/reservList?currentPage=${pageVo.firstPage-1}&condition=${map.condition}&keyword=${map.keyword}'/>">
				<img src="<c:url value='/images/first.JPG'/>">
			</a>
		</c:if>
		<!-- 페이지 번호 추가 -->
		<!-- [1][2][3][4][5][6][7][8][9][10] -->
		<c:forEach var="i" begin="${pageVo.firstPage }"
			end="${pageVo.lastPage }">
			<c:if test="${i<=pageVo.totalPage }">
				<c:if test="${i==pageVo.currentPage }">
					<span style="color: blue; font-weight: bold; font-size: 1em">
						${i}</span>
				</c:if>
				<c:if test="${i!=pageVo.currentPage }">
					<a
						href="<c:url value='/admin/reservList?currentPage=${i}&condition=${map.condition}&keyword=${map.keyword}'/>">
						[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		<!--  페이지 번호 끝 -->

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage }">
			<a
				href="<c:url value='/admin/reservList?currentPage=${pageVo.lastPage+1}&condition=${map.condition}&keyword=${map.keyword}'/>">
				<img src="<c:url value='/images/last.JPG'/>">
			</a>
		</c:if>
 
</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>