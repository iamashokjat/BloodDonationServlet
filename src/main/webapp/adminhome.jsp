<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blood Donation- Admin Home</title>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/1c7d46ef33.js" crossorigin="anonymous"></script>
  <style>
  /* Make the image fully responsive */
  .navbar-nav > li{
  margin-left:20px;
  margin-right:20px;
}

 .navbar-nav > li a{
	color:white;
}

  </style>
</head>
<body>

	
	<%	
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.
	
		if(session.getAttribute("usertype") == null || session.getAttribute("usertype").equals("user"))
		{
			response.sendRedirect("/BloodDonationREST/");
		}
	%>
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: grey">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="<%= request.getContextPath()%>">Blood Donation</a>
		    </div>
		    <ul class="nav navbar-nav navbar-right" style="background-color: grey" >
		       <li><a href="<%= request.getContextPath()%>/add_donation.jsp"><i class="fas fa-plus-circle"></i> New Donation</a></li>
		        <li><a href="<%= request.getContextPath()%>/add_event.jsp"><i class="far fa-calendar-plus"></i> New Event</a></li>
		      <li><a href="<%= request.getContextPath()%>/rest/admin/logout"><i class="fas fa-sign-in-alt"></i> Logout</a></li>
		    </ul> 
		  </div>
		</nav>
	</header>
	
<div class="container mt-3 border border-white">
  <div class="d-flex justify-content-around  mb-3">
    <div class="p-2  border border-secondary table-responsive">
    	<strong>Recent Donations Added</strong>
    	<table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Donation no</th>
		        <th>User Id</th>
		        <th>Date</th>
		      </tr>
		    </thead>
		<c:forEach items="${donations}" var="donation"> 
		    <tbody>
		      <tr>
		        <td>${donation.donationid}</td>
		        <td>${ donation.userid }</td>
		        <td>${donation.date}</td>
		      </tr>
		    </tbody>
		 </c:forEach> 
  		</table>
    </div>
    <div class="p-2  border border-secondary table-responsive">
    	<strong>Recent Events Added</strong>
    	<table class="table table-hover">
		    <thead>
		      <tr>
		        <th>Event Id</th>
		        <th>Event Name</th>
		        <th>Event Location</th>
		        <th>Event Date</th>
		      </tr>
		    </thead>
		    <c:forEach items="${sessionScope.eventslist}" var="event"> 
		    <tbody>
		      <tr>
		        <td>${event.eventid}</td>
		        <td>${event.name}</td>
		        <td>${event.location}</td>
		        <td>${event.date}</td>
		      </tr>
		    </tbody>
		 </c:forEach> 
  		</table>
    </div>
  </div>
</div>


</body>
</html>