<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top" />
<link rel="stylesheet" href="<c:url value='/css/board-detail.css' />">
<c:url var="inquiryListUrl" value="/askBoard/askBoardList" />

<main class="board-detail">
	<header class="board-heading">
		<h1>문의 내역</h1>
		<a class="btn btn-outline-secondary" href="${inquiryListUrl}">목록</a>
	</header>

	<article class="board-panel">
		<header class="board-panel-header">
			<h2><c:out value="${vo.a_title}" /></h2>
			<p><fmt:formatDate value="${vo.a_regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		</header>
		<p class="board-message"><c:out value="${vo.a_content}" /></p>
		<c:if test="${owner}">
			<div class="board-actions">
				<c:url var="editUrl" value="/askBoard/askEdit"><c:param name="askNo" value="${vo.askNo}" /></c:url>
				<a class="btn btn-outline-secondary btn-sm" href="${editUrl}">수정</a>
				<form method="post" action="<c:url value='/askBoard/askDelete' />" onsubmit="return confirm('해당 문의글을 삭제하시겠습니까?')">
					<input type="hidden" name="askNo" value="${vo.askNo}">
					<button type="submit" class="btn btn-outline-danger btn-sm">삭제</button>
				</form>
			</div>
		</c:if>
	</article>

	<c:import url="/askBoard/commentsList"><c:param name="askNo" value="${vo.askNo}" /></c:import>
	<c:if test="${admin}">
		<c:import url="/askBoard/commentsWrite"><c:param name="askNo" value="${vo.askNo}" /></c:import>
	</c:if>
</main>
<jsp:include page="../inc/footer.jsp" />
