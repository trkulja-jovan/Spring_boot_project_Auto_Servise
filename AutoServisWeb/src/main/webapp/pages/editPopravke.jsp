<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/editPop.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	<meta charset="UTF-8">
	<title>Unos i izmena popravki</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
	
		<sec:authorize access="hasRole('WORKER')">
			
			<%@include file="meniRadnik.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Unos i izmena popravki</h3>
				</div>
				
				<div class="card">
					<h4>Odaberite opciju</h4>
	
					<ul>
						<li>
							<input type="radio" name="name" id="one" checked />
							<label for="one">Unesi popravku</label>
			
							<div class="check"></div>
						</li>
		
						<li>
							<input type="radio" name="name" id="two" />
							<label for="two">Izmeni popravku</label>
			
							<div class="check"></div>
						</li>
					</ul>
				</div>
				
				
			</div>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>