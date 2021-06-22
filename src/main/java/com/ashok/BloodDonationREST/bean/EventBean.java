package com.ashok.BloodDonationREST.bean;

public class EventBean {
	private String name,date,location;
	private int eventid;

	public EventBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventBean(String name, String date, String location) {
		super();
		this.name = name;
		this.date = date;
		this.location = location;
	}
	
	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
