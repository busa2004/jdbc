package com.douzone.jdbc.bookshop;

import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;



public class MainApp {
	public static void main(String[] args) {
		// (2) Book 객체의 정보를 출력
				
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		long num = scanner.nextLong();
		scanner.close();
		
		rent(num);
		
		displayBookInfo();
	}
	private static void rent(long no) {
		new BookDao().update(no,"대여중");;
	}
	
	
	private static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list){
			System.out.println(vo);
		}
	}
}
