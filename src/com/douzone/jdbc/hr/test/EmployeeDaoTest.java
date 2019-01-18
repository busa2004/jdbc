package com.douzone.jdbc.hr.test;

import java.util.List;

import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;
import com.douzone.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDaoTest {
	public static void main(String[] args) {

		
		getListTest("a");
	}
	public static void getListTest(String name) {
		List<EmployeeVo> list = new EmployeeDao().getList(name);
		for(EmployeeVo vo : list){
			System.out.println(vo);
		}
	}
	

}
