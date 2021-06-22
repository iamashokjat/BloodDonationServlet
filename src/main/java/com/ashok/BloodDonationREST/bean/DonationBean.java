package com.ashok.BloodDonationREST.bean;

public class DonationBean {
	private String date;
	private int userid,donationid;
	
	public DonationBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DonationBean(int userid,String date) {
		super();
		this.userid = userid;
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getDonationid() {
		return donationid;
	}
	public void setDonationid(int donationid) {
		this.donationid = donationid;
	}
}
