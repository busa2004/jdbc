package com.douzone.jdbc.hr;

import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;
  
public class HRMain {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.print("검색 : ");
		String str = s.next();
		getList(str);
		
		s.close();
		
	}
	public static void getList(String name) {
		List<EmployeeVo> list = new EmployeeDao().getList(name);

		if(list.size() == 0) {
			System.out.println("찾는 이름이 없습니다.");
		}else {
		for(EmployeeVo vo : list){
			System.out.println(vo);
		}
		}
		 
	}
}
