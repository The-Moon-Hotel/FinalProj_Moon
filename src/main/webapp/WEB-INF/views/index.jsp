<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <jsp:include page="inc/top.jsp"></jsp:include> --%>
<link rel="stylesheet" href="css/index.css">
<section class="CarouselSize CarouselLocation " >
	<article>
	<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="false">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <!-- 사진 -->
  <div class="carousel-inner">
    <div class="carousel-item active ">
      <img src="images/hotel1.png" class=" CarouselImage text-center" alt="...">
      <div class="carousel-caption d-none d-md-block contentFont">
        <h3>Full Moon Hotel</h3>
        <p>현대적인 라이프스타일 데스티네이션</p>
        <p>심장부의 우아하고 스타일리시한 매력</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="images/hotel2.png" class=" CarouselImage text-center" alt="...">
      <div class="carousel-caption d-none d-md-block contentFont">
        <h3>Half Moon Hotel</h3>
        <p>글로벌 럭셔리 호텔</p>
        <p>당신을 위한 서울의 진미를 발견해보세요</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="images/hotel3.png" class=" CarouselImage text-center" alt="...">
      <div class="carousel-caption d-none d-md-block contentFont">
        <h3>Crescent Moon Hotel</h3>
        <p>현대적인 라이프스타일 데스티네이션</p>
        <p>고객의 품위와 고객에 대한 존중을 보장</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
	</article>
</section>
<section style="margin-top: 50px">
	<article>
	<div class="container text-left" >
	  <div class="row" >
	      <!-- 디럭스 -->
	    <div class="col col-sm-12 col-md-6 card" style="width: 600px;margin: 25px;border: 0px;">
		  <img src="images/dex.png" class="card-img-top" alt="...">
		  <div class="card-body contentFont"  style="font-family: 'Do Hyeon', sans-serif;">
		    <h2 class="card-title">디럭스</h2>
		    <h6 class="card-subtitle mb-2 text-muted  ">이국적인 느낌의 안락한 객실</h6>
		    <p class="card-text  ">전망: 바다(전면, 측면)</p>
		    <p class="card-text  ">위치: 본관 2~5층, 6~10층</p>
		    <p class="card-text  ">객실크기: 36m<sup>2</sup></p>
		    <a href="<%=request.getContextPath()%>/room/deluxeDetail.jsp" class="btn btn-dark ">자세히 보기</a>
		    
		  </div>			
	    </div>
	      <!-- 디럭스 끝 -->
	      <!-- 슈페리어 -->
	      	<div class="col col-sm-12 col-md-6 card" style="width: 600px;margin: 25px;border: 0px;">
			  <img src="images/spr.png" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h2 class="card-title ">슈페리어</h2>
			    <h6 class="card-subtitle mb-2 text-muted ">편안하고 아늑한 가장 기본적인 객실</h6>
			    <p class="card-text ">전망: 바다(측면)</p>
			    <p class="card-text ">위치: 본관 2~5층</p>
			    <p class="card-text ">객실크기: 36m<sup>2</sup></p>
		    <a href="<%=request.getContextPath()%>/room/supeDetail.jsp" class="btn btn-dark ">자세히 보기</a>
			  </div>
			</div>
	      <!-- 슈페리어 끝 -->
	      <!-- 스위트 -->
	      	<div class="col col-sm-12 col-md-6 card" style="width: 600px;margin: 25px;border: 0px;">
			  <img src="images/room-suite.png" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h2 class="card-title ">스위트</h2>
			    <h6 class="card-subtitle mb-2 text-muted">자유로운 공간감이 느껴지는 객실</h6>
			    <p class="card-text ">전망: 바다</p>
			    <p class="card-text ">위치: 본관 2~10층</p>
			    <p class="card-text ">객실크기: 56m<sup>2</sup></p>
		    <a href="<%=request.getContextPath()%>/room/suitDetail.jsp" class="btn btn-dark ">자세히 보기</a>
			  </div>
			</div>
	      <!-- 스위트 끝 -->
	      <!-- 모노그램 스위트 -->
	      	<div class="col col-sm-12 col-md-6 card" style="width: 600px;margin: 25px;border: 0px;">
			  <img src="images/monogram.png" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h2 class="card-title " >모노그램스위트</h2>
			    <h6 class="card-subtitle mb-2 text-muted "> 파라마노처럼 펼처진 바다를 바라볼 수 있는 객실</h6>
			    <p class="card-text " >전망: 바다</p>
			    <p class="card-text ">위치: 본관 7~10층</p>
			    <p class="card-text ">객실크기: 72m<sup>2</sup></p>
		    <a href="<%=request.getContextPath()%>/room/monoDetail.jsp" class="btn btn-dark ">자세히 보기</a>
			  </div>
			</div>
	      <!-- 모노그램 스위트 끝 -->
	  </div>
	</div>
	</article>
</section>
<%-- <jsp:include page="inc/footer.jsp" ></jsp:include> --%>