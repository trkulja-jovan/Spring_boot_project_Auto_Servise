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
				
				<div class="dugmici">
					
					<button onclick="showFormPopravka()" class="dugme">Unesi popravku</button>
					<button onclick="showFormEdit()" class="dugme">Izmeni popravku</button>
				
				</div>
				
				<div class="forma" id="formPopravka" style="display: none">
					
					<form action="${pageContext.request.contextPath}/worker/addPopravka" 
					      method="post" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td><label>Unesite opis popravke</label></td>
								<td><textarea name="opis" rows="10" cols="30" required="required"></textarea></td>
							</tr>
							
							<tr>
								<td><label>Datum prijema vozila</label></td>
								<td><input type="date" name="datum" required></td>
							</tr>
							
							<tr>
								<c:if test="${not empty vozila}">
									<td><label>Izaberite vozilo za popravku</label></td>
									<td>
										<select name="vozilo">
											<c:forEach var="v" items="${vozila}">
												<option value="${v.idVozilo}">${v.marka} | ${v.vlasnik.ime} ${v.vlasnik.prezime}
											</c:forEach>
										</select>
									</td>
								</c:if>
								
								<c:if test="${empty vozila}">
									<td>
										<label>
											Nemate uneta vozila u bazi.
											<a href="/AutoServis/getKlijenti">Unesi klijenta i / ili vozilo</a>
										</label>
									</td>
								</c:if>
							</tr>
							
							<tr>
								<td><pre><br><br></pre></td>
								<td><input type="submit" value="Unesi popravku"></td>
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

				<div class="forma" id="formEdit" style="display: none">
					
					<form action="${pageContext.request.contextPath}/worker/updatePopravka" 
					      method="post" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td><label>Izaberite popravku</label></td>
								<td>
									<c:if test="${not empty popravke}">
									
										<select name="popravka">
											<c:forEach var="p" items="${popravke}">
												<option value="${p.idPopravka}">${p.opisPopravke} | ${p.datumPrijema}
											</c:forEach>
										</select>

									</c:if>
								</td>
							</tr>
							
							<tr>
								<td><label>Datum završetka popravke</label></td>
								<td>
									<input type="date" name="datum" required>
								</td>
								
							</tr>
							
							<tr>
								<td><label>Izaberite usluge koje su odrađene</label></td>
								<td>
									<c:if test="${not empty usluge}">
									
										<c:forEach var="u" items="${usluge}">
											<input type="checkbox" name="usluge" value="${u.idUsluga}">${u.nazivUsluge} | ${u.cena} <br>
										</c:forEach>
	
									</c:if>
								</td>
							</tr>

							<tr>
								<td><pre><br><br></pre></td>
								<td><input type="submit" value="Ažuriraj popravku"></td>
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
				
				<div class="ispisi">
					
					<c:if test="${uspesnoPopravka}">
						<pre><br></pre>
						
						<script type="text/javascript">
							var forma = document.getElementById("formPopravka");
							if(forma.style.display === "block"){
								forma.style.display = "none";
							}
						</script>
						
						<h3 class="uspeloh3">Uspešno ste dodali popravku. Čeka se odobrenje šefa.</h3>
						<pre><br></pre>
						<c:remove var = "uspesnoPopravka"/>
					</c:if>
				
				</div>
			</div>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>