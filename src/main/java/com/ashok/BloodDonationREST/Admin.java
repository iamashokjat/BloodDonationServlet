package com.ashok.BloodDonationREST;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.ashok.BloodDonationREST.Dao.DB;
import com.ashok.BloodDonationREST.bean.DonationBean;
import com.ashok.BloodDonationREST.bean.EventBean;
import com.sun.jersey.api.view.Viewable;



@Path("/admin")
public class Admin {
	
	@POST
    public void adminhomePage(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		
		String email=multivaluedMap.getFirst("email");
		String password=multivaluedMap.getFirst("password");
		
		DB db = new DB();
		
		if((email.equals("admin@gmail.com") && password.equals("admin006")))
		{	
			ArrayList<EventBean> eventslist = null;
			ArrayList<DonationBean> donations = null;
			try {
				eventslist = db.fetchAllEvents();
				donations = db.fetchAllDonations();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			session.setAttribute("eventslist", eventslist);
			session.setAttribute("donations", donations);
			session.setAttribute("usertype", "admin");
			session.setAttribute("adminloginerror", null);
			
			response.sendRedirect("/BloodDonationREST/adminhome.jsp");
		}
		
		else
		{
			session.setAttribute("adminloginerror", "Invalid Credentials!");
			response.sendRedirect("/BloodDonationREST/login.jsp");
		}
		
    		
    }
    
    
    @POST
    @Path("/refresh")
	public Viewable adminhomePageRefresh(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException {
	    	System.out.println("Inside the Refresh of admin");
	    	HttpSession session = request.getSession();
			ArrayList<EventBean> eventslist = null;
			ArrayList<DonationBean> donations = null;
			
			DB db = new DB();
			
			try {
				eventslist = db.fetchAllEvents();
				donations = db.fetchAllDonations();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			session.setAttribute("eventslist", eventslist);
			session.setAttribute("donations", donations);
			session.setAttribute("usertype", "admin");
			
			return new Viewable("/adminhome.jsp");
	    		
	    }
    
    @POST
    @Path("/add_donation")
    public void addDonation(@Context HttpServletRequest request,@Context HttpServletResponse response,MultivaluedMap<String, String> multivaluedMap) throws ServletException, IOException, ParseException {
    	
    	
    	System.out.println("Inside the add donation!!");
    	
    	int userid=Integer.parseInt(multivaluedMap.getFirst("userid"));
		String date=multivaluedMap.getFirst("date");
		
		System.out.println("userid" + userid +" " + "date" + date);
		HttpSession session = request.getSession(false);
		
		DB db = new DB();
		
		Boolean userIdExists=false;
		
		try {
			userIdExists = db.checkIfUserExistUsingId(userid);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(userIdExists)
		{	
		
		DonationBean donation = new DonationBean(userid,date);

			try {
				db.addDonation(donation);
				ArrayList<EventBean> eventslist = null;
				ArrayList<DonationBean> donations = null;
				try {
					eventslist = db.fetchAllEvents();
					donations = db.fetchAllDonations();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				session.setAttribute("eventslist", eventslist);
				session.setAttribute("donations", donations);
				session.setAttribute("usertype", "admin");
				session.setAttribute("donationerror",null);
			} catch (SQLException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//request.getRequestDispatcher("adminhome.jsp").forward(request, response);
			
			//doGet(request, response);
			response.sendRedirect("/BloodDonationREST/adminhome.jsp");
			
		}
		else
		{
			session.setAttribute("donationerror", "User with Id " + userid + " doesn't exists");
			response.sendRedirect("/BloodDonationREST/add_donation.jsp");
		}
        		
        }
    
    @POST
    @Path("/add_event")
    public void addEvent(@Context HttpServletRequest request,@Context HttpServletResponse response,String json) throws ServletException, IOException, ParseException, Throwable {
    
    	System.out.println("Inside the Add event method.");
    	
		System.out.println(json);
		
		JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);
//    	
//    String eventname = multivaluedMap.getFirst("eventname");
//	String eventdate=multivaluedMap.getFirst("eventdate");
//	String eventlocation=multivaluedMap.getFirst("eventlocation");
	
 	
    String eventname = (String)jsonObject.get("eventname");
	String eventdate=(String)jsonObject.get("eventdate");
	String eventlocation=(String)jsonObject.get("eventlocation");
	
	HttpSession session = request.getSession(false);
	
	DB db = new DB();
	
	EventBean event = new EventBean(eventname,eventdate,eventlocation);

		try {
			db.addEvent(event);
			ArrayList<EventBean> eventslist = null;
			ArrayList<DonationBean> donations = null;
			try {
				eventslist = db.fetchAllEvents();
				donations = db.fetchAllDonations();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			session.setAttribute("eventslist", eventslist);
			session.setAttribute("donations", donations);
			session.setAttribute("usertype", "admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//request.getRequestDispatcher("adminhome.jsp").forward(request, response);
		
		//response.sendRedirect("/BloodDonationREST/adminhome.jsp");
    }
    
    
    @GET
	@Path("/logout")
	public void logoutUser(@Context HttpServletRequest request,@Context HttpServletResponse response) throws IOException {
	
	HttpSession session = request.getSession(false);
	session.setAttribute("eventslist", null);
	session.setAttribute("donations", null);
	session.setAttribute("usertype", null);
	session.invalidate();
		
	response.sendRedirect("/BloodDonationREST/");
	}
    		
    }

