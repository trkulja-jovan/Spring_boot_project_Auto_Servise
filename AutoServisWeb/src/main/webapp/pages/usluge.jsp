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

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/usluge.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	<meta charset="UTF-8">
	<title>Usluge</title>
	
</head>
<body>
	
	<sec:authorize access="isAuthenticated()">
		
		<sec:authorize access="hasRole('ADMIN')">
			
			<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Unos usluge</h3>
				</div>
				
				<div class="dugmici">
					
					<button onclick="showForm()" class="dugme">Dodaj uslugu</button>
				
				</div>
				
				<div class="forma" id="form" style="display: none">
					
					<form action="${pageContext.request.contextPath}/admin/addUsluga" 
					      method="post" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td><label>Unesite naziv usluge</label></td>
								<td><input type="text" name="nazUsluge" required></td>
							</tr>
							
							<tr>
								<td><label>Unesite cenu usluge</label></td>
								<td><input type="text" name="cena" required></td>
							</tr>
							
							<tr>
								<td><pre><br><br></pre></td>
								<td><input type="submit" value="Dodaj"></td>
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
				
				<div class="usluge">
					
					<c:if test="${uspesnoUsluga}">
						<pre><br></pre>
						
						<script type="text/javascript">
							var forma = document.getElementById("form");
							if(forma.style.display === "block"){
								forma.style.display = "none";
							}
						</script>
						
						<h3 class="uspeloh3">Uspešno ste dodali uslugu.</h3>
						<pre><br></pre>
					</c:if>

				</div>
				
				<div class="tabela">
			
					<table class="redTable">
						<thead>
							<tr>
								<th>Naziv usluge</th>
								<th>Cena usluge</th>
							</tr>
						</thead>
					    <c:if test="${not empty usluge}">
							<tbody>
						
								<c:forEach var="u" items="${usluge}">
							
									<tr>
										<td>${u.nazivUsluge}</td>
										<td>${u.cena}</td>
									</tr>
						
								</c:forEach>
							</tbody>
						</c:if>
						
						<c:if test="${empty usluge}">
							<tbody>
								
								<tr>
									<td>///</td>
									<td>///</td>
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