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
	
	<script src="/AutoServis/js/scriptFajl.js"></script>
	
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/zaposleni.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	
	<meta charset="UTF-8">
	<title>Evidencija zaposlenih</title>
</head>
<body>

	<sec:authorize access="isAuthenticated()">
	
		<sec:authorize access="hasRole('ADMIN')">
			
			<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
				<div class="naslov">
					<h3>Prikaz trenutno zaposlenih radnika u firmi</h3>
				</div>
			
				<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Ime zaposlenog</th>
								<th>Prezime zaposlenog</th>
								<th>Kvalifikacije zaposlenog</th>
							</tr>
						</thead>
					
						<tbody>
						
							<c:forEach var="z" items="${zaposleni}">
							
								<tr>
									<td>${z.ime}</td>
									<td>${z.prezime}</td>
									<td>${z.kvalifikacije}</td>
								</tr>
						
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div class="dugmici">
					
					<button onclick="showForm()" class="dugme">Zaposli novog radnika</button>
				
				</div>
				
				<div class="forma" id="form" style="display: none">
					
					<form action="${pageContext.request.contextPath}/admin/registerWorker" 
					      method="post" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td><label>Unesite ime radnika</label></td>
								<td><input type="text" name="ime" required></td>
							</tr>
							
							<tr>
								<td><label>Unesite prezime radnika</label></td>
								<td><input type="text" name="prezime" required></td>
							</tr>
							
							<tr>
								<td><label>Napišite kvalifikacije radnika</label></td>
								<td><input type="text" name="kvalif" required></td>
							</tr>
							
							<tr>
								<td><label>Dodelite korisničko ime</label></td>
								<td><input type="text" name="korIme" placeholder="example@worker" required></td>
							</tr>
							
							<tr>
								<td><label>Dodelite lozinku</label></td>
								<td><input type="password" name="password" required></td>
							</tr>
							
							<tr>
								<td><pre><br><br></pre></td>
								<td><input type="submit" value="Registruj novog radnika"></td>
							</tr>
							
							<tr>
								<td>
									<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								</td>
							</tr>
						
						</table>
					
					</form>
						
				</div>
				
				<div class="zaposleni">
					
					<c:if test="${uspesno}">
						<pre><br><br><br></pre>
						<h3 class="uspeloh3">Uspešno ste registrovali novog radnika.</h3>
						<h4 class="uspeloh4">Srećan rad.</h4>
					</c:if>

				</div>
			</div>
			
		</sec:authorize>
	
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
	
	<sec:authorize access="hasRole('WORKER')">
		<%@include file="access_denied.jsp"%>
	</sec:authorize>
</body>
</html>