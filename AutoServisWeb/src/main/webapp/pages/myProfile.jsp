<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/index.css" />
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/profile.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	
	<title>Moj profil</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">

  <sec:authorize access="hasRole('ADMIN')">
  
  	<%@include file="meniAdmin.jsp"%>
  	
  	<div class="content-wrapper">
  	  	
  	  	<div class="generalije">
  	  		<div class="okvir">
  	  			<h3>Ime</h3>
  	  			<h4>${radnik.ime}</h4>
  	  		
  	  			<h3>Prezime</h3>
  	  			<h4>${radnik.prezime}</h4>
  	  		
  	  			<h3>Kvalifikacije</h3>
  	  			<h4>${radnik.kvalifikacije}</h4>
  	  		</div>
  	  	</div>
  	  	<pre><br><br></pre>
  	  	<div class="licni-podaci">
  	  		<div class="okvir">
  	  			<h3>Korisničko ime</h3>
  	  			<h4>${radnik.korIme}</h4>
  	  		
  	  			<h3>Lozinka</h3>
  	  			<h4>${radnikPass}</h4>
  	  			
  	  			<h6>Vodite računa o vašim ličnim podacima</h6>
  	  		</div>
  	  	</div>
      	
	</div>
  	
  </sec:authorize>
  
  <sec:authorize access="hasRole('WORKER')">
  
  	<%@include file="meniRadnik.jsp"%>
  	
  	<div class="content-wrapper">
  	  	
  	  	<div class="generalije">
  	  		<div class="okvir">
  	  			<h3>Ime</h3>
  	  			<h4>${radnik.ime}</h4>
  	  		
  	  			<h3>Prezime</h3>
  	  			<h4>${radnik.prezime}</h4>
  	  		
  	  			<h3>Kvalifikacije</h3>
  	  			<h4>${radnik.kvalifikacije}</h4>
  	  		</div>
  	  	</div>
  	  	<pre><br><br></pre>
  	  	<div class="licni-podaci">
  	  		<div class="okvir">
  	  			<h3>Korisničko ime</h3>
  	  			<h4>${radnik.korIme}</h4>
  	  		
  	  			<h3>Lozinka</h3>
  	  			<h4>${radnikPass}</h4>
  	  			
  	  			<h6>Vodite računa o vašim ličnim podacima</h6>
  	  		</div>
  	  	</div>
      	
	</div>
  	
  </sec:authorize>
  
</sec:authorize>

<sec:authorize access="!isAuthenticated()">

	<%@include file="notLogged.jsp"%>
	
</sec:authorize>
</body>
</html>