<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="/css/sales.css">



<style>
	table{
		border-collapse: collapse;
		width: 800px;
		margin-top: 30px;
		margin-left: 90px;
	}
	.co_list{
		width: 1000px;
		margin-left: 210px;
	}
	
	textarea{

	font-size: 1em; 
	width:600px; 
	height: 60px;
	overflow: hidden;
	}
</style>
<div class="co_list">
	<h4 style="margin-left: 90px">답글</h4>
	<div class="tableSize">
		<input type="hidden" id="askNo" name="askNo" value="${askNo}">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">작성자</th>
					<th scope="col">내용</th>
					<th scope="col">등록시간</th>
					<th></th>
				</tr>
				
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr>
							<td colspan="3" style="text-align: center">등록된 답변이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="vo" items="${list }">							
							<tr style="text-align: center">
								<td>${vo.name }</td>
								<td style="margin-left: 100px">
									<textarea readonly>${vo.content }</textarea>
								</td>
								<td><fmt:formatDate value="${vo.regdate }"
										pattern="yyyy-MM-dd" /></td>
								<c:if test="${sys != 1 }">
									<td>
										<input type="button" class="Delete" value="삭제">
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('.Delete').click(function(){
			if(!confirm('해당 답변을 삭제하시겠습니까?')){
				event.preventDefault();
			}else{
				location.href
					="/askBoard/commentDelete?no="/* +${vo.no} */;
			}
		});
	});
</script>