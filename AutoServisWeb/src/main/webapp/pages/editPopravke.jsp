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
					<button onclick="showFormPopravka()" class="dugme">Izmeni popravku</button>
					<button onclick="showFormPridr()" class="dugme">Pridruži se popravci</button>
				
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
					
					<form action="${pageContext.request.contextPath}/worker/addPopravka" 
					      method="post" 
					      class="form-register">
						
						<table>
							
							<tr>
								<td><label>Unesite opis kvara</label></td>
								<td><textarea name="kvar" rows="10" cols="30" required="required"></textarea></td>
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
											<c:forEach var="v" items="${vozilo}">
												<option value="${v.idVozilo}">${v.marka}
											</c:forEach>
										</select>
									</td>
								</c:if>
								
								<c:if test="${empty vozila}">
									<td>
										<label>
											Nemate uneta vozila u bazi.
											<a href="/AutoServis/pages/klijenti.jsp">Unesi klijenta i / ili vozilo</a>
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
				
				<div class="forma" id="formPridr" style="display: none">
					
					<form action="${pageContext.request.contextPath}/worker/changePopravkaPridru" 
					      method="post" 
					      class="form-register">
						
						<table>
						
							<tr>
								<c:if test="${not empty popravke}">
									<td><label>Izaberite popravku kojoj želite da se pridružite</label></td>
									<td>
										<select name="popravka">
											<c:forEach var="p" items="${popravke}">
												<option value="${p.idPopravka}">${p.opisPopravke} ${p.datumPrijema} ${p.datumZavrsetka} ${p.status.opis}
											</c:forEach>
										</select>
									</td>
								</c:if>
							</tr>
							
							<tr>
								<td><pre><br><br></pre></td>
								<td><input type="submit" value="Pošaljite zahtev za pridruživanje"></td>
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
					
					<c:if test="${uspesnoPridruzivanje}">
						<pre><br></pre>
						
						<script type="text/javascript">
							var forma = document.getElementById("formPridr");
							if(forma.style.display === "block"){
								forma.style.display = "none";
							}
						</script>
						
						<h3 class="uspeloh3">Uspešno ste podneli zahtev za pridruživanje. Čeka se odobrenje šefa.</h3>
						<pre><br></pre>
						<c:remove var = "uspesnoPridruzivanje"/>
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