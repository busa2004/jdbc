package com.douzone.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.vo.AuthorVo;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class BookDao {

	
	public boolean insert(BookVo bookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			
			conn = getConnection();
			//3. statement 객체를 생성
			String sql = "insert into book values(null, ?,'대여가능',?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  bookVo.getTitle());
			pstmt.setLong(2,  bookVo.getAuthorNo());
			int count = pstmt.executeUpdate();
			result = count == 1;
		}catch (SQLException e) {
			System.out.println("error:" +e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	
	
	public static boolean update(long no,String status) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//2. 연결하기(Connection 객체 얻어보기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			System.out.println("연결 성공");
			//3. SQL문 준비
			String sql = "update book set status= ? where no=?";
			pstmt=conn.prepareStatement(sql);
			//4. binding
			pstmt.setString(1, status);
			pstmt.setLong(2, no);
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			result = count >=1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		} catch (SQLException e) {
			System.out.println("error:" +e);
		} finally {
			try {

				if(pstmt!=null)
					pstmt.close();
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	public List<BookVo> getList(){
		List<BookVo> list = new ArrayList<BookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			//3. statement 객체를 생성
			stmt = conn.createStatement();
			//4. SQL문 실행
			String sql = "select a.no, a.title, a.status,b.name" + 
					" from book a, author b" + 
					" where a.no2 = b.no;";
			rs = stmt.executeQuery(sql);
			//5. 결과
			while(rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
				
				list.add(vo);
			}
			
		}catch (SQLException e) {
			System.out.println("error:" +e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(conn != null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	}
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		//2. 연결하기(Connection 객체 얻어보기)
		String url = "jdbc:mysql://localhost:3306/webdb";
		conn = DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) { 
			System.out.println("드라이버 로딩 실패");
		}
		
		return conn;
	}
}
