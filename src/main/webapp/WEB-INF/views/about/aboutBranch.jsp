<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" type="text/css" href="../css/aboutBranch.css">
<section class="branchBanner">
	<article>
		<div>
			<img alt="" src="../images/greetings_banner.png" >
		</div>
	</article>
</section>
<div class="container text-center branchTitle">
		<h1>지점별 소개</h1>
</div>
<section class="content">
<article>
	
	<div class="container text-left">
		<div class="row">
			<div class="col col-lg-6 col-md-12 card card-body" style=" border:0px">
				<p class="card-text">1st</p>
				<h2 class="card-title text-center">Full Moon Hotel</h2>
				<br>
				<h5 class="card-subtitle mb-6 text-muted">현대적인 라이프스타일 데스티네이션</h5>
				<br>
				<p class="card-text">한국의 전통미와 현대적 감각을 겸비하고 있는 세계 속의 명문호텔
					The Moon Hotel은 세계 최고의 럭셔리 어번 ()Urban) 라이프스타일 호텔로 고객들에게 최고급 Hospitality 서비스를
					제공합니다. 한국을 대표하는 럭셔리 호텔로서 '일상이 최고의 순간이 되는 곳' 이라는 컨셉을 새롭게 선보이며 휴식은 물론
					고급 식문화, 예술, 부티, 쇼핑, 웨딩, 엔터테인먼트 등 고객의 고품격 라이프스타일을 제안하는 공간으로 거듭냐고
					있습니다.</p>
				<br>
				<h5 class="card-subtitle mb-6 text-muted">심장부의 우아하고 스타일리시한 매력</h5>
				<br>
				<p class="card-text">역사적인 왕궁 유적들 사이 유리와 철조로 지어진 건축물을 품은 서울은
					전통적인 느낌이 물씬한 동시에 미래 지향적인 감각이 가득한 역동적인 도시입니다. 바닥에서 천장까지 통유리가 설치된
					객실에서 밖을 내다보면, 서울의 역동적인 풍경이 한눈에 펼쳐집니다. 광화문에 위치한 현대적인 감각의 Full Moon Hotel
					만의 서비스를 선보이는 특별한 공간입니다. 도심 속 스파에서 완벽한 고요와 평화를 만끽하시고 사계절
					운영되는 수영장에서 휴식을 취하신 다음, 유유안의 광동식 요리를 맛보세요. 최고급 칵테일로 유명한 찰스 H.바로
					오시면, 흥미진진한 스토리가 있는 특별한 칵테일을 즐기실 수 있습니다.</p>

			</div>
			<div class="col col-lg-6 col-md-12 col-sm-12 col-xs-12 card" style="border:0px; ">
				<br>
				<img src="../images/fullMoon.jpg" class="card-img-top contentImg" alt="...">
				<br>
			</div>
			<div class="col col-12 card" style="border:0px; ">
				<br>
				<div id="map1" style="width:100%;height:350px;"></div>

				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=93bf7c10cf6280b4b491009456fe786b"></script>
				<script>
				var mapContainer = document.getElementById('map1'), // 지도를 표시할 div 
				    mapOption = { 
				        center: new kakao.maps.LatLng(37.557689, 127.007634), // 지도의 중심좌표
				        //draggable: false,//지도고정
				        level: 5 // 지도의 확대 레벨
				    };
				
				var map1 = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 마커가 표시될 위치입니다 
				var markerPosition  = new kakao.maps.LatLng(37.557689, 127.007634); 
				
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
				    position: markerPosition
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map1);
				</script>
				<br>
			</div>
			<div class="col col-lg-6 col-md-12 col-sm-12 col-xs-12 card" style="border:0px; ">
				<br>
				<img src="../images/halfMoon.jpg" class="card-img-top contentImg" alt="...">
				<br>
			</div>
			<div class="col col-lg-6 col-md-12 card card-body" style=" border:0px">
				<p class="card-text">2nd</p>
				<h2 class="card-title text-center">Half Moon Hotel</h2>
				<br>
				<h5 class="card-subtitle mb-2 text-muted">글로벌 럭셔리 호텔</h5>
				<br>
				<p class="card-text">LHW의 멤버로 세계 럭셔리 호텔들과 어깨를 나란히 하고 있는 The Moon Hotel은
					고객 한 분 한 분을 배려하는 기품있는 서비스로 세계 각국의 수반들과 외고, 경제, 문화 등의 분야의 명사로부터 찬사를
					받고 있으며, Conde Nast Traveler, Travel & Leisure, Zagat, Euromoney등
					권위 있는 해외 매체로부터 매년 한국 최고의 호텔로 선정되고 있습니다.</p>
				<br>
				<h5 class="card-subtitle mb-2 text-muted">당신을 위한 서울의 진미를 발견해보세요</h5>
				<br>
				<p class="card-text">예술성에 의해 창조되고, 취향에 따라 움직이며, 연결로 인해 풍요로워집니다.
					르 코메디안(Le Comedian)을 마시며 사랑하는 사람들과의 재회를 계획해보세요. 찰스 H.의 르 코메디안은
					마우리치오 카텔란의 2019 아트 바젤 작품에 고개를 끄덕여 마이애미에 전통의 대한 경의를 표합니다.</p>
			</div>
			<div class="col col-12 card" style="border:0px; ">
				<br>
				<div id="map2" style="width:100%;height:350px;"></div>

				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=93bf7c10cf6280b4b491009456fe786b"></script>
				<script>
				var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
				    mapOption = { 
				        center: new kakao.maps.LatLng(37.542765, 126.949617), // 지도의 중심좌표
				        //draggable: false,//지도고정
				        level: 5 // 지도의 확대 레벨
				    };
				
				var map2 = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 마커가 표시될 위치입니다 
				var markerPosition  = new kakao.maps.LatLng(37.542765, 126.949617); 
				
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
				    position: markerPosition
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map2);
				</script>
				<br>
			</div>
			<div class="col col-lg-6 col-md-12 card card-body" style=" border:0px">
				<p class="card-text">3rd</p>
				<h2 class="card-title text-center">Crescent Moon Hotel</h2>
				<br>
				<h5 class="card-subtitle mb-6 text-muted">현대적인 라이프스타일 데스티네이션</h5>
				<br>
				<p class="card-text">한국의 전통미와 현대적 감각을 겸비하고 있는 세계 속의 명문호텔
					The Moon Hotel은 세계 최고의 럭셔리 어번 ()Urban) 라이프스타일 호텔로 고객들에게 최고급 Hospitality 서비스를
					제공합니다. 한국을 대표하는 럭셔리 호텔로서 '일상이 최고의 순간이 되는 곳' 이라는 컨셉을 새롭게 선보이며 휴식은 물론
					고급 식문화, 예술, 부티, 쇼핑, 웨딩, 엔터테인먼트 등 고객의 고품격 라이프스타일을 제안하는 공간으로 거듭냐고
					있습니다.</p>
				<br>
				<h5 class="card-subtitle mb-6 text-muted">고객의 품위와 고객에 대한 존중을 보장</h5>
				<br>
				<p class="card-text">Crescent Moon Hotel의 역사가 고객의 품위와 고객에 대한 존중을 보장합니다.
				Crescent Moon Hotel은 섬세하고 품격 있는 시설과 서비스로 비교할 수 없는 명성과 대표성을 가지고 있습니다.
				전 세계 어디에서나 보증된 서비스와 시설을 제공하고 있으며 고객에게 변함없는 만족감을 선사하고 있습니다.
				품격 높은 삶을 즐기는 당신의 여유와 자긍심에 걸맞는 세심한 서비스로 배려와 감동을 약속합니다.</p>
				
			</div>
			<div class="col col-lg-6 col-md-12 col-sm-12 col-xs-12 card" style="border:0px; ">
				<br>
				<img src="../images/crescentMoon.jpg" class="card-img-top contentImg" alt="...">
				<br>
			</div>
			<div class="col col-12 card" style="border:0px; ">
				<br>
				<div id="map3" style="width:100%;height:350px;"></div>

				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=93bf7c10cf6280b4b491009456fe786b"></script>
				<script>
				var mapContainer = document.getElementById('map3'), // 지도를 표시할 div 
				    mapOption = { 
				        center: new kakao.maps.LatLng(37.489343, 127.030149), // 지도의 중심좌표
				        //draggable: false,//지도고정
				        level: 5 // 지도의 확대 레벨
				    };
				
				var map3 = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 마커가 표시될 위치입니다 
				var markerPosition  = new kakao.maps.LatLng(37.489343, 127.030149); 
				
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
				    position: markerPosition
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map3);
				</script>
				<br>
			</div>
		</div>
	</div>
</article>

</section>
<jsp:include page="../inc/footer.jsp"></jsp:include>