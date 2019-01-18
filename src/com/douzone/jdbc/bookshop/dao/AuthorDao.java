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

public class AuthorDao {
	public boolean insert(AuthorVo authorVo) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			boolean result = false;
			try {
				
				conn = getConnection();
				//3. statement 객체를 생성
				String sql = "insert into author values(null,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,  authorVo.getName());
				pstmt.setString(2,  authorVo.getBio());
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
	
	
	public  List<AuthorVo> getList(){
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			//3. statement 객체를 생성
			stmt = conn.createStatement();
			//4. SQL문 실행
			String sql = "select no,name,bio from author";
			rs = stmt.executeQuery(sql);
			//5. 결과
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String bio = rs.getString(3);
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setBio(bio);
				
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
