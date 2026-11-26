<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top" />
<link rel="stylesheet" href="<c:url value='/css/board-detail.css' />">
<c:url var="reviewListUrl" value="/reviewBoard/reviewBoardList" />

<main class="board-detail">
	<header class="board-heading">
		<h1>리뷰 상세</h1>
		<a class="btn btn-outline-secondary" href="${reviewListUrl}">목록</a>
	</header>

	<article class="board-panel">
		<header class="board-panel-header">
			<h2><c:out value="${vo.r_title}" /></h2>
			<p><fmt:formatDate value="${vo.r_regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		</header>
		<div class="board-message review-content"><c:out value="${reviewContent}" escapeXml="false" /></div>
		<c:if test="${vo.guestNo eq gno}">
			<c:url var="editUrl" value="/reviewBoard/reviewEdit"><c:param name="reviewNo" value="${vo.reviewNo}" /></c:url>
			<c:url var="deleteUrl" value="/reviewBoard/BoardDelete"><c:param name="reviewNo" value="${vo.reviewNo}" /></c:url>
			<div class="board-actions">
				<a class="btn btn-outline-secondary btn-sm" href="${editUrl}">수정</a>
				<a class="btn btn-outline-danger btn-sm" href="${deleteUrl}">삭제</a>
			</div>
		</c:if>
	</article>
</main>
<jsp:include page="../inc/footer.jsp" />
