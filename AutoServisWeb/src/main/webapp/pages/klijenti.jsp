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

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/klijenti.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	
	<meta charset="UTF-8">
	<title>Klijenti i vozila</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
	
		<sec:authorize access="hasRole('ADMIN')">
			
			<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Klijenti i vozila</h3>
				</div>
			
				<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Ime i prezime klijenta</th>
								<th>Prebivalište klijenta</th>
								<th>Prikaži vozila klijenta</th>
							</tr>
						</thead>
					    <c:if test="${not empty klijenti}">
							<tbody>
						
								<c:forEach var="k" items="${klijenti}">
							
									<tr>
										<td>${k.ime} ${k.prezime}</td>
										<td>${k.mesto}</td>
										<td>
											<a href="/AutoServis/admin/detaljiKlijenta?idV=${k.idVlasnik}">Prikaži</a>
										</td>
									</tr>
						
								</c:forEach>
							</tbody>
						</c:if>
						
						<c:if test="${empty klijenti}">
							<tbody>
								
								<tr>
									<td>///</td>
									<td>///</td>
									<td>///</td>
								</tr>
							
							</tbody>
						</c:if>
					</table>
				</div>
				
				<c:if test="${not empty vozila}">
					
					<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Marka automobila</th>
								<th>Registarske tablice</th>
							</tr>
						</thead>
					    
						<tbody>
						
							<c:forEach var="v" items="${vozila}">
							
								<tr>
									<td>${v.marka}</td>
									<td>${v.regTablice}</td>
								</tr>
						
							</c:forEach>
						</tbody>
					</table>
					</div>
					
				</c:if>
			
			</div>
			
		</sec:authorize>
	
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>