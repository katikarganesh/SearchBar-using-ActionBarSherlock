package com.example.actionbarsherlockdemo;

public class Student {
	private String fname;
	private String lname;
	private String address;

	public Student(String fname, String lname, String address) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
