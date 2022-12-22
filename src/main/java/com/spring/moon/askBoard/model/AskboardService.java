package com.spring.moon.askBoard.model;

import java.sql.SQLException;
import java.util.List;

public interface AskboardService {

	/**
	 * 문의글 등록
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	int insert(AskBoardVO vo) throws SQLException ;
	
	/**
	 * 관리자 - 전체 문의글 조회
	 * @param condition
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	List<AskBoardVO> selectAll(String condition, String keyword) throws SQLException;
	
	/**
	 * 문의글 상세보기
	 * @param askno
	 * @return
	 * @throws SQLException
	 */
	AskBoardVO selectByAskNo(int askno) throws SQLException;
	
	/**
	 * 문의글 수정
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	int updateAskboard(AskBoardVO vo) throws SQLException ;
	
	/**
	 * 문의글 삭제
	 * @param askno
	 * @return
	 * @throws SQLException
	 */
	int deleteAskboard(int askno) throws SQLException ;
	
	/**
	 * 회원별 문의글 조회
	 * @param guestno
	 * @return
	 * @throws SQLException
	 */
	List<AskBoardVO> selectByGuestno(int guestno) throws SQLException;
}
