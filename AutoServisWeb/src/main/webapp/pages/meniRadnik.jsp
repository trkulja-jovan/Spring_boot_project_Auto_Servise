<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/index.css" />
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/app-icon.png" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">

    	<button class="navbar-toggler navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

    	<div class="collapse navbar-collapse" id="navbarResponsive">
    		
        	<ul class="navbar-nav navbar-sidenav">
				<li>
          			<a href="${pageContext.request.contextPath}/worker/refreshData" class="nav-link navlogo text-center">
            			<img src="/AutoServis/images/app-icon.png">
          			</a>
				</li>
				
        		<li class="nav-item">
          			<a class="nav-link sidefrst" href="/AutoServis/pages/myProfile.jsp">
            			<span class="textside">Moj profil</span>
          			</a>
        		</li>

        		<li class="nav-item">
          			<a class="nav-link sidesecnd" href="${pageContext.request.contextPath}/worker/getMojePopravke">
            			<span class="textside">Moje popravke</span>
          			</a>
        		</li>
        		
        		<li class="nav-item">
          			<a class="nav-link sidesthrd" href="${pageContext.request.contextPath}/worker/getDataForPopravka">
            			<span class="textside">Popravke</span>
          			</a>
        		</li>
        		
        		<li class="nav-item">
          			<a class="nav-link sidesforth" href="${pageContext.request.contextPath}/getKlijenti">
            			<span class="textside">Klijenti i vozila</span>
          			</a>
        		</li>
      		</ul>
      
    	</div>
   </nav>
</body>
</html>