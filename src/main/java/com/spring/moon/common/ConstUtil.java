package com.spring.moon.common;

public interface ConstUtil {
	//게시판 페이징 관련 상수
	public static final int RECORD_COUNT=5;
	int BLOCKSIZE=10;

	//파일 업로드 관련 상수
	String FILE_UPLOAD_TYPE="test";   //테스트시
	//String FILE_UPLOAD_TYPE="deploy"; //배포시
	
	//자료실 - 파일 저장 경로
	String FILE_UPLOAD_PATH="fileUpload";
	//String FILE_UPLOAD_PATH_TEST="C:\\lecture\\workspace_list\\spboot_ws\\springherb\\src\\main\\resources\\static\\pds_upload";
	String FILE_UPLOAD_PATH_TEST="C:/lecture/workspace_list/ezenGit_ws/FinalProj_Moon/src/main/resources/static/fileUpload";
	
	//관리자 페이지 - 상품 등록시 상품 이미지 저장 경로
	String IMAGE_FILE_UPLOAD_PATH="pd_images";
	//String IMAGE_FILE_UPLOAD_PATH_TEST="C:\\lecture\\workspace_list\\spboot_ws\\springherb\\src\\main\\resources\\static\\pd_images";
	String IMAGE_FILE_UPLOAD_PATH_TEST="C:\\lecture\\pd_images";
	
	//자료실 업로드인지, 상품 등록시 업로드인지 구분값
	int UPLOAD_FILE_FLAG=1;  //자료실 업로드
	int UPLOAD_IMAGE_FLAG=2; //상품 등록시 이미지 업로드
	
	//장바구니 배송비 관련 상수
	//구매금액이 3만원 이상이면 배송비 3000원
	int DELIVERY=3000;  //배송비
	int BUYING_PRICE=30000; //기준 구매금액
	
}
