<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/inc/top"></c:import>
<link rel="stylesheet" href="../css/roomDetail.css">


<section style="width: 1900px; align: center;">
	<article>
		<div class="carousel-item active ">
			<img src="../images/hotel1.png" class="d-block  CarouselImage  "
				alt="..." style="width: 1900px; height: 600px">
			
			
			<div class="rooms">
				<p><b>스위트</b></p>
				<br>
					
					<img src="../images/suit_big.png" style="width: 1200px;">

				<br><br><br>
				

				<div class = "explain" style="font-size: 20px">
					<p style="font-size: 30px" >자연경관이 한눈에 들어오는 스위트 룸</p>
					<p style="font-weight: bold;color: black">야외 수영장, 끝없이 펼쳐지는 해변을 조망할 수 있는 객실입니다.</p>
					<br>
					<문의전화>
					<span style="color: black"> 02-3333-6666</span>
					<br><br>
					<p>여유로운 공간감이 느껴지며, 객실 어디에서나 바다의 경관을 즐길 수 있는 객실입니다. </p>

				
				<br>
				
					<pre style="color: black">위치  본관 2~10층  |  전망  바다  |  침대  더블,트윈  |  크기  56m<sub>2</sub>
룸구성  침실1, 욕실1, 거실1</pre>
				<br>
				<input type="button" value="객실 어메니티 보기" 
					class="amenity" style="background:#7B725E;color:white"; cursor:pointer
			onclick="window.open('/room/amenity','asdfo8or','scrollbars=yes, width=750,height=580,top=250,left=800');">
				</div>
				
				
				<br>
				<div style="font-size: 20px">
					<hr style="width: 900px;">
					<label>객실이용</label>
					<ul>
						<li>TV위성채널정보 보기</li>
						<li>초고속 유·무선(wifi) 인터넷</li>
						<li>220V 전압 사용 가능</li>
						<li>커피, 디카페인 커피, 로컬 티 무료 제공</li>
						<li>엑스트라 베드 1개 추가 가능(유료)</li>
					</ul>
					<hr style="width: 900px;">
					<label>부대시설</label>
					<ul>
						<li>실내 체육관 이용(24시간)</li>
							- 타월 및 생수 구비, 운동복 및 운동화는 제공하지 않습니다.
						<li>수영장 이용</li>
							- 샌드 & 유아 풀(수심 0.3m) 07:00∼19:00<br>
							- 키즈 풀(수심 0.8m) 07:00∼19:00<br>
							- 패밀리 풀(수심 1.2m) 07:00∼22:00<br>
							- 인피니티 성인 전용 풀(수심 1.2m) 07:00∼22:00
						<li>투숙 기간 내 무료 주차 가능</li>
						<li>유아 동반 고객은 유아 관련 필요 물품을 무료로 대여하실 수 있습니다.</li>
							- 대여 가능한 유아 용품 : 유모차, 아기침대, 침대 안전가드, 아기의자,
							아기욕조, 유아발판 디딤대
					</ul>
					
					<hr style="width: 900px;">
					<label>룸서비스</label>
					<ul>
						<li>객실에서 즐기실 수 있는 다양한 룸서비스 메뉴가 준비되어 있습니다.(24시간)</li>
					</ul>	
					<hr style="width: 900px;">				
				</div>
				
				<div class="right_box"> 
					<a href ="/askBoard/askWrite">
						<input type="button" value="문의하기" style="background:#7B725E;color:white; margin-top: 50px;" >
					</a>
					<br><br>
					<table  style="border-collapse: collapse;background:#E6E6E6;">
						<tr>
							<td>
							<b>Hotel Info.</b>
							<br><br>
							<ul>
								<il><b>조식 뷔페 이용 안내</b></il><br>
									- 시간 : 06:30 ~ 10:30<br>
									- 장소 : 다이닝 M (본관 B1층)<br><br>
								<il><b>체크인/체크아웃 시간</b></il><br>
									- 체크인 : 오후 3시 이후<br>
									- 체크아웃 : 12시<br><br>
								<il><b>취소/변경 및 노쇼(No-show) 안내</b></il><br>
									- 숙박 예정일 2일 전 : 위약금 없음<br>
									- 숙박 예정일 1일 전 이후 취소 및 변경, 노쇼<br>
									 (No-show) 시 : 최초 1박 요금의 100% 위약금 부과<br>
							</ul>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</article>
</section>


<div style="height: 2800px"></div>
<%@ include file="../inc/footer.jsp" %>
