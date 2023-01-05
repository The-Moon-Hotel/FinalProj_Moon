<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="../css/room_main.css">


<section style="width: 1900px; align: center;">
	<article>
<div class="container text-center">
  <div class="row">
    <div class="col">
      <!-- 디럭스 -->
      	<div class="card" style="width: 18rem;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">디럭스</h5>
		    <p class="card-text">이국적인 느낌의 안락한 객실</p>
		    <br>
		    <p class="card-text">전망: 바다(전면, 측면)</p>
		    <p class="card-text">위치: 본관 2~5층, 6~10층</p>
		    <p class="card-text">객실크기: 36m<sub>2</sub></p>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
      <!-- 디럭스 끝 -->
    </div>
    <div class="col">
      <!-- 슈페리어 -->
      	<div class="card" style="width: 18rem;">
		  <img src="supeDetail.jsp" class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">슈페리어</h5>
		    <p class="card-text">편안하고 아늑한 가장 기본적인 객실</p>
		    <br>
		    <p class="card-text">전망: 바다(측면)</p>
		    <p class="card-text">위치: 본관 2~5층</p>
		    <p class="card-text">객실크기: 36m<sub>2</sub></p>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
      <!-- 슈페리어 끝 -->
    </div>
    <div class="col">
      <!-- 스위트 -->
      	<div class="card" style="width: 18rem;">
		  <img src="../images/spr.png" class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">스위트</h5>
		    <p class="card-text">자유로운 공간감이 느껴지는 객실</p>
		    <br>
		    <p class="card-text">전망: 바다</p>
		    <p class="card-text">위치: 본관 2~10층</p>
		    <p class="card-text">객실크기: 56m<sub>2</sub></p>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
      <!-- 스위트 끝 -->
    </div>
    <div class="col">
      <!-- 모노그램 스위트 -->
      	<div class="card" style="width: 18rem;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">모노그램스위트</h5>
		    <p class="card-text"> 파라마노처럼 펼처진 바다를 바라볼 수 있는 객실</p>
		    <br>
		    <p class="card-text">전망: 바다</p>
		    <p class="card-text">위치: 본관 7~10층</p>
		    <p class="card-text">객실크기: 72m<sub>2</sub></p>
		    <a href="#" class="btn btn-primary">Go somewhere</a>
		  </div>
		</div>
      <!-- 모노그램 스위트 끝 -->
    </div>
  </div>
</div>
		<div class="carousel-item active ">
			<img src="../images/room_t.png" class="d-block  CarouselImage  "
				alt="..." style="width: 1900px; height: 600px">

			<!-- 객실안내 -->
			<div class="rooms">
				<p>
					<b>객실안내</b>
				</p>
				<br>
				<div class="superior">
					<img src="../images/spr.png">
					<p class="roomtype">
						<b>수페리어</b>
					</p>
					<a href="/room/supeDetail"> <input type="button" class="look"
						value="상세보기" style="left: 520px; top: 420px;">
					</a>
					<pre>   편안하고 아늑한 가장 기본적인 객실
    			
   전망: 바다(측면)
   위치: 본관 2~5층
   객실크기: 36m<sub>2</sub>
					</pre>
				</div>
				<div class="deluxe">
					<img src="../images/dex.png">
					<p class="roomtype">
						<b>디럭스</b>
					</p>
					<a href="/room/deluxeDetail"> <input type="button" class="look"
						value="상세보기" style="right: 8px; top: 310px;">
					</a>
					<pre>   이국적인 느낌의 안락한 객실
    			
   전망: 바다(전면, 측면)
   위치: 본관 2~5층, 6~10층
   객실크기: 36m<sub>2</sub>
					</pre>
				</div>
				<br>

				<div class="suite">
					<img src="../images/room-suite.png">
					<p class="roomtype">
						<b>스위트룸</b>
					</p>
					<a href="/room/suitDetail"> <input type="button" class="look"
						value="상세보기" style="left: 520px; bottom: 150px;">
					</a>
					<pre>   여유로운 공간감이 느껴지는 객실	
    			
   전망: 바다
   위치: 본관 2~10층
   객실크기: 56m<sub>2</sub>
					</pre>
				</div>

				<div class="monogramsuit">
					<img src="../images/monogram.png">
					<p class="roomtype">
						<b>모노그램 스위트</b>
					</p>
					<a href="/room/monoDetail"> <input type="button" class="look"
						value="상세보기" style="right: 8px; bottom: 150px;">
					</a>
					<pre>   파라마노처럼 펼처진 바다를 바라볼 수 있는 객실
    			
   
					</pre>
				</div>


			</div>

		</div>


	</article>
</section>
<div style="height: 2000px"></div>
<jsp:include page="../inc/footer.jsp"></jsp:include>