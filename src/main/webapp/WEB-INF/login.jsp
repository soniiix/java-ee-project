<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Connexion</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<c:if test="${not empty error}">
	    <div class="container mt-4 alert alert-danger" role="alert">
	        ${error}
	    </div>
	</c:if>
	
	<c:if test="${not empty success}">
	    <div class="container mt-4 alert alert-success" role="alert">
	        ${success}
	    </div>
	</c:if>
	
	<form action="user_login" method="POST" class="container mt-5">
       	<h2 class="mb-4">Connexion</h2>
		<div class="mb-4">
		    <label class="form-label">Login</label>
		    <input type="text" class="form-control" name="login" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Mot de passe</label>
		    <input type="password" class="form-control" name="mdp" required>
		</div>

		<button type="submit" class="btn btn-info">Se connecter</button>
		<br/>
		<br/>
		<a href="user_register" class="text-info">Pas de compte ? En créer un</a>
	</form>
</body>
</html>