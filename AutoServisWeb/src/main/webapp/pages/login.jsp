<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link rel="shortcut icon" type="image/png" href="/AutoServis/images/login.png" />
	<link rel="stylesheet" type="text/css" href="/AutoServis/css/loginStyle.css" />
	
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

	<meta charset="UTF-8">
	
	<title>Login page</title>

</head>
<body>
	<div id="fullscreen_bg" class="fullscreen_bg">

		<div class="container">

			<form action="${pageContext.request.contextPath}/login" method="post" class="form-signin">
	
				<h1 class="form-signin-heading text-muted">Auto servis <b style="color: yellow">"RIS"</b></h1>
			    
				<input type="text" name="username" 
				       class="form-control" 
				       placeholder="Korisničko ime" required>
				       
				<input type="password" name="password"
				       class="form-control" 
				       placeholder="Lozinka" required>
				       
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> 
				       
				<input type="submit" value="Prijavi se" class="btn btn-lg btn-primary btn-block" >
			
			</form>

		</div>
	</div>
</body>
</html>