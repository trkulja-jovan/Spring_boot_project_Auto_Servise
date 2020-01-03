<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<link href="https://fonts.googleapis.com/css?family=Ropa+Sans" rel="stylesheet">
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/close-icon.png" />
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/accessDenied.css" />
	
	<meta charset="UTF-8">
	
	<title>Form error</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
	
		<c:if test="${greskaUsluga}">
			<div class="error-main">
				<h1>Oops!</h1>
				<div class="error-heading">500</div>
				<p>Greška prilikom dodavanja usluge. <br> Proverite podatke!</p>
				<p><a href="/AutoServis/admin/refreshData">Povratak na početnu stranicu</a></p>
				<c:remove var = "greskaUsluga"/>
			</div>
		</c:if>
		
		<c:if test="${greskaRadnik}">
			<div class="error-main">
				<h1>Oops!</h1>
				<div class="error-heading">500</div>
				<p>Greška prilikom dodavanja radnika. <br> Proverite podatke!</p>
				<p><a href="/AutoServis/admin/refreshData">Povratak na početnu stranicu</a></p>
				<c:remove var = "greskaRadnik"/>
			</div>
		</c:if>
		
		<c:if test="${greskaKlijent}">
			<div class="error-main">
				<h1>Oops!</h1>
				<div class="error-heading">500</div>
				<p>Greška prilikom dodavanja klijenta. <br> Proverite podatke!</p>
				<p><a href="/AutoServis/worker/refreshData">Povratak na početnu stranicu</a></p>
				<c:remove var = "greskaKlijent"/>
			</div>
		</c:if>
		
		<c:if test="${greskaVozilo}">
			<div class="error-main">
				<h1>Oops!</h1>
				<div class="error-heading">500</div>
				<p>Greška prilikom dodavanja vozila. <br> Proverite podatke!</p>
				<p><a href="/AutoServis/worker/refreshData">Povratak na početnu stranicu</a></p>
				<c:remove var = "greskaVozilo"/>
			</div>
		</c:if>
		
		<c:if test="${greskaPopravka}">
			<div class="error-main">
				<h1>Oops!</h1>
				<div class="error-heading">500</div>
				<p>Greška prilikom dodavanja popravke. <br> Proverite podatke!</p>
				<p><a href="/AutoServis/worker/refreshData">Povratak na početnu stranicu</a></p>
				<c:remove var = "greskaPopravka"/>
			</div>
		</c:if>
	
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>