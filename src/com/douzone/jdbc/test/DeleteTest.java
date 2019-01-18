package com.douzone.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest {
	public static void main(String[] args) {
		boolean result = delete("마음이2");
		System.out.println(result);
	}
	public static boolean delete(String name) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.jdbc.Driver");
			//2. 연결하기(Connection 객체 얻어보기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			System.out.println("연결 성공");
			//3. statement 객체를 생성
		 stmt = conn.createStatement();
			//4. SQL문 실행
			String sql = "delete from pet where name = '"+name +"'";
			int count = stmt.executeUpdate(sql);
			
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		} catch (SQLException e) {
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
		return result;
	}
}
