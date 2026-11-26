<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="board-panel" aria-labelledby="inquiry-reply-heading">
	<header class="board-panel-header"><h2 id="inquiry-reply-heading">답변 작성</h2></header>
	<form method="post" action="<c:url value='/askBoard/commentWrite' />">
		<input type="hidden" name="askNo" value="${askNo}">
		<label class="visually-hidden" for="inquiry-reply">답변 내용</label>
		<textarea id="inquiry-reply" name="c_content" class="form-control" rows="5" placeholder="답변을 입력해 주세요." required></textarea>
		<div class="board-actions">
			<button type="submit" class="btn btn-dark">답변 등록</button>
		</div>
	</form>
</section>
