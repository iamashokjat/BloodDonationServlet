<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blood Donation-Login</title>
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
	  $("#userloginform").validate({
		  errorClass: "error fail-alert",
		  validClass: "valid success-alert", 
	    rules: {
	      email: {
	        required: true,
	        email: true
	      },
	      password : {
              minlength : 8
          },

	    },
	    messages : {
	      email: {
	        email: "The email should be in the format: abc@domain.tld"
	      },
	    },
	    //submitHandler: function(form) {
	    	//var data = {};
	    	//$(form).serializeArray().map(function(x){data[x.name] = x.value;});
	       //$.ajax({
	        //   type: "POST",  
	        //    contentType: "application/json",
	        //    data:JSON.stringify(data),   
	         //   url:"",
	         //   success: function(data) {
	         //   	console.log(data);
	        //    }
	       // });
	    //},
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
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: grey">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="<%= request.getContextPath()%>">Blood Donation</a>
		    </div>
		    <ul class="nav navbar-nav navbar-right" style="background-color: grey" >
		      <li><a href="<%= request.getContextPath()%>/signup.jsp"><i class="fas fa-user-plus"></i> Sign Up</a></li>
		    </ul> 
		  </div>
		</nav>
	</header>
	
	<div class="container login-container">
            <div class="row">
                <div class="col-md-6 login-form-1">
                    <h3>Admin Login</h3>
                    <c:if test = "${adminloginerror != null}">
	                    <div class="alert alert-danger">
							  <strong><c:out value="${adminloginerror}"></c:out></strong>
						</div>
					</c:if>
                    <form id="adminloginform" method="post" action="<%= request.getContextPath() %>/rest/admin">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Email *" name="email" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Your Password *" name="password" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Login" />
                        </div>
                    </form>
                </div>
                <div class="col-md-6 login-form-2">
                    <h3>User Login</h3>
                    <c:if test = "${loginerrormessage != null}">
	                    <div class="alert alert-danger">
							  <strong><c:out value="${loginerrormessage}"></c:out></strong>
						</div>
					</c:if>
                    <form  id="userloginform" method="post" action="<%= request.getContextPath() %>/rest/user">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Your Email *" name="email" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Your Password *" name="password" value="" required/>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btnSubmit" value="Login" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
</body>
</html>