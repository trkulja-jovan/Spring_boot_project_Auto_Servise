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

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/popravke.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	<meta charset="UTF-8">
	<title>Popravke</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
		
		<sec:authorize access="hasRole('ADMIN')">
			
			<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Prikaz svih popravki</h3>
				</div>
			
				<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Opis popravke</th>
								<th>Datum prijema</th>
								<th>Datum završetka</th>
								<th>Status popravke</th>
								<th>Pogledaj detalje</th>
							</tr>
						</thead>
					    <c:if test="${not empty svePopravke}">
							<tbody>
						
								<c:forEach var="p" items="${svePopravke}">
							
									<tr>
										<td>${p.opisPopravke}</td>
										<td>${p.datumPrijema}</td>
										<td>${p.datumZavrsetka}</td>
										<td>${p.status.opis}</td>
										<td>
											<a href="/AutoServis/admin/detaljiPopravke?id=${p.idPopravka}">Detalji</a>
										</td>
									</tr>
						
								</c:forEach>
							</tbody>
						</c:if>
						
						<c:if test="${empty svePopravke}">
							<tbody>
								
								<tr>
									<td>U bazi</td>
									<td>nisu evidentirane</td>
									<td>ni jedne</td>
									<td>popravke</td>
									<td>:(</td>
								</tr>
							
							</tbody>
						</c:if>
					</table>
				</div>
			
			</div>
	
		</sec:authorize>
	
		<sec:authorize access="hasRole('WORKER')">
	
			<%@include file="meniRadnik.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Prikaz popravki za radnika: ${radnik.ime} ${radnik.prezime}</h3>
				</div>
			
				<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Opis popravke</th>
								<th>Datum prijema</th>
								<th>Datum završetka</th>
								<th>Status popravke</th>
								<th>Pogledaj detalje</th>
							</tr>
						</thead>
					    <c:if test="${not empty mojePopravke}">
							<tbody>
						
								<c:forEach var="p" items="${mojePopravke}">
							
									<tr>
										<td>${p.opisPopravke}</td>
										<td>${p.datumPrijema}</td>
										<td>${p.datumZavrsetka}</td>
										<td>${p.status.opis}</td>
										<td>
											<a href="/AutoServis/worker/detaljiPopravke?id=${p.idPopravka}">Detalji</a>
										</td>
									</tr>
						
								</c:forEach>
							</tbody>
						</c:if>
						
						<c:if test="${empty mojePopravke}">
							<tbody>
								
								<tr>
									<td>U bazi</td>
									<td>nisu evidentirane</td>
									<td>ni jedne</td>
									<td>popravke</td>
									<td>:(</td>
								</tr>
							
							</tbody>
						</c:if>
					</table>
				</div>
				
			</div>
	
		</sec:authorize>
	
	
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>