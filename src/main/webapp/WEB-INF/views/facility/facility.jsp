<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../inc/top.jsp"></jsp:include>
<link rel="stylesheet" href="../css/facility.css">

<section class = "faci">
	<span>
		<a href="<%=request.getContextPath() %>/facility/facility.jsp">
			<input type="button" value="부대시설" class="btn btn-dark">
		</a>
		<a href="<%=request.getContextPath() %>/facility/dining.jsp">
			<input type="button" value="다이닝" class="btn btn-dark">	
		</a>
		<!-- style="background:#7B725E;color:white" -->
	</span>
	<br><br>
	<div class="swim">
		<h3><b>아름다운 자연 안에서 즐기는 아늑한 휴식</b></h3><br>
		<p style="font-size: 17px">끝없이 펼쳐지는 바다를 바라보며 즐기는 여유로운 수영과 가벼운 칵테일 한잔.<br>
		생기 넘치는 하루를 만드는 피트니스에서의 활기찬 시작과, 사우나에서 보내는 재충전의 시간을 선사합니다</p>
		<br><br>

		<p class="title"><b>야외수영장</b></p>
		<img src="../images/swimmingpool.png" 
			style="cursor:pointer" 
			onclick="window.open('swimm.jsp','asdfo8or','scrollbars=yes, width=900,height=700,top=10,left=20');"> 
		<p>
			<b>따라로운 햇살 아래 여유로운 휴식</b>
		</p>
		<p class="conten">끝없이 펼쳐지는 수평선을 바라보며 프라이빗하고 여유로운 휴식을 즐기실 수 있습니다.</p>

		<br><br>
		<hr><br>
	</div>
	
	<div>		
		<span class="title">
			<p>
				<b>테라피 가든</b>
				<b style="margin-left: 450px">카바나</b>
			</p>
		<img src = "../images/tera.png" style=" position: absolute;
			cursor:pointer" 
			onclick="window.open('tera.jsp','asdfo8or','scrollbars=yes, width=900,height=300,top=10,left=20');">	
		<img src = "../images/cabanaa.png" style="margin-left: 560px;cursor:pointer" 
			onclick="window.open('cabana.jsp','asdfo8or','scrollbars=yes, width=900,height=600,top=10,left=20');">
		<br>
		<span class="conten" style="font-size: 15px;">대나무숲 그늘 아래 편안한 휴식</span>
		<span style="font-size: 15px; margin-left: 340px" class="conten">소중한 사람들과 함께하는 오붓한 시간</span><br>
	
		
		<br><br>
		<hr><br>
		</span>
	</div>
	
	<div>
		<span class="title">
			<p>
				<b>사우나</b>
				<b style="margin-left: 495px">헬스장</b>
			</p>
		<img src = "../images/saun.png" style=" position: absolute;
			cursor:pointer" 
			onclick="window.open('sauna.jsp','asdfo8or','scrollbars=yes, width=900,height=300,top=10,left=20');">
			
		<img src = "../images/gym.png" style="margin-left: 560px;  cursor:pointer" 
			onclick="window.open('gym.jsp','asdfo8or','scrollbars=yes, width=900,height=400,top=10,left=20');">
		<br>
		<span class="conten" style="font-size: 15px;">편안함과 피로해소를 위한 실내 사우나</span>
		<span style="margin-left: 300px; font-size: 15px;" class="conten">최상의 건강과 체계적인 체력관리를 위한 특별한 공간</span><br>

		
		<br><br>
		<hr><br>
		</span>
	</div>	
	
	<div class="swim">
		<p class="title">
			<b>모노그램 라운지</b>
		</p>
		<img src="../images/loun.png" style="cursor:pointer" 
			onclick="window.open('lounge.jsp','asdfo8or','scrollbars=yes, width=900,height=400,top=10,left=20');">
		<p class="conten">다양한 로컬 문화를 경험할 수 있는 라이프스타일 라운지</p>

	</div>	
</section>

<div style="height: 100px"></div>
<jsp:include page="../inc/footer.jsp" ></jsp:include>