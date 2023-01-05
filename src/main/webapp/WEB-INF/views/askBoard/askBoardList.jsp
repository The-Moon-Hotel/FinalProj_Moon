<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>

<%-- <%@ include file="../login/checkLogin.jsp"%> --%>
<c:import url="/inc/top"></c:import>
<c:set var="userid" value="${sessionScope.userid }" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<div style="height: 800px;">
	<c:choose>
		<c:when test="${g_vo.sys !=1 }">
			<p style="font-size: 120%; text-align: center; margin-top: 20px;">고객
				문의내역</p>
		</c:when>
		<c:otherwise>
			<p style="font-size: 120%; text-align: center; margin-top: 20px;">나의
				문의내역</p>
		</c:otherwise>
	</c:choose>
	<hr>
	<table class="table"
		style="width: 800px; margin: auto; text-align: center">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<c:choose>
					<c:when test="${g_vo.sys !=1 }">
						<th>고객번호</th>
					</c:when>
					<c:otherwise>
						<th>고객이름</th>
					</c:otherwise>
				</c:choose>
				<th>등록날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<th colspan="4" rowspan="2">문의내역이 없습니다.</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:set var="num" value="${pageVo.num }" />
					<c:set var="curPos" value="${pageVo.curPos}" />
					<c:forEach var="i" begin="1" end="${pageVo.pageSize }">
						<c:if test="${num>=1 }">
							<c:set var="vo" value="${list[curPos]}" />
							<c:set var="curPos" value="${curPos+1 }" />
							<c:set var="num" value="${num-1 }" />
							<tr style="text-align: center">
								<td>${vo.askNo}</td>
								<td style="text-align: left"><a
									href="<c:url value='/askBoard/askDetail?askNo=${vo.askNo}'/>">
										${vo.a_title }</a></td>
								<c:if test="${g_vo.sys==1 }">
									<td>${vo.name }</td>
								</c:if>
								<c:if test="${g_vo.sys!=1 }">
									<td>${vo.guestNo }</td>
								</c:if>
								<td><fmt:formatDate value="${vo.a_regdate }"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:if>
					</c:forEach>

				</c:otherwise>
			</c:choose>
		</tbody>
	</table>


	<div class="divPage" style="text-align: center"; margin: 10px;>
		<!-- 이전블럭으로 이동 -->
		<c:if test="${pageVo.firstPage>1 }">
			<a
				href="<c:url value='/askBoard/askBoardList?currentPage=${pageVo.firstPage-1}&condition=${map.condition}&keyword=${map.keyword}'/>">
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
						href="<c:url value='/askBoard/askBoardList?currentPage=${i}&condition=${map.condition}&keyword=${map.keyword}'/>">
						[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		<!--  페이지 번호 끝 -->

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage }">
			<a
				href="<c:url value='/askBoard/askBoardList?currentPage=${pageVo.lastPage+1}&condition=${map.condition}&keyword=${map.keyword}'/>">
				<img src="<c:url value='/images/last.JPG'/>">
			</a>
		</c:if>
	</div>
	<c:if test="${g_vo.sys!=1 }">
		<div class="divSearch" style="text-align: center">
			<form name="frmSearch" method="post" action='/askBoard/askBoardList'>
				<select name="condition">
					<option value="a_title"
						<c:if test="${map.condition=='a_title' }">
		            		selected="selected"
		            </c:if>
		            >제목</option>
					<option value="a_content"
						<c:if test="${map.condition=='a_content' }">
	            		selected="selected"
	            	</c:if>>내용</option>
					<option value="askNo"
						<c:if test="${map.condition=='askNo' }">
		            		selected="selected"
		            </c:if>>고객번호</option>
				</select> <input type="text" name="keyword" title="검색어 입력"
					value="${map.keyword}"> <input type="submit" value="검색">

			</form>

		</div>
	</c:if>
	<div class="divbtn" style="margin: 0 auto; text-align: center;">
		<a href='/askBoard/askWrite' class="btn btn-dark"
			style="margin-left: 750px;">글쓰기</a>
	</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>