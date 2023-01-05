
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/inc/top"></c:import>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<style>
	tr, td{
		text-align: center; 
	
	}
</style>

<div style="height: 800px;">
<p style="font-size: 120%; text-align: center; margin-top:20px;" >회원 정보 조회</p>

<table class="table" style="width: 800px; margin:auto;">
  <thead>
    <tr>
      <th scope="col">회원번호</th>
      <th scope="col">이름</th>
      <th scope="col">아이디</th>
      <th scope="col">이메일</th>
      <th scope="col">전화번호</th>
      <th scope="col">가입날짜</th>
    </tr>
  </thead>
  <tbody>
  <c:if test="${empty glist }">
  	<tr><th colspan="6" rowspan="2">가입된 회원이 없습니다.</th></tr>
  </c:if>
  <c:if test="${!empty glist }">
  	<c:set var="num" value="${pageVo.num }" />
	<c:set var="curPos" value="${pageVo.curPos}" />
	<c:forEach var="i" begin="1" end="${pageVo.pageSize }">
		<c:if test="${num>=1 }">
			<c:set var="vo" value="${glist[curPos]}" />
			<c:set var="curPos" value="${curPos+1 }" />
			<c:set var="num" value="${num-1 }" />
			<tr>
		      <td>
		      	<a href="/admin/guestDetail?guestNo=${vo.guestNo}">
		      		${vo.guestNo }</a>
		      </td>
			  <td>${vo.name }</td>
			  <td>${vo.userid }</a></td>
			  <td>${vo.email }</td>
			  <td>${vo.tel }</td>
			  <td><fmt:formatDate value="${vo.joindate }" pattern="yyyy-MM-dd" /></td>	
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
				href="<c:url value='/admin/guestList?currentPage=${pageVo.firstPage-1}&condition=${map.condition}&keyword=${map.keyword}'/>">
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
						href="<c:url value='/admin/guestList?currentPage=${i}&condition=${map.condition}&keyword=${map.keyword}'/>">
						[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		<!--  페이지 번호 끝 -->

		<!-- 다음 블럭으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage }">
			<a
				href="<c:url value='/admin/guestList?currentPage=${pageVo.lastPage+1}&condition=${map.condition}&keyword=${map.keyword}'/>">
				<img src="<c:url value='/images/last.JPG'/>">
			</a>
		</c:if>
</div>
<div class="guestSearch" style="text-align: center; padding: 10px">
   	<form name="frmSearch" method="post" action='guestList.jsp'>
        <select name="searchCondition">
            <option value="name" 
            <c:if test="${map.condition=='name' }">
	        	selected="selected"
		    </c:if>
            >이름</option>
            <option value="userid"
	           	<c:if test="${map.condition=='userid' }">
		       		selected="selected"
			    </c:if>
	        >아이디</option>
            <option value="guestNo"
	            <c:if test="${map.condition=='guestNo' }">
		        	selected="selected"
			    </c:if>
            >회원번호</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${map.keyword}">   
		<input type="submit" value="검색">
    </form>
</div>
</div>
<jsp:include page="../inc/footer.jsp"></jsp:include>
