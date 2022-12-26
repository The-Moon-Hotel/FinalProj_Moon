<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/top.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/footer.css">  
  <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.6.1.min.js"></script>
  <script type="text/javascript">
  	 var navBar = document.getElementById('navBlack');
  	 
  	window.addEventListener('scroll',function(){
  		var value=window.scrollY;
  		console.log('scrollY',scrollY);
  		if(value>100){
  			$('#navBlack').css({
  				visibility: 'visible',
  				animation:'slide 1s ease-out forwards'
  			});  		
  		}else {
  			$('#navBlack').css({
  				visibility: 'hidden',
  				animation:'disapper 1s ease-out forwards'
  			});
  			
  		}
  	});
  	
  	$(function(){
  		var resevUserid="${t_userid}";
  		$('#bookBtn1').click(function(){
  			if(resevUserid==""){
  				alert("예약을 하려면 로그인이 필요합니다.");
  				event.preventDefault();
  			}else{  				
  				$(location).attr("href", "<%=request.getContextPath() %>/reservRoom/reservation1");
  			}
  		});
  		$('#bookBtn2').click(function(){
  			if(resevUserid==""){
  				alert("예약을 하려면 로그인이 필요합니다.");
  				event.preventDefault();
  			}else{  				
  				$(location).attr("href", "<%=request.getContextPath() %>/reservRoom/reservation1");
  			}
  		});
  	})
  </script>
  </head>
<body>
<header>
</header>
<nav class="navbar navbar-expand-md navWhite" style="background: rgba(0, 0, 0, 0.1) ; height:70px;">
	
  <div class="container-fluid" >
        <a class="navbar-brand navColor" href="/" >THE MOON HOTEL</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">소개</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/about/greetings">인사말</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/about/aboutBranch">지점별 정보</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            객실소개
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/deluxeDetail">디럭스</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/supeDetail">슈페리어</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/suitDetail">스위트</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/monoDetail">모노그램스위트</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link  navColor" href="<%=request.getContextPath() %>/facility/facility" role="button" aria-expanded="false">
            부대시설
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            게시판
          </a>
          <ul class="dropdown-menu">
          	<li><a class="dropdown-item" href="<%=request.getContextPath() %>/noticeBoard/noticeBoardList">공지사항</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/reviewBoard/reviewBoardList">리뷰게시판</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/askBoard/askBoardList">문의게시판</a></li>
          </ul>
        </li>
        <!-- 로고 -->
      </ul>
      <c:if test="${t_login==true }">
      	<c:choose >
      		<c:when test="${GuestOrAdmin==GuestSerivce.GUEST_ACCOUNT }">
      			<!-- 로그인시 보이게 설정 -->
		        <li class="nav-item dropdown nav-link me-3 mb-2 mb-lg-0 navColor" >
		          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            마이페이지
		          </a>
		          <ul class="dropdown-menu">
		          	<li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/myReservList">예약정보 확인</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/guestEdit_pwdChk">회원정보수정</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/askBoard/askBoardList">문의내역</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/guestOut">회원탈퇴</a></li>
		          </ul>
		        </li>
		      	<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/logout"  >로그아웃</a>
      		</c:when>
      		<c:otherwise>
      			<!-- 관리자 로그인시 보이게 설정 -->
		        <li class="nav-item dropdown nav-link me-3 mb-2 mb-lg-0 navColor" >
		          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            관리자페이지
		          </a>
		          <ul class="dropdown-menu">
		          	<li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/guestList">회원관리</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/reservList">예약정보조회</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/Sales">매출조회</a></li>
		          </ul>
		        </li>
		      	<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/logout"  >로그아웃</a>
      		</c:otherwise>
      </c:choose>
      </c:if>
      <c:if test="${t_login==false }">
      		<!-- 로그인 전  -->
      		<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/login"  >로그인</a>
      		<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor" href="<%=request.getContextPath() %>/guest/signUp">회원가입</a>
      </c:if>
      <form class="d-flex navColor" role="search" >
        <button class="btn btn-outline-primary" type="button" id="bookBtn1">book a room</button>
      </form>
    </div>
  </div>
</nav>
<!-- 움직이는 검은 메뉴바 -->
<nav class="navbar navbar-expand-md navBlack bg-dark" id="navBlack"style=" height:70px;">
	
  <div class="container-fluid" >
        <a class="navbar-brand navColor" href="/" >THE MOON HOTEL</a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            소개
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/about/greetings">인사말</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/about/aboutBranch">지점별 정보</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            객실소개
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/deluxeDetail">디럭스</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/supeDetail">슈페리어</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/suitDetail">스위트</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/room/monoDetail">모노그램스위트</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link  navColor" href="<%=request.getContextPath() %>/facility/facility" role="button" aria-expanded="false">
            부대시설
          </a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            게시판
          </a>
          <ul class="dropdown-menu dropdown-menu-dark">
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/noticeBoard/noticeBoardList">공지사항</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/reviewBoard/reviewBoardList">리뷰게시판</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/askBoard/askBoardList">문의게시판</a></li>
          </ul>
        </li>
        <!-- 로고 -->
      </ul>
      <c:if test="${t_login==true }">
      	<c:choose >
      		<c:when test="${GuestOrAdmin==GuestSerivce.GUEST_ACCOUNT }">
      			<!-- 로그인시 보이게 설정 -->
		        <li class="nav-item dropdown nav-link me-3 mb-2 mb-lg-0 navColor"  >
		          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            마이페이지
		          </a>
		          <ul class="dropdown-menu dropdown-menu-dark">
		          	<li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/myReservList">예약정보 확인</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/guestEdit_pwdChk">회원정보수정</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/askBoard/askBoardList">문의내역</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/guest/guestOut">회원탈퇴</a></li>
		          </ul>
		        </li>
		      	<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/logout" >로그아웃</a>
      		</c:when>
      		<c:otherwise>
      			<!-- 관리자 로그인시 보이게 설정 -->
		        <li class="nav-item dropdown nav-link me-3 mb-2 mb-lg-0 navColor" >
		          <a class="nav-link dropdown-toggle navColor" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            관리자페이지
		          </a>
		          <ul class="dropdown-menu dropdown-menu-dark">
		          	<li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/guestList">회원관리</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/reservList">예약정보조회</a></li>
		            <li><a class="dropdown-item" href="<%=request.getContextPath() %>/admin/Sales">매출조회</a></li>
		          </ul>
		        </li>
		      	<a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/logout"  >로그아웃</a>
			</c:otherwise>
      </c:choose>
      </c:if>
      <c:if test="${t_login==false }">
	      <!-- 로그인 전  -->      
		  <a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor"   href="<%=request.getContextPath() %>/login/login" >로그인</a>
		  <a class="nav-item nav-link me-3 mb-2 mb-lg-0 navColor" href="<%=request.getContextPath() %>/guest/signUp">회원가입</a>
      </c:if>
      <form class="d-flex navColor" role="search" >
        <button class="btn btn-outline-primary" type="button" id="bookBtn2">book a room</button>
      </form>
    </div>
  </div>
</nav>