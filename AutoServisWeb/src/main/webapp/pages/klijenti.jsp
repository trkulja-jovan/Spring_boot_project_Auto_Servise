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
					<div class="naslov2">
						<h4>Prikaz vozila za klijenta: ${k.ime} ${k.prezime}</h4>
					</div>
					
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
	
	<sec:authorize access="hasRole('WORKER')">
		
		<%@include file="meniRadnik.jsp"%>
			
		<div class="content-wrapper">
			
			<div class="naslov">
				<h3>Klijenti i vozila (nemate mogućnost pregleda klijenata)</h3>
			</div>
				
		 	<div class="dugmici">
					
				<button onclick="showFormVozilo()" class="dugme">Unesi vozilo</button>
				<button onclick="showFormKlijent()" class="dugme">Unesi klijenta</button>
				
			</div>
				
		 	<c:if test="${not empty klijenti}">
		 
		 		<div class="forma" id="formV" style="display: none">
					
				<form action="${pageContext.request.contextPath}/worker/addVozilo" 
				  	  method="post" 
				      class="form-register">
						
					<table>
							
						<tr>
							<td><label>Unesite marku automobila</label></td>
							<td><input type="text" name="marka" required></td>
						</tr>
							
						<tr>
							<td><label>Unesite registarske tablice</label></td>
							<td><input type="text" name="regTab" placeholder="KK - 000000 - AB" required></td>
						</tr>
							
						<tr>
							<td><label>Odaberite vlasnika vozila</label></td>
							<td>
								<select name="klijent">
									<c:forEach var="k" items="${klijenti}">
										<option value="${k.idVlasnik}">${k.ime} ${k.prezime}
									</c:forEach>
								</select>
							</td>
						
						</tr>
					
						<tr>
							<td><pre><br><br></pre></td>
							<td><input type="submit" value="Unesite vozilo"></td>
						</tr>
							
						<tr>
							<td><input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" /></td>
						</tr>
						
					</table>
					
				</form>
						
			</div>
		 
		 </c:if>
		 
		 <div class="forma" id="formK" style="display: none">
					
			<form action="${pageContext.request.contextPath}/worker/addKlijent" 
				  method="post" 
				   class="form-register">
						
				<table>
							
					<tr>
						<td><label>Unesite ime</label></td>
						<td><input type="text" name="ime" required></td>
					</tr>
							
					<tr>
						<td><label>Unesite prezime</label></td>
						<td><input type="text" name="prez" required></td>
					</tr>
							
					<tr>
						<td><label>Unesite prebivalište vlasnika</label></td>
						<td><input type="text" name="mesto" required></td>
					</tr>
					
					<tr>
						<td><pre><br><br></pre></td>
						<td><input type="submit" value="Unesite klijenta / vlasnika"></td>
					</tr>
							
					<tr>
						<td><input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" /></td>
					</tr>
						
				</table>
					
			</form>
						
		</div>

		 <div class="ispisi">
					
					<c:if test="${uspesnoKlijent}">
						<pre><br></pre>
						
						<script type="text/javascript">
							var forma = document.getElementById("formK");
							if(forma.style.display === "block"){
								forma.style.display = "none";
							}
						</script>
						
						<h3 class="uspeloh3">Uspešno ste dodali klijenta.</h3>
						<pre><br></pre>
						<c:remove var = "uspesnoKlijent"/>
						<c:remove var = "uspesnoVozilo"/>
					</c:if>
					
					<c:if test="${uspesnoVozilo}">
						<pre><br></pre>
						
						<script type="text/javascript">
							var forma = document.getElementById("formV");
							if(forma.style.display === "block"){
								forma.style.display = "none";
							}
						</script>
						
						<h3 class="uspeloh3">Uspešno ste dodali vozilo.</h3>
						<pre><br></pre>
						<c:remove var = "uspesnoKlijent"/>
						<c:remove var = "uspesnoVozilo"/>
						
					</c:if>

		</div>
	</div>
				
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<%@include file="notLogged.jsp"%>
	</sec:authorize>
</body>
</html>