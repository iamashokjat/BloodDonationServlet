<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blood Donation - Home Page</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/1c7d46ef33.js" crossorigin="anonymous"></script>
  <style>
  /* Make the image fully responsive */
  .carousel-inner img {
    width: 100%;
    height: 100%;
  } 
  .navbar-nav > li{
  margin-left:20px;
  margin-right:20px;
}

 .navbar-nav > li a{
	color:white;
}
  
  </style>
<body>
	<%	
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.
	
		session.setAttribute("usertype",null);
	%>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: grey">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="<%= request.getContextPath()%>">Blood Donation</a>
		    </div>
		    <ul class="nav navbar-nav navbar-right" style="background-color: grey" >
		      <li><a href="<%= request.getContextPath()%>/login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a></li>
		      <li><a href="<%= request.getContextPath()%>/signup.jsp"><i class="fas fa-user-plus"></i> Sign Up</a></li>
		    </ul> 
		  </div>
		</nav>
	</header>
<div class="jumbotron"> 
<div id="demo" class="carousel slide" data-ride="carousel">

  <!-- Indicators -->
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
    <li data-target="#demo" data-slide-to="3"></li>
    <li data-target="#demo" data-slide-to="4"></li> 
    <li data-target="#demo" data-slide-to="5"></li>
    <li data-target="#demo" data-slide-to="6"></li>
    <li data-target="#demo" data-slide-to="7"></li>
    <li data-target="#demo" data-slide-to="8"></li>
  </ul>
  
  <!-- The slideshow -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="images/img2.png" alt="img1" >
    </div>
    <div class="carousel-item">
      <img src="images/img1.png" alt="img2">
    </div>
    <div class="carousel-item">
      <img src="images/img3.png" alt="img3">
    </div>
    <div class="carousel-item">
      <img src="images/img4.png" alt="img4">
    </div> 
    <div class="carousel-item">
      <img src="images/img5.png" alt="img5">
    </div>
    <div class="carousel-item">
      <img src="images/img6.png" alt="img6">
    </div> 
    <div class="carousel-item">
      <img src="images/img7.png" alt="img7">
    </div> 
    <div class="carousel-item">
      <img src="images/img8.jpeg" alt="img8">
    </div>
  </div>
  
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>
</div>

	<c:forEach items="${sessionScope}" var="attr">
    ${attr.key}=${attr.value}<br>
	</c:forEach>
	
</body>
</html>
