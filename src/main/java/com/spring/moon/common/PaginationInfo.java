package com.spring.moon.common;

public class PaginationInfo {
	private int currentPage; //현재 페이지
	private int totalRecord; //총 레코드 수
	private int recordCountPerPage;  //pageSize 페이지당 보여질 레코드수
	private int totalPage;  //총 페이지수
	private int blockSize; //블럭당 보여질 페이지 수
	private int firstPage;  //블럭당 시작 페이지, 1, 11, 21, 31, ...
	private int lastPage; //블럭당 마지막 페이지 10, 20, 30, 40, ...
	private int firstRecordIndex; //curPos, 페이지당 시작 인덱스 0, 5, 10, 15 ...
	private int num;  //페이지당 시작 글 번호
	
	public PaginationInfo(int currentPage, int totalRecord, int recordCountPerPage, int blockSize) {
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.recordCountPerPage = recordCountPerPage;
		this.blockSize = blockSize;
		
		totalPage = (int)Math.ceil((float)totalRecord/recordCountPerPage);;
		firstPage = currentPage-((currentPage-1)%blockSize);
		lastPage = firstPage+(blockSize-1);
		firstRecordIndex = (currentPage-1)*recordCountPerPage;
		num = totalRecord-firstRecordIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	@Override
	public String toString() {
		return "PaginationInfo [currentPage=" + currentPage + ", totalRecord=" + totalRecord + ", recordCountPerPage="
				+ recordCountPerPage + ", totalPage=" + totalPage + ", blockSize=" + blockSize + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", firstRecordIndex=" + firstRecordIndex + ", num=" + num
				+ "]";
	}	
}
