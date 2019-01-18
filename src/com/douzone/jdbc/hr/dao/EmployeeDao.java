package com.douzone.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.vo.BookVo;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao {

	
	
	public List<EmployeeVo> getList(String name){
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			//3. statement 객체를 생성
			stmt = conn.createStatement();
			
			//3. statement 객체를 생성
			String sql = "select first_name,last_name, email, number, hire_date from employee where first_name like '%"+name +"%'or last_name like '%"+name+"%'";
			rs = stmt.executeQuery(sql);
			
			
			//5. 결과
			while(rs.next()) {
				String first_name = rs.getString(1);
				String last_name = rs.getString(2);
				String email = rs.getString(3);
				String number = rs.getString(4);
				String hire_date = rs.getString(5);
				EmployeeVo vo = new EmployeeVo();
				vo.setFirst_name(first_name);
				vo.setLast_name(last_name);
				vo.setEmail(email);
				vo.setNumber(number);
				vo.setHire_date(hire_date);
				
				
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
