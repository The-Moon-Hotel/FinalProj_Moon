create database themoon;

use themoon;
##commit;

/* 예약 */
CREATE TABLE reservation (
	reservNo int NOT NULL, /* 예약번호 */
	guestNo int NOT NULL, /* 회원번호 */
	RoomNo int NOT NULL, /* 객실정보번호 */
	adult int DEFAULT 2 NOT NULL, /* 성인수 */
	kids int DEFAULT 0 NOT NULL, /* 아동수 */
	ci_date char(50) NOT NULL, /* 체크인날짜 */
	co_date char(50) NOT NULL, /* 체크아웃날짜 */
	totalPrice int NOT NULL /* 총금액 */
);

CREATE UNIQUE INDEX reservation
	ON reservation (
		reservNo ASC
	);

ALTER TABLE reservation
	ADD
		CONSTRAINT reservation
		PRIMARY KEY (
			reservNo
		);

/* 회원 */
CREATE TABLE guest (
	guestNo 	int unique auto_increment, /* 회원번호 */
	name 		char(50) NOT NULL, /* 이름 */
	userId 		char(50) NOT NULL, /* 아이디 */
	pwd 		char(50) NOT NULL, /* 비밀번호 */
	email 		char(50), /* 이메일 */
	tel 		char(50) NOT NULL, /* 전화번호 */
	joindate 	DATETIME  DEFAULT now() NOT NULL, /* 가입날짜 */
	outdate 	DATETIME , /* 탈퇴날짜 */
	sys 		int DEFAULT 1 NOT NULL /* 관리자여부 */
);

CREATE UNIQUE INDEX guest
	ON guest (
		guestNo ASC
	);

ALTER TABLE guest
	ADD
		CONSTRAINT guest
		PRIMARY KEY (
			guestNo
		);

insert into guest (name, userid, pwd, email, tel, joindate, sys)
values('관리자', 'admin', '1234', 'admin@naver.com', '010-1000-2000', now(), 2);

insert into guest ( name, userid, pwd, email, tel, joindate, sys)
values('홍길동', 'honghong', '1234', 'hong@naver.com', '010-1234-1234', now(), 1);

insert into guest (name, userid, pwd, email, tel, joindate, sys)
values('박권수', 'park', '1234', 'park@naver.com', '010-9478-6140', now(), 1);

insert into guest (name, userid, pwd, email, tel, joindate, sys)
values('김봉중', 'honghong', '1234', 'hong@naver.com', '010-1111-1111', now(), 1);


/* 후기게시판 */
CREATE TABLE reviewBoard (
	reviewNo int unique auto_increment, /* 후기번호 */
	guestNo int NOT NULL, /* 회원번호 */
	r_title char(50) NOT NULL, /* 제목 */
	r_content text, /* 내용 */
	r_regdate DATETIME  DEFAULT now() NOT NULL /* 작성일 */
);

CREATE UNIQUE INDEX reviewBoard
	ON reviewBoard (
		reviewNo ASC
	);

ALTER TABLE reviewBoard
	ADD
		CONSTRAINT reviewBoard
		PRIMARY KEY (
			reviewNo
		);

/* 문의게시판 */
CREATE TABLE askBoard (
	askNo int unique auto_increment, /* 문의번호 */
	guestNo int NOT NULL, /* 회원번호 */
	a_title char(50) NOT NULL, /* 제목 */
	a_content text NOT NULL, /* 문의내용 */
	a_regdate DATETIME  DEFAULT now() NOT NULL, /* 작성일 */
    fileName	char(50)			null,
    originalFilename char(50)			null,
    fileSize		int					null
);
select*  from askBoard;
/*
ALTER TABLE askBoard ADD fileName char(50);
ALTER TABLE askBoard ADD originalFilename char(50);
ALTER TABLE askBoard MODIFY  fileSize bigint;
*/
CREATE UNIQUE INDEX askBoard
	ON askBoard (
		askNo ASC
	);

ALTER TABLE askBoard
	ADD
		CONSTRAINT askBoard
		PRIMARY KEY (
			askNo
		);

/* 공지사항 */
CREATE TABLE noticeBoard (
	noticeNo int unique auto_increment, /* 공지사항번호 */
	guestNo int NOT NULL, /* 회원번호 */
	n_title char(50) NOT NULL, /* 제목 */
	n_content text NOT NULL, /* 내용 */
	n_regdate DATETIME  DEFAULT now() NOT NULL /* 작성일 */
);

CREATE UNIQUE INDEX noticeBoard
	ON noticeBoard (
		noticeNo ASC
	);

ALTER TABLE noticeBoard
	ADD
		CONSTRAINT noticeBoard
		PRIMARY KEY (
			noticeNo
		);

/* 부대시설 */
CREATE TABLE facility (
	facNo int unique auto_increment, /* 부대시설예약번호 */
	reservNo int, /* 예약번호 */
	facInfoNo int, /* 부대시설정보번호 */
	adultNo int, /* 성인수 */
	kidsNo int /* 아동수 */
);

CREATE UNIQUE INDEX facility
	ON facility (
		facNo ASC
	);

ALTER TABLE facility
	ADD
		CONSTRAINT facility
		PRIMARY KEY (
			facNo
		);

/* 지점 위치 */
CREATE TABLE location (
	locName char(50) NOT NULL, /* 지점명 */
	Address char(100) NOT NULL /* 위치 */
);

CREATE UNIQUE INDEX location
	ON location (
		locName ASC
	);

ALTER TABLE location
	ADD
		CONSTRAINT location
		PRIMARY KEY (
			locName
		);

insert into location (locName, Address)
values('Full Moon', '서울특별시 중구 동호로 249');  
insert into location (locName, Address)
values('Half Moon', '서울특별시 마포구 마포대로 83');  
insert into location (locName, Address)
values('Crescent Moon', '서울특별시 서초구 효령로 427');  

/* 부대시설정보 */
CREATE TABLE facilityInfo (
	facInfoNo int NOT NULL, /* 부대시설정보번호 */
	facName char(50) NOT NULL, /* 부대시설이름 */
	adultPrice int DEFAULT 0 NOT NULL, /* 성인가격 */
	kidsPrice int DEFAULT 0 NOT NULL /* 어린이가격 */
);

CREATE UNIQUE INDEX PK_facilityInfo
	ON facilityInfo (
		facInfoNo ASC
	);

ALTER TABLE facilityInfo
	ADD
		CONSTRAINT PK_facilityInfo
		PRIMARY KEY (
			facInfoNo
		);

insert into facilityInfo(facInfoNo, facName, adultPrice, kidsPrice)
values(1, '조식', 40000, 17000);
insert into facilityInfo(facInfoNo, facName, adultPrice, kidsPrice)
values(2, '수영장', 45000, 28000);
insert into facilityInfo(facInfoNo, facName, adultPrice, kidsPrice)
values(3, '사우나', 35000, 0);
insert into facilityInfo(facInfoNo, facName, adultPrice, kidsPrice)
values(4, '헬스장', 20000, 0);
select * from guest where userid ='admin';
/* 객실 */
CREATE TABLE Room (
	RoomNo int unique auto_increment, /* 객실정보번호 */
	locName char(50) NOT NULL, /* 지점명 */
	roomType char(50) NOT NULL, /* 객실타입 */
	roomPrice int NOT NULL, /* 객실가격 */
	roomImage char(100), /* 객실이미지 */
	roomCount int /* 객실수 */
);

CREATE UNIQUE INDEX PK_Room
	ON Room (
		RoomNo ASC
	);

ALTER TABLE Room
	ADD
		CONSTRAINT PK_Room
		PRIMARY KEY (
			RoomNo
		);

insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Full Moon', '디럭스', 240000, 'dex.png', 10); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Full Moon', '슈페리어', 280000, 'spr.png', 8); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Full Moon', '스위트', 400000, 'room-suite.png', 5); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Full Moon', '모노그램스위트', 550000, 'monogram.png', 2); 

insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Half Moon', '디럭스', 240000, 'dex.png', 10); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Half Moon', '슈페리어', 280000, 'spr.png', 8); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Half Moon', '스위트', 400000, 'room-suite.png', 5); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Half Moon', '모노그램스위트', 550000, 'monogram.png', 2); 

insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Crescent Moon', '디럭스', 240000, 'dex.png', 10); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Crescent Moon', '슈페리어', 280000, 'spr.png', 8); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Crescent Moon', '스위트', 400000, 'room-suite.png', 5); 
insert into room( locName, roomType, roomPrice, roomImage, roomCount)
values( 'Crescent Moon', '모노그램스위트', 550000, 'monogram.png', 2); 


/* 문의게시판 댓글 */
CREATE TABLE comments (
	no int unique auto_increment, /* 댓글번호 */
	askNo int NOT NULL, /* 문의번호 */
	name char(20) NOT NULL, /* 이름 */
	content text, /* 내용 */
	regdate DATETIME  DEFAULT now() NOT NULL /* 작성일 */
);

CREATE UNIQUE INDEX PK_comments
	ON comments (
		no ASC
	);

ALTER TABLE comments
	ADD
		CONSTRAINT PK_comments
		PRIMARY KEY (
			no
		);

ALTER TABLE reservation
	ADD
		CONSTRAINT FK_guest_TO_reservation
		FOREIGN KEY (
			guestNo
		)
		REFERENCES guest (
			guestNo
		);

ALTER TABLE reservation
	ADD
		CONSTRAINT FK_Room_TO_reservation
		FOREIGN KEY (
			RoomNo
		)
		REFERENCES Room (
			RoomNo
		);

ALTER TABLE reviewBoard
	ADD
		CONSTRAINT FK_guest_TO_reviewBoard
		FOREIGN KEY (
			guestNo
		)
		REFERENCES guest (
			guestNo
		);

ALTER TABLE askboard
	ADD
		CONSTRAINT FK_guest_TO_askBoard
		FOREIGN KEY (
			guestNo
		)
		REFERENCES guest (
			guestNo
		);

ALTER TABLE noticeBoard
	ADD
		CONSTRAINT FK_guest_TO_noticeBoard
		FOREIGN KEY (
			guestNo
		)
		REFERENCES guest (
			guestNo
		);

ALTER TABLE facility
	ADD
		CONSTRAINT FK_reservation_TO_facility
		FOREIGN KEY (
			reservNo
		)
		REFERENCES reservation (
			reservNo
		);

ALTER TABLE facility
   ADD
      CONSTRAINT FK_facilityInfo_TO_facility
      FOREIGN KEY (
         facInfoNo
      )
      REFERENCES facilityInfo (
         facInfoNo
      )
on delete cascade;

ALTER TABLE Room
	ADD
		CONSTRAINT FK_location_TO_Room
		FOREIGN KEY (
			locName
		)
		REFERENCES location (
			locName
		);

ALTER TABLE comments
	ADD
		CONSTRAINT FK_askBoard_TO_comments
		FOREIGN KEY (
			askNo
		)
		REFERENCES askBoard (
			askNo
		);


##부대시설 판매 조회 

create or replace view v_fac_sales
as
    select m.locname, i.facname, i.adultprice, i.kidsprice, r.ci_date , f.kidsno, f.adultno
    from facility f, reservation r, facilityinfo i, room m
    where f.reservno = r.reservno 
        and f.facinfono = i.facinfono
         and r.roomno = m.roomno;
         
  

##객실 판매조회 

create or replace view v_room_sales
as
    select m.locname, m.roomtype, m.roomprice, r.ci_date 
    from reservation r,
        room m
    where r.roomno = m.roomno;
    