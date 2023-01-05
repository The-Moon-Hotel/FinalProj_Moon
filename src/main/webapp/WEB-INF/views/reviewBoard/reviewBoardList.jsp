<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top"></c:import>
<style type="text/css">
#div1 {
	width: 800px;
}
</style>

<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btn1').click(function(){
			location.href="/reviewBoard/reviewWrite";
		});
	});
</script>
<div style="height: 800px;">
	<div class="container text-center" style="margin-top: 100px;">
	<br><br>
		<h2>리뷰게시판</h2>
	</div>
	<div id="div1" class="container text-center">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">no</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list }">
					<tr>
						<td colspan="5">데이터가 없..</td>
					</tr>
				</c:if>
				<c:if test="${!empty list }">
					<c:set var="num" value="${pageVo.num }" />
						<c:set var="curPos" value="${pageVo.curPos}" />
						<c:forEach var="i" begin="1" end="${pageVo.pageSize }">
							<c:if test="${num>=1 }">
								<c:set var="vo" value="${list[curPos]}" />
								<c:set var="curPos" value="${curPos+1 }" />
								<c:set var="num" value="${num-1 }" />
								<tr style="text-align: center">
									<td>${vo.reviewNo}</td>
									<td style="text-align: left">
										<a href="/reviewBoard/detail?no=${vo.reviewNo}">${vo.r_title}</a>
									</td>
									<td>${vo.userid }</td>
									<td><fmt:formatDate value="${vo.r_regdate }" pattern="yyyy-MM-dd" />
									</td>
								</tr>
							</c:if>
						</c:forEach>
				</c:if>
			</tbody>
		</table>
			<div class="divPage">
		<!-- 이전블럭으로 이동 -->
		<c:if test="${pageVo.firstPage>1 }">
			<a
				href="<c:url value='/reviewBoard/reviewBoardList?currentPage=${pageVo.firstPage-1}&condition=${map.condition}&keyword=${map.keyword}'/>">
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
						href="<c:url value='/reviewBoard/reviewBoardList?currentPage=${i}&condition=${map.condition}&keyword=${map.keyword}'/>">
						[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		<!--  페이지 번호 끝 -->

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage }">
			<a
				href="<c:url value='/reviewBoard/reviewBoardList?currentPage=${pageVo.lastPage+1}&condition=${map.condition}&keyword=${map.keyword}'/>">
				<img src="<c:url value='/images/last.JPG'/>">
			</a>
		</c:if>
	</div>
	<form name= "frm1" method="post" action="reviewBoardList.jsp" style="width: 100%">
		<div class="form-group"style="display:inline-block">
			<label for="exampleSelect1" class="form-label mt-4"></label>
			<select class="form-select" name="condition" style="width:100px" style="float:left">
				<option value="n_title"
					<c:if test="${map.condition=='n_title' }">
	            		selected="selected"
	            	</c:if>
				>제목</option>
				<option value="userid"
					<c:if test="${map.condition=='userid' }">
	            		selected="selected"
	            	</c:if>					
				>작성자</option>
				<option value="regdate"
					<c:if test="${map.condition=='regdate' }">
	            		selected="selected"
	            	</c:if>					
				>작성일</option>
			</select>
			</div>
		<div class="form-group"style="display:inline-block">
			<input type="text" class="form-control" name="keyword" placeholder="검색하고자 하는 내용 입력"
			style="width:400px" style="float:right" value="${map.keyword}">
			</div>
		<div class="form-group"style="display:inline-block">
			<button id="searchBtn" type="submit" class="btn btn-dark" style="margin-bottom: 5px">검색</button>
			</div>
	</form>
			<br><br>
		<button id="btn1" type="button" class="btn btn-dark" style="float: right;">글쓰기</button>
		<br> <br> <br>
	</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
