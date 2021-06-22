<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blood Donation- Add Event</title>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/1c7d46ef33.js" crossorigin="anonymous"></script>
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
		      <a class="navbar-brand" href="<%= request.getContextPath()%>/adminhome.jsp">Blood Donation</a>
		    </div>
		  </div>
		</nav>
	</header>
	
	<div class="container login-container signup-page">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Add New Event</h3>
                    <form method="post" action="addevent">
                    	<div class="form-group">
                            <input type="text" class="form-control" id="eventname" placeholder="Event name" value="" name="eventname" required />
                        </div>
                        <div class="form-group">
                           <input type="date" class="form-control" id="eventdate" name="eventdate" min="2020-01-01"  value="" required>
                        </div> 
                        <div class="form-group">
                           <input type="text" class="form-control" id="eventlocation" name="eventlocation" placeholder="Event location" value="" required>
                        </div> 
                        <div class="form-group">
                            <input type="button" class="btnSubmit" onclick="addevent()" value="Post Event" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
	<script>
	function addevent()
	{
		var eventname = document.getElementById("eventname").value;
		var eventdate = document.getElementById("eventdate").value;
		var eventlocation = document.getElementById("eventlocation").value;
		
		var data = JSON.stringify({"eventname":eventname,"eventdate":eventdate,"eventlocation":eventlocation});
		
		var xhr = new XMLHttpRequest();
		var url="http://localhost:8080/BloodDonationREST/rest/admin/add_event";
		console.log(data);
		
		xhr.open('POST',url,false);
		xhr.setRequestHeader("Content-Type","appliation/json");
		xhr.send(data);
		window.location.replace("http://localhost:8080/BloodDonationREST/adminhome.jsp");
	}
	</script>
</body>
</html>