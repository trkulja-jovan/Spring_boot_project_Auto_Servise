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
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/index.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
	
	<title>Početna stranica</title>
	
</head>
<body>

<sec:authorize access="isAuthenticated()">

  <sec:authorize access="hasRole('ADMIN')">
  	<%@include file="meniAdmin.jsp"%>
  	  <div class="content-wrapper">
  	  	<div class="logg">
  	  		<pre>Dobrodošao ${radnik.ime} ${radnik.prezime}</pre>
    		<h3>Ulogovani ste kao: ADMINISTRATOR</h3>
    	</div>
      	<div class="container-fluid">
      		<div class="row">
        		<div class="col-lg-4 col-md-4 col-sm-6 col-12 mb-2 mt-4">
            		<div class="inforide">
              			<div class="row">
                			<div class="col-lg-3 col-md-4 col-sm-4 col-4 rideone">
                			
               		 		</div>
                			<div class="col-lg-9 col-md-8 col-sm-8 col-8 fontsty">
                    			<h4>Broj zaposlenih</h4>
                    			<h2>${brRadnika}</h2>
                			</div>
              			</div>
            		</div>
        		</div>

        		<div class="col-lg-4 col-md-4 col-sm-6 col-12 mb-2 mt-4">
            		<div class="inforide">
              			<div class="row">
                			<div class="col-lg-3 col-md-4 col-sm-4 col-4 ridetwo">
                    			
                			</div>
                			<div class="col-lg-9 col-md-8 col-sm-8 col-8 fontsty">
                    			<h4>Popravke u toku</h4>
                    			<c:if test="${!empty popProces}">
                    				<c:forEach var="p" items="${popProces}">
                    					<h2>Popravka: ${p.opisPopravke}</h2>
                    					<h3>Datum prijema: ${p.datumPrijema}</h3>
                    				</c:forEach>
                    			</c:if>
                    			<c:if test="${empty popProces}">
                    				<h2>0</h2>
                    			</c:if>
                			</div>
              			</div>
            		</div>
        		</div>

        		<div class="col-lg-4 col-md-4 col-sm-6 col-12 mb-2 mt-4">
            		<div class="inforide">
              			<div class="row">
                			<div class="col-lg-3 col-md-4 col-sm-4 col-4 ridethree">
                    			
                			</div>
                			<div class="col-lg-9 col-md-8 col-sm-8 col-8 fontstyyy">
                				<h4>Popravke na čekanju</h4>
                    			<c:if test="${!empty popCekanje}">
                    				<h2>${brCekanjePop}</h2>
                    			</c:if>
                    			<c:if test="${empty popCekanje}">
                    				<h2>0</h2>
                    			</c:if>
                			</div>
              			</div>
            		</div>
        		</div>
    		</div>
  		</div>
	</div>
  
  </sec:authorize>
  
  <sec:authorize access="hasRole('WORKER')">
  
  	<%@include file="meniRadnik.jsp"%>
  	<div class="content-wrapper">
  	  	<div class="logg">
  	  		<pre>Dobrodošao ${radnik.ime} ${radnik.prezime}</pre>
    		<h2>Ulogovani ste kao: RADNIK</h2>
    	</div>
    </div>
  </sec:authorize>

</sec:authorize>

<sec:authorize access="!isAuthenticated()">

	<%@include file="notLogged.jsp"%>
	
</sec:authorize>

</body>
</html>