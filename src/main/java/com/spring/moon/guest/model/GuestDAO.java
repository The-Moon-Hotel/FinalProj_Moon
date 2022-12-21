package com.spring.moon.guest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.moon.reservation.model.ReservationVO;

@Repository
public class GuestDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//회원가입
	public int insertGuest(GuestVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="insert into guest(guestNo,name,userid,pwd,email,tel)"
					+ " values(guest_seq.nextval, ?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getTel());
			
			int cnt=ps.executeUpdate();
			
			if(cnt>0) {
				System.out.println("회원등록 성공");
			}else {
				System.out.println("회원등록 실패");
			}
			return cnt;
		} finally {
			pool.dbClose(ps, con);
		} 
	}
	
	//중복확인
	public int duplicateUserid(String userid) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1,2
			con=pool.getConnection();
			//3
			String sql="select count(*) from guest where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			//4
			int result=0;
			rs=ps.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count>0) {
					result=GuestSerivce.EXIST_ID; //이미 존재
				}else {
					result=GuestSerivce.NOT_EXIST_ID;	//사용 가능
				}
			}
			System.out.println("아이디 중복확인 결과 result="+result
					+", 매개변수 userid="+userid);
			return result;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//로그인 
	public int loginCheck(String userid, String pwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select pwd from guest "
					+ " where userid = ? and outdate is null";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			
			//4
			int result=0;
			rs=ps.executeQuery();
			if(rs.next()) {
				String dbPwd=rs.getString(1);
				
				if(pwd.equals(dbPwd)) {
					result=GuestSerivce.LOGIN_OK;
				}else {
					result=GuestSerivce.DISAGREE_PWD;
				}
			}else {
				result=GuestSerivce.NONE_USERID;
			}
			return result;
		} finally {
			//5
			pool.dbClose(rs, ps, con);
			// TODO: handle finally clause
		}
	}
	
	//회원정보 페이지에 회원정보 불러올때
	public GuestVO selectByUserid(String userid) throws SQLException {
		String sql = "select * from guest where userid = ?";
		GuestVO vo=new GuestVO();
		vo= jdbcTemplate.query(sql, new rowMapper(GuestVO.class));
		return vo;
	}
	
	//매개변수 회원번호로 회원정보 불러오는 매서드
	public GuestVO selectByGuestNo(int guestNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		GuestVO vo = new GuestVO();
		try {
			con = pool.getConnection();

			String sql = "select * from guest where guestNo = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, guestNo);

			rs = ps.executeQuery();
			if(rs.next()) {
				String userid = rs.getString("userid");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				Timestamp joindate = rs.getTimestamp("joindate");
				Timestamp outdate = rs.getTimestamp("outdate");
				int sys = rs.getInt("sys");

				vo.setGuestNo(guestNo);
	            vo.setName(name);
	            vo.setUserid(userid);
	            vo.setPwd(pwd);
	            vo.setEmail(email);
	            vo.setTel(tel);
	            vo.setJoindate(joindate);
	            vo.setOutdate(outdate);
	            vo.setSys(sys);
			}
			System.out.println("회원조회 결과 vo="+vo+", 매개변수 userid="+guestNo);
			
			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	// 회원정보 수정
	public int updateGuset(GuestVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			//3
			String sql="update guest"
					+ " set email=?, pwd=?, tel=?"
					+ " where userid=? ";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getEmail());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getTel());
			ps.setString(4, vo.getUserid());
			
			//4
			int cnt=ps.executeUpdate();
			if(cnt>0) {
				System.out.println("회원정보 수정 성공");
			}else {
				System.out.println("회원정보 수정 실패");	
			}
			return cnt;
		} finally {
			//5
			pool.dbClose(ps, con);
		}
	}
	
	//회원탈퇴
	public int OutGuset(String userid , String pwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=pool.getConnection();
			
			String sql="update guest"
					+ " set outdate=sysdate"
					+ " where userid=? and pwd=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, pwd);
			
			
			int cnt=ps.executeUpdate();
			if(cnt>0){
				System.out.println("회원탈퇴 성공");
			}else {
				System.out.println("회원탈퇴 실패");
			}
			return cnt;
		} finally {
			// TODO: handle finally clause
			pool.dbClose(ps, con);
		}
	}
	
	//전체 회원 조회
	public List<GuestVO> selectAllGuest(String condition, String keyword) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<GuestVO> glist = new ArrayList<>();
		
		try {
			con = pool.getConnection();
			
			String sql = "select * from guest ";
			
			if(keyword != null && !keyword.isEmpty()) {
				sql+=" where "+condition+" like  '%' || ? || '%' ";
			}
							
			sql += "order by guestNo desc";
			
			ps=con.prepareStatement(sql);
			
			if(keyword!=null && !keyword.isEmpty()) {
				ps.setString(1, keyword);
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int guestNo = rs.getInt("guestNo");
				String userid = rs.getString("userid");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				Timestamp joindate = rs.getTimestamp("joindate");
				Timestamp outdate = rs.getTimestamp("outdate");
				int sys = rs.getInt("sys");
	            
	            GuestVO guestVo = new GuestVO(guestNo, name, userid, 
	            		pwd, email, tel, joindate, outdate, sys);
	            
	            glist.add(guestVo);
			}
			return glist;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}
}
	
