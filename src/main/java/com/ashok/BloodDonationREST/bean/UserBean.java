package com.ashok.BloodDonationREST.bean;

public class UserBean {
	
	private String name,bloodtype,address,gender,mobileno,email,password;
	private int id;

	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserBean(String name, String bloodtype, String address, String gender, String mobileno,String email,String password) {
		super();
		this.name = name;
		this.bloodtype = bloodtype;
		this.address = address;
		this.gender = gender;
		this.mobileno = mobileno;
		this.email=email;
		this.password=password;
		
		System.out.println("Email " + email + "Mobileno "+mobileno);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
