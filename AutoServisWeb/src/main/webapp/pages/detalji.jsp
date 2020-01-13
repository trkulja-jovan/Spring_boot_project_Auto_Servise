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

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/detalji.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	<meta charset="UTF-8">
	<title>Detalji</title>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
	
		<sec:authorize access="hasRole('ADMIN')">
			
			<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Detalji popravke</h3>
				</div>
				
				<div class="naslov2">
					<h4>Prikaz detalja za popravku: ${popravka.opisPopravke} -> Status: ${popravka.status.opis}</h4>
				</div>
				
				<a href="${pageContext.request.contextPath}/admin/getPopravke">Povratak</a>
				
				<div class="generalije">
  	  				<div class="okvir">
  	  				
  	  					<h3>Datum početka i završetka:</h3>
  	  					<h4>Datum početka -> ${popravka.datumPrijema}</h4>
  	  					<h4>Datum završetka -> ${popravka.datumZavrsetka}</h4>
  	  		
  	  				</div>
  	  			</div>
				
				<div class="generalije">
  	  				<div class="okvir">
  	  				
  	  					<h3>Spisak vozila na popravci:</h3>
  	  					<c:forEach var="v" items="${vozila}">
  	  						<h4>${v.marka} ${v.regTablice} -> Vlasnik: ${v.vlasnik.ime} ${v.vlasnik.prezime}</h4>
  	  					</c:forEach>
  	  		
  	  				</div>
  	  			</div>
  	  			
  	  			<div class="generalije">
  	  			
  	  				<div class="okvir">
  	  					<h3>Spisak radnika na popravci:</h3>
  	  					<c:forEach var="r" items="${radnici}">
  	  						<h4>${r.ime} ${r.prezime} -> Kvalifikacije: ${r.kvalifikacije}</h4>
  	  					</c:forEach>
  	  		
  	  				</div>
  	  			</div>
  	  			
  	  			<div class="generalije">
  	  			
  	  				<div class="okvir">
  	  					<h3>Spisak usluga na popravci:</h3>
  	  					<c:forEach var="u" items="${usluge}">
  	  						<h4>${u.nazivUsluge} -> Cena: ${u.cena}</h4>
  	  					</c:forEach>
  	  				</div>
  	  			</div>
  	  			
  	  			<c:remove var="popravka"/>
  	  			<c:remove var="vozila"/>
  	  			<c:remove var="radnici"/>
  	  			<c:remove var="usluge"/>
  	  			
			</div>
		</sec:authorize>
		
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>