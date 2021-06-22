package com.ashok.BloodDonationREST.Dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import com.ashok.BloodDonationREST.*;
import com.ashok.BloodDonationREST.bean.DonationBean;
import com.ashok.BloodDonationREST.bean.EventBean;
import com.ashok.BloodDonationREST.bean.UserBean;

public class DB {
	
	
	private String username = "root";
	private String password = "rootroot";
	private String dbName = "blood_donation";
	private String url = "jdbc:mysql://localhost:3306/" + dbName+"?useSSL=false";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection con;
	
	public DB() {
		
	}
	
	
	
	
	// function to connect to the MySql DB
	private void dbConnect() {
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//function to close the DB
	private void dbClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//function to add the user to DB
	public void addUser(UserBean user) throws Exception {
		dbConnect();
		String sql = "Insert into users(name,bloodtype,address,gender,mobileno,email,password) values(?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		String hashedpassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		
		user.setPassword(hashedpassword);
		
		//System.out.println("Password during signup " + user.getPassword());
		
		//System.out.println("IN DB - Email " + user.getEmail() + "Mobileno "+user.getMobileno());
		
		st.setString(1, user.getName());
		st.setString(2, user.getBloodtype());
		st.setString(3, user.getAddress());
		st.setString(4, user.getGender());
		st.setString(5, user.getMobileno());
		st.setString(6, user.getEmail());
		st.setString(7, user.getPassword());
		
		st.executeUpdate();
		dbClose();
	}
	
	
	//function to update the user
	public void updateUser(UserBean user) throws SQLException {
		dbConnect();
		String sql = "update users set name=?,bloodtype=?,address=?,gender=?,mobileno=?,password=? where email=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		System.out.println("Inside update users");
		
		System.out.println("Name inside Update User :" +user.getName());
		
		String hashedpassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		
		user.setPassword(hashedpassword);
		
		//System.out.println("Password during signup " + user.getPassword());
		
		st.setString(1, user.getName());
		st.setString(2, user.getBloodtype());
		st.setString(3, user.getAddress());
		st.setString(4, user.getGender());
		st.setString(5, user.getMobileno());
		st.setString(6, user.getPassword());
		st.setString(7, user.getEmail());
		
		st.executeUpdate();
		dbClose();
	}

	
	//function to check whether the user exists
	public boolean checkUser(String email, String password) throws Exception {
		dbConnect();
		int count = 0;
		String sql = "Select * from users where email = ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, email);
		
		ResultSet rs = st.executeQuery();
		String encryptedpassword="";
		while(rs.next()) {
			encryptedpassword=rs.getString("password");
		}
		
		//System.out.println("Encrypted password " + encryptedpassword);
		
		//System.out.println(" password " + password);
		
		Boolean passwordmatched =BCrypt.checkpw(password, encryptedpassword);
		
		//System.out.println("Password matced ?" + passwordmatched);
		
		dbClose();
		
		if(passwordmatched)
		{
			return true;
		}
		
		return false;
	} 
	
	
	//function to check user using the email id
	public boolean checkUserEmail(String email) throws SQLException {
		dbConnect();
		int count = 0;
		String sql = "Select * from users where email = ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, email);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			count = 1;
		}
		
		dbClose();
		if(count == 0)
			return false;
		
		return true;
	}
	
	
	//function to fetch user using email address
	public UserBean fetchUserUsingEmail(String email) throws SQLException {
		dbConnect();
		String sql = "Select * from users where email = ?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, email);
		
		//System.out.println("email - > " + email);
		
		ResultSet rs = st.executeQuery();
		UserBean u=null;
			
		while(rs.next()) {
			String name = rs.getString("name");
			String bloodtype = rs.getString("bloodtype");
			String address = rs.getString("address");
			String gender = rs.getString("gender");
			String email_id = rs.getString("email");
			int userid = rs.getInt("userid");
			String mobileno = rs.getString("mobileno");
			String password = rs.getString("password");
			
			u = new UserBean();
			u.setAddress(address);
			u.setBloodtype(bloodtype);
			u.setEmail(email_id);
			u.setId(userid);
			u.setGender(gender);
			u.setMobileno(mobileno);
			u.setName(name); 
			u.setPassword(password);
		}
		dbClose();
		
		return u;
	}
	
	
	//function to check user using the email id
		public boolean checkIfUserExistUsingId(int id) throws SQLException {
			dbConnect();
			int count = 0;
			String sql = "Select * from users where userid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				count = 1;
			}
			
			dbClose();
			if(count == 0)
				return false;
			
			return true;
		}
	
	
	//function to add Donation [ ADMIN ]
	public void addDonation(DonationBean donation) throws SQLException, ParseException {
		dbConnect();
		String sql = "Insert into donation(userid,date) values(?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 
		 java.util.Date date = sdf.parse(donation.getDate());
		 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		st.setInt(1, donation.getUserid());
		st.setDate(2, sqlDate);
		
		st.executeUpdate();
		dbClose();
	}
	
	//function to add Event [ADMIN]
	public void addEvent(EventBean event) throws SQLException, ParseException {
		dbConnect();
		String sql = "Insert into event(name,date,location) values(?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 java.util.Date date = sdf.parse(event.getDate());
		 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		 System.out.println(event.getName());
		 
		st.setString(1, event.getName());
		st.setDate(2, sqlDate);
		st.setString(3,event.getLocation());
		
		st.executeUpdate();
		dbClose();
	}
	
	
	//function to fetch user's donations by ID
	public ArrayList<DonationBean> fetchUserDonationById(int id) throws SQLException {
		dbConnect();
		String sql = "Select * from donation where userid = ? order by date desc";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, id);
		
		System.out.println("FetchUserDonationById id - > " + id);
		
		ResultSet rs = st.executeQuery();
		DonationBean d=null;
		ArrayList<DonationBean> userdonationbyid = new ArrayList<DonationBean>();
			
		while(rs.next()) {
			int userid = rs.getInt("userid");
			String date = rs.getString("date");
			int donationid = rs.getInt("donationno");
			
			d = new DonationBean();
			d.setDate(date);
			d.setDonationid(donationid);
			d.setUserid(userid);
			
			userdonationbyid.add(d);
			d=null;
		}
		dbClose();
		
		return userdonationbyid;
	}
	
	//function to fetch All the Donations [ADMIN]
	public ArrayList<DonationBean> fetchAllDonations() throws SQLException {
		dbConnect();
		String sql = "Select * from donation order by date desc";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		DonationBean d=null;
		ArrayList<DonationBean> alldonations = new ArrayList<DonationBean>();
			
		while(rs.next()) {			
			int userid = rs.getInt("userid");
			String date = rs.getString("date");
			int donationid = rs.getInt("donationno");
			
			d = new DonationBean();
			d.setDate(date);
			d.setDonationid(donationid);
			d.setUserid(userid);
			
			alldonations.add(d);
			d=null;
		}
		dbClose();
		
		return alldonations;
	}
	
	
	//function to fetch all events [ ADMIN ]
	public ArrayList<EventBean> fetchAllEvents() throws SQLException {
		dbConnect();
		String sql = "Select * from event order by date desc";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		EventBean e=null;
		ArrayList<EventBean> allevents = new ArrayList<EventBean>();
			
		while(rs.next()) {
			int eventid = rs.getInt("eventid");
			String name = rs.getString("name");
			String location = rs.getString("location");
			String date = rs.getString("date");
			
			e = new EventBean();
			e.setEventid(eventid);
			e.setDate(date);
			e.setLocation(location);
			e.setName(name);
			
			allevents.add(e);
			e=null;
		}
		dbClose();
		
		return allevents;
	}

}

