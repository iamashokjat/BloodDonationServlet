<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blood Donation-Edit Profile</title>
 <meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/jquery.validate.min.js"></script>
  <script src="https://kit.fontawesome.com/1c7d46ef33.js" crossorigin="anonymous"></script>
  
  <script type="text/javascript">
  
  jQuery.validator.setDefaults({
	    errorElement: 'span',
	    errorPlacement: function (error, element) {
	        error.addClass('invalid-feedback');
	        element.closest('.form-group').append(error);
	    },
	    highlight: function (element, errorClass, validClass) {
	        $(element).addClass('is-invalid');
	    },
	    unhighlight: function (element, errorClass, validClass) {
	        $(element).removeClass('is-invalid');
	    }
	});
  
  $(document).ready(function() {
	  $("#editprofileform").validate({
		  errorClass: "error fail-alert",
		  validClass: "valid success-alert", 
	    rules: {
	      name : {
	        required: true,
	        minlength: 3
	      },
	      email: {
	        required: true,
	        email: true
	      },
	      mobileno: {
	    	  required:true,
	    	  minlength:10,
	          maxlength:10
	      },
	      password : {
              minlength : 8
          },
          confirmpassword : {
              minlength : 8,
              equalTo : "#password"
          }
	    },
	    messages : {
	      name: {
	        minlength: "Name should be at least 3 characters"
	      },
	      email: {
	        email: "The email should be in the format: abc@domain.tld"
	      },
	      mobileno: {
	    	  minlength: "Please enter 10 digits"
	      },
	      confirmpassword : {
	    	  equalTo : "Password not matching"
	      },
	    }
	  });
	});
  
  </script>
  
  
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
		
			if(session.getAttribute("usertype") == null || session.getAttribute("usertype").equals("admin"))
			{
				response.sendRedirect("/BloodDonationDonationREST/");
			}
		%>
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: grey">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="<%= request.getContextPath()%>/userhome.jsp">Blood Donation</a>
		    </div> 
		  </div>
		</nav>
	</header>
	
	<%= session.getAttribute("username") %>
	
	<div class="container login-container signup-page">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Edit Profile</h3>
                    <form id="editprofileform" method="post" action="<%= request.getContextPath()%>/rest/user/edit_profile">
                    	<div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Id *" value="${sessionScope.user.getId()}" name="userid" readonly />
                        </div>
                    	<div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Name *" value="${sessionScope.user.getName()}" name="name" required/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="Your Email *" value="${sessionScope.user.getEmail()}" name="email" readonly/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Blood Group *" value="${sessionScope.user.getBloodtype()}" name="bloodtype" required/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Address *" value="${sessionScope.user.getAddress()}"  name="address" required/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Phone Number(10 Digits) *" value="${sessionScope.user.getMobileno()}" name="mobileno" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" id="password" class="form-control" placeholder="Enter New Password *" value="" name="password" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Re-Enter New Password *" value="" name="confirmpassword" required/>
                        </div>
                        <div class="form-group">
                        	  Gender<br/>
							 <select name="gender"> 
						    <option value="Male" selected>Male</option> 
						    <option value="Female">Female</option> 
							</select> 
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Update" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
</body>
</html>