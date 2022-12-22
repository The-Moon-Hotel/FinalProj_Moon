package com.spring.moon.askBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.moon.db.ConnectionPoolMgr;

@Repository
public class AskBoardDAO {
	private ConnectionPoolMgr pool;
	
	public AskBoardDAO() {
		pool = new ConnectionPoolMgr();
	}
	
	
	/**
	 * 문의글 insert
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public int insert(AskBoardVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
		
			String sql ="insert into askboard(askno, guestno, a_title, a_content)"
					+ " values(askboard_seq.nextval, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, vo.getGuestNo());
			ps.setString(2, vo.getA_title());
			ps.setString(3, vo.getA_content());
			
			int cnt = ps.executeUpdate();

			System.out.println("문의글 등록 결과: "+cnt+", 매개변수: "+vo);
			return cnt;
			
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	
	/**
	 * 문의글 관리자 전체 selectAll
	 * @return
	 * @throws SQLException
	 */
	public List<AskBoardVO> selectAll(String condition, String keyword) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<AskBoardVO> list = new ArrayList<>();
		
		try {
			con = pool.getConnection();
		
			String sql = "select * from askboard";
			
			//검색의 경우
			if(keyword!=null && !keyword.isEmpty()) {
				sql+=" where "+ condition +" like '%'|| ? ||'%'";
			}
			sql+=" order by askno desc";
			ps = con.prepareStatement(sql);
			
			if(keyword!=null && !keyword.isEmpty()) {
				ps.setString(1, keyword);
			}

			rs = ps.executeQuery();
				
			while(rs.next()) {
				int askno = rs.getInt("askno");
				int guestno = rs.getInt("guestno");
				String a_title = rs.getString("a_title");
				String a_content = rs.getString("a_content");
				Timestamp a_regdate = rs.getTimestamp("a_regdate");
				
				AskBoardVO vo 
					= new AskBoardVO(askno, guestno, a_title, a_content, a_regdate);
				list.add(vo);
			}
			
			System.out.println("문의글 전체 조회 결과 list.size: " + list.size());
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	
	/**
	 * askNo를 이용하여 문의글 상세보기
	 * @param guestno
	 * @return
	 * @throws SQLException
	 */
	public AskBoardVO selectByAskNo(int askno) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AskBoardVO vo = new AskBoardVO();
		
		try {
			con = pool.getConnection();
			
			String sql = "select * from askboard where askno = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, askno);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int guestno = rs.getInt("guestno");
				String a_title = rs.getString("a_title");
				String a_content = rs.getString("a_content");
				Timestamp a_regdate = rs.getTimestamp("a_regdate");
				
				vo.setAskNo(askno);
				vo.setGuestNo(guestno);
				vo.setA_title(a_title);
				vo.setA_content(a_content);
				vo.setA_regdate(a_regdate);
			}
			System.out.println("문의글 상세보기: "+vo+", askno: "+askno);
			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	
	/**
	 * 문의글 update
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public int updateAskboard(AskBoardVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "update askboard"
					+ " set a_title = ?, a_content = ?"
					+ " where askno = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getA_title());
			ps.setString(2, vo.getA_content());
			ps.setInt(3, vo.getAskNo());
			
			int cnt = ps.executeUpdate();
			
			System.out.println("문의글 수정 결과: "+cnt+", vo: "+vo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	
	/**
	 * 문의글 delete
	 * @param askno
	 * @param pwd
	 * @return
	 * @throws SQLException
	 */
	public int deleteAskboard(int askno) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "delete from askboard where askno = ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, askno);
			
			int cnt = ps.executeUpdate();
			
			System.out.println("문의글 삭제 결과: "+cnt);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	
	/**
	 * 회원별 문의글 조회(회원번호)
	 * @param guestno
	 * @return
	 * @throws SQLException
	 */
	public List<AskBoardVO> selectByGuestno(int guestno) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<AskBoardVO> list = new ArrayList<>();
		
		try {
			con = pool.getConnection();
			
			String sql = "select * from askboard where guestno = ? order by askno desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, guestno);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				int askno = rs.getInt("askno");
				String a_title = rs.getString("a_title");
				String a_content = rs.getString("a_content");
				Timestamp a_regdate = rs.getTimestamp("a_regdate");
				
				AskBoardVO vo 
					= new AskBoardVO(askno, guestno, a_title, a_content, a_regdate);
				list.add(vo);
			}
			
			System.out.println("회원번호: "+guestno +" 글 조회 결과: "+list.size() );
			return list;
			
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
}
