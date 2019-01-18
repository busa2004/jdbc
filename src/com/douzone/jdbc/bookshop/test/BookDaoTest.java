package com.douzone.jdbc.bookshop.test;

import java.util.List;

import com.douzone.jdbc.bookshop.dao.AuthorDao;
import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		insertTest("이클립스",1);
		insertTest("브레이킹 던",1);
		
		getListTest();
	}
	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list){
			System.out.println(vo);
		}
	}
	
	public static void insertTest(String title, long authorNo) {
		BookVo vo =new BookVo();
		vo.setTitle(title);
		vo.setAuthorNo(authorNo);
		
		new BookDao().insert(vo);
	}
}
