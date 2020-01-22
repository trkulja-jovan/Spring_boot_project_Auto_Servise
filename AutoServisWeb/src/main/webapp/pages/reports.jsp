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

	<link rel="stylesheet" type="text/css" href="/AutoServis/css/izvestaji.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	<meta charset="UTF-8">
	<title>Izveštaji</title>
</head>
<body>
	
	<sec:authorize access="isAuthenticated() and hasRole('ADMIN')">
		
		<%@include file="meniAdmin.jsp"%>
			
			<div class="content-wrapper">
			
				<div class="naslov">
					<h3>Izveštaji</h3>
				</div>
				
				<div class="forma" id="form">
					
					<form action="${pageContext.request.contextPath}/admin/spisakPopravkiIzvestaj" 
					      method="get" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td>
									<label>Izaberite radnika i datume</label>
								</td>
								
								<td>
									<select name="radnik">
									
										<c:forEach var="r" items="${radnici}">
											<option value="${r.idRadnik}">${r.ime} ${r.prezime}
										</c:forEach>
									</select>
								</td>
								
								<td>
									<input type="date" name="datumOd" required>	
								</td>
								
								<td>
									<input type="date" name="datumDo" required>	
								</td>
								
								<td>
									<input type="submit" value="Odštampaj izveštaj">
								</td>
								
								<td>
									<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								</td>
								
							</tr>
							
							<tr>
								<c:if test="${nemaPopravki}">
									<td>
										Izabrani radnik ne radi ni na jednoj popravci
									</td>
									<c:remove var="nemaPopravki"/>
								</c:if>
							</tr>

						</table>
					
					</form>
					
					<form action="${pageContext.request.contextPath}/admin/svePopravkeIzvestaj" 
					      method="get" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td>
									<label>Izaberite datum za izveštaj</label>
								</td>
								
								<td>
									<input type="date" name="datumOd" required>	
								</td>
								
								<td>
									<input type="date" name="datumDo" required>	
								</td>
								
								<td>
									<input type="submit" value="Odštampaj izveštaj">
								</td>
								
								<td>
									<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								</td>
								
							</tr>
							
							<tr>
								<c:if test="${nemaPopravki}">
									<td>
										Za izabrani period ne postoje popravke
									</td>
									<c:remove var="nemaPopravki"/>
								</c:if>
							</tr>

						</table>
					
					</form>
					
					<form action="${pageContext.request.contextPath}/admin/sveUslugeIzvestaj" 
					      method="get" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td>
									<label>Izveštaj svih usluga</label>
								</td>
								
								<td>
									<input type="submit" value="Odštampaj izveštaj">
								</td>
								
								<td>
									<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								</td>
								
							</tr>
							
						</table>
					
					</form>
					
					<form action="${pageContext.request.contextPath}/admin/sviKlijentiIzvestaj" 
					      method="get" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td>
									<label>Spisak svih klijenata</label>
								</td>
								
								<td>
									<input type="submit" value="Odštampaj izveštaj">
								</td>
								
								<td>
									<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								</td>
								
							</tr>
							
						</table>
					
					</form>
						
				</div>
			
			</div>
			
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>