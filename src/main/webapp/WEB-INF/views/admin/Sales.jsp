<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" type="text/css" href="../css/sales.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('form[name=SalseCk]').submit(function(){
			var branchChk=$('#branchType option:selected').val();
			var salesChk=$('#salesType option:selected').val();
			if(branchChk=='0'){
				alert("조회할 지점을 선택하세요");
				$(this).focus();s
				event.preventDefault();
			}else if(salesChk=='0'){
				alert("조회목록을 선택하세요");
				$(this).focus();
				event.preventDefault();
			}else if($('#date1').val().length<1){
				alert("조회할 기간을 선택하세요");
				$(this).focus();
				event.preventDefault();
			}else if($('#date2').val().length<1){
				alert("조회할 기간을 선택하세요");
				$(this).focus();
				event.preventDefault();
			}
		});
	});
</script>
<section class="salesBanner">
	<article>
		<div>
		  <img src="../images/resevBanner.png" class="" alt="...">
		</div>
	</article>
</section>
		<div class="container text-center salesTitle">
			<h1>매출조회</h1>
		</div>
<section class="content">
		<div>
			<form name="SalseCk" method="post" action="Sales.jsp"> 
				<fieldset>
					<div class="ck">
						<div>
							<p class="pSize" style="display: inline-block;">지점 : </p>
							<select class="form-select form-select-md mb-3 selectSize" id="branchType" name="SearchBranchType" style="display: inline-block;" >
							  	<option value="0">지점선택</option>
								<option value="Full Moon"
									<%if(branchType.equals("Full Moon")){ %>
							  			selected="selected"
							  		<%} %>
								>Full Moon</option>
								<option value="Half Moon"
									<%if(branchType.equals("Half Moon")){ %>
							  			selected="selected"
							  		<%} %>								
								>Half Moon</option>
								<option value="Crescent Moon"
									<%if(branchType.equals("Crescent Moon")){ %>
							  			selected="selected"
							  		<%} %>								
								>Crescent Moon</option>
								<option value="all"
									<%if(branchType.equals("all")){ %>
							  			selected="selected"
							  		<%} %>								
								>전체지점</option>
							</select>
							<p class="pSize" style="display: inline-block;">조회목록 : </p>
							<select class="form-select form-select-md mb-3 selectSize" id="salesType" name="SearchSalesType" style="display: inline-block;" >
							  	<option value="0">조회 목록</option>
								<option value="1"
									<%if(salesType.equals("1")){ %>
							  			selected="selected"
							  		<%} %>								
								>타입별 매출</option>
								<option value="2"
									<%if(salesType.equals("2")){ %>
							  			selected="selected"
							  		<%} %>	
								>부대시설별 매출</option>
								<option value="3"
									<%if(salesType.equals("3")){ %>
							  			selected="selected"
							  		<%} %>	
								>모든 항목 매출</option>
							</select>
						</div>
							<p class="pSize" style="display: inline-block;">조회기간 : </p>
						<div class="input-group mb-3 dateSize" style="display: inline-block;width: 311px;">
						  	<input type="date" class="form-control " style="width: 100%" name="searchDate1" id="date1" value="<%=date1 %>">
						</div>
							<p class="pSize" style="display: inline-block; width: 30px">&nbsp;~&nbsp;</p>
						<div class="input-group mb-3 dateSize" style="display: inline-block; width: 311px;">
						  	<input type="date" class="form-control" style="width: 100%" name="searchDate2" id="date2" value="<%=date2 %>">
						</div>
						<div style="text-align:center ;display: block;">
							<button type="submit" class="btn btn-dark" style="width: 100px">조회</button>
						</div>
					</div>
				</fieldset>
				<div class="tableSize">
				<%if(st==1 || st==3){ %>
					<table class="table">
						<thead>
							<tr>							
								<th scope="col">날짜</th>
								<th scope="col">지점</th>
								<th scope="col">타입</th>
								<th scope="col">단가</th>
								<th scope="col">수량</th>
								<th scope="col">합계</th>								
							</tr>
							
						 	<%for(int i=0; i<list.size(); i++){
						 		SalesVO2 vo = list.get(i);	 
						 		sum += vo.getRoom_total_Price();%>
						<tbody>
							<tr>
								<td><%=vo.getCi_date() %></td>
								<td><%=vo.getLocName() %></td>	
								<td><%=vo.getRoomType() %></td>							
								<td><%=vo.getRoomPrice() %></td>								
								<td><%=vo.getQuantity() %></td>								
								<td><%=vo.getRoom_total_Price() %> 원</td>								
							</tr>
						</tbody>
						<%} %>
						<tfoot>
							<th colspan="5">총 매출</th>
							<th>
								<%=sum%> 원					
							</th>
						</tfoot>
					</table>
					<%}//if %>
					
					
					<%if(st==2){ %>
						<table class="table">
						<thead>
							<tr>							
								<th scope="col">날짜</th>
								<th scope="col">지점</th>
								<th scope="col">시설명</th>
								<th scope="col">성인수</th>
								<th scope="col">아동수</th>
								<th scope="col">성인가격</th>								
								<th scope="col">아동가격</th>															
							</tr>
							
						 	<%for(int i=0; i<list1.size(); i++){
						 		SalesVO2 vo = list1.get(i);	 
						 		sum1 += vo.getFac_Adult_Price(); 
						 		sum1 += vo.getFac_kids_Price();%>
						<tbody>
							<tr>
								<td><%=vo.getCi_date() %></td>
								<td><%=vo.getLocName() %></td>	
								<td><%=vo.getFacname() %></td>							
								<td><%=vo.getFac_adultNo() %></td>								
								<td><%=vo.getFac_kidsNo() %></td>								
								<td><%=vo.getFac_Adult_Price() %></td>								
								<td><%=vo.getFac_kids_Price() %></td>																
							</tr>
						</tbody>
						<%} %>
						<tfoot>
							<th colspan="6">총 매출</th>
							<th>
								<%=sum1%> 원					
							</th>
						</tfoot>
					</table>
					
					<%}else if(st==3){%>
						<div style="height:50px"></div>	
						<table class="table">
						<thead>
							<tr>							
								<th scope="col">날짜</th>
								<th scope="col">지점</th>
								<th scope="col">시설명</th>
								<th scope="col">성인수</th>
								<th scope="col">아동수</th>
								<th scope="col">성인가격</th>								
								<th scope="col">아동가격</th>															
							</tr>
							
						 	<%for(int i=0; i<list1.size(); i++){
						 		SalesVO2 vo = list1.get(i);	 
						 		sum1 += vo.getFac_Adult_Price(); 
						 		sum1 += vo.getFac_kids_Price();%>
						<tbody>
							<tr>
								<td><%=vo.getCi_date() %></td>
								<td><%=vo.getLocName() %></td>	
								<td><%=vo.getFacname() %></td>							
								<td><%=vo.getFac_adultNo() %></td>								
								<td><%=vo.getFac_kidsNo() %></td>								
								<td><%=vo.getFac_Adult_Price() %></td>								
								<td><%=vo.getFac_kids_Price() %></td>																
							</tr>
						
						<%} %>
							<th colspan="5">
							<th>총 매출</th>
							<th>
								<%=sum1%> 원					
							</th>
						</tbody>
						<tfoot>
							<th colspan="5">
							<th>
								객실&부대시설 매출 합계</th>
							<th><%=(sum + sum1) %> 원</th>
						</tfoot>
					</table>
					<%}//if %>		
				</div>
			</form>
		</div>
</section>
<jsp:include page="../inc/footer.jsp"></jsp:include>