package com.ashok.BloodDonationREST;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ashok.BloodDonationREST.Dao.DB;
import com.ashok.BloodDonationREST.bean.DonationBean;
import com.ashok.BloodDonationREST.bean.EventBean;
import com.ashok.BloodDonationREST.bean.UserBean;
import com.sun.jersey.api.view.Viewable;

@Path("/user")
public class User {
	
	@POST
    public void userhomePage(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException, ParseException {
    	
//		
//		System.out.println(json);
//		Object obj = new JSONParser().parse(json);
//		
//		JSONObject jsonObject = (JSONObject) obj;
    	
    	
    	HttpSession session= request.getSession(true);
    	DB db = new DB();
    	String loginemail = multivaluedMap.getFirst("email");
		String loginpassword = multivaluedMap.getFirst("password");
    	
//    	String loginemail =(String) jsonObject.get("email");
//    	String loginpassword = (String) jsonObject.get("password");
    	
		Boolean loginuserexists=false;
		
		System.out.println("LoginPassword: "+loginpassword + "LoginEmail: "+loginemail);
		
		try {
			loginuserexists = db.checkUser(loginemail, loginpassword);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(loginuserexists)
		{	
			UserBean user;
			ArrayList<DonationBean> donationbyuser = null;
			ArrayList<EventBean> eventslist = null;
			try {
				user=db.fetchUserUsingEmail(loginemail);
				donationbyuser = db.fetchUserDonationById(user.getId());
				eventslist = db.fetchAllEvents();
				session.setAttribute("user",user);	
				session.setAttribute("donationbyuser", donationbyuser);
				session.setAttribute("eventslist", eventslist);
				session.setAttribute("loginerrormessage", null);
				session.setAttribute("usertype", "user");
				session.setAttribute("username", user.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Hello User Logged in");
			
			response.sendRedirect("/BloodDonationREST/userhome.jsp");
		}
		else
		{	
			session.setAttribute("loginerrormessage", "Invalid Credentials!");
			session.setAttribute("usertype", null);
			//System.out.println(session.getAttribute("loginerrormessage"));
			response.sendRedirect("/BloodDonationREST/login.jsp");
		}
    }
	
	
	 	@POST
	 	@Path("signup")
	    public Viewable signupUserHomePage(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException {
	    	
	    	HttpSession session = request.getSession();
			session.setAttribute("usertype", "user");
			DB db = new DB();
			
				String checkemail = multivaluedMap.getFirst("email");
				Boolean userexists=false;
				
				try {
					userexists = db.checkUserEmail(checkemail);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				if(userexists)
				{
					session.setAttribute("signuperrormessage", "User Already Exists");
					session.setAttribute("usertype", null);
					request.getRequestDispatcher("signup.jsp").forward(request, response);
					
				}
				else
				{	
					String name=multivaluedMap.getFirst("name");
					System.out.println("Name received" + name);
					String bloodtype=multivaluedMap.getFirst("bloodtype");
					String address=multivaluedMap.getFirst("address");
					String gender=multivaluedMap.getFirst("gender");
					String mobileno=multivaluedMap.getFirst("mobileno");
					String email=multivaluedMap.getFirst("email");
					String password=multivaluedMap.getFirst("password");
					
					UserBean user = new UserBean(name,bloodtype,address,gender,mobileno,email,password);
					try {
						db.addUser(user);
						user = db.fetchUserUsingEmail(email);					
						session.setAttribute("user",user);
						session.setAttribute("usertype", "user");
						session.setAttribute("username", user.getName());
						session.setAttribute("signuperrormessage",null);
						return new Viewable("/userhome.jsp");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				return new Viewable("/signup.jsp"); 
	    }
	
	
	@POST
	@Path("/edit_profile")
    public void editUserProfile(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException {
    	
    	DB db = new DB();
    	String name = multivaluedMap.getFirst("name");
    	String bloodtype = multivaluedMap.getFirst("bloodtype");
		String address = multivaluedMap.getFirst("address");
		String gender = multivaluedMap.getFirst("gender");
		String mobileno = multivaluedMap.getFirst("mobileno");
		String email = multivaluedMap.getFirst("email");
		String password = multivaluedMap.getFirst("password");
		
		HttpSession session = request.getSession(false);
		
		UserBean user = new UserBean(name,bloodtype,address,gender,mobileno,email,password);
		try {
			db.updateUser(user);
			user = db.fetchUserUsingEmail(email);					
			session.setAttribute("user",user);
			session.setAttribute("usertype", "user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("/BloodDonationREST/userhome.jsp");
    }
	
	@GET
	@Path("/logout")
	public void logoutUser(@Context HttpServletRequest request,@Context HttpServletResponse response) throws IOException {
	
	HttpSession session = request.getSession(false);
	session.setAttribute("usertype", null);
	session.setAttribute("user",null);	
	session.setAttribute("donationbyuser", null);
	session.setAttribute("eventslist", null);
	session.setAttribute("loginerrormessage", null);
	session.setAttribute("usertype", null);
	session.setAttribute("username", null);
	session.invalidate();
		
	response.sendRedirect("/BloodDonationREST/");
	}
}
