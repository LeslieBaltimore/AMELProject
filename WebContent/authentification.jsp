<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" 
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
	crossorigin="anonymous">
	<link rel="stylesheet" href="./css/style.css">
	
	<title>QCM ENI - Authentification</title>
</head>
<body>
	<div class="login-block">
		<div>
			<div class="authentification">
				<form action="${pageContext.request.contextPath}/login" method="POST">
					<div class="form-group">
					<label for="identifiant" class="form-text-label">Identifiant :</label>
					<input type="email" class="form-text-input form-control" name="identifiant">
					</div>
					<div class="form-group">
					<label for="password" class="form-text-label">Mot de passe :</label>
					<input type="password" class="form-text-input form-control" name="password">
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="form-submit-input btn btn-outline-dark" name="action" value="login">Valider</button>
					</div>
				</form>
			</div>
			<div class="error-message">
				<jsp:include page="/error"/>
			</div>
		</div>
	</div>

</body>
</html>