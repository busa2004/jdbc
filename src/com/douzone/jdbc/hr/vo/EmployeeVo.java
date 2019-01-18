package com.douzone.jdbc.hr.vo;

public class EmployeeVo {
	private long no;
	private String first_name;
	private String last_name;
	private String email;
	private String number;
	private String hire_date;
	public long getNo() {
		return no;
	}
	public String getHire_date() {
		return hire_date;
	}
	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String date;
	@Override
	public String toString() {
		return "이름 :" +first_name + " " + last_name +" 이메일:"+email+" 전화번호:"+number+" 입사일:"+hire_date;
	}
	
	
}
