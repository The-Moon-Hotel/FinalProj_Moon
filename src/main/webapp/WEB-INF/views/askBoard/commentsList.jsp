<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="board-panel" aria-labelledby="inquiry-replies-heading">
	<header class="board-panel-header"><h2 id="inquiry-replies-heading">답변</h2></header>
	<c:choose>
		<c:when test="${empty list}">
			<p class="inquiry-empty">등록된 답변이 없습니다.</p>
		</c:when>
		<c:otherwise>
			<ul class="inquiry-replies">
				<c:forEach var="reply" items="${list}">
					<li>
						<div class="inquiry-reply-meta">
							<strong><c:out value="${reply.name}" /></strong>
							<span><fmt:formatDate value="${reply.regdate}" pattern="yyyy-MM-dd" /></span>
						</div>
						<p class="board-message"><c:out value="${reply.content}" /></p>
						<c:if test="${admin}">
							<form class="board-actions" method="post" action="<c:url value='/askBoard/commentDelete' />" onsubmit="return confirm('해당 답변을 삭제하시겠습니까?')">
								<input type="hidden" name="no" value="${reply.no}">
								<input type="hidden" name="askNo" value="${askNo}">
								<button type="submit" class="btn btn-outline-danger btn-sm">답변 삭제</button>
							</form>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
</section>
