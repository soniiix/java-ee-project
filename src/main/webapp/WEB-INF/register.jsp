<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Créer un compte</title>
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
	
	<form action="user_register" method="POST" class="container mt-5">
       	<h2 class="mb-4">Créer un compte</h2>
		<div class="mb-4">
		    <label class="form-label">Nom</label>
		    <input type="text" class="form-control" name="nom" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Prénom</label>
		    <input type="text" class="form-control" name="prenom" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Login</label>
		    <input type="text" class="form-control" name="login" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Mot de passe</label>
		    <input type="password" class="form-control" name="mdp" required>
		</div>

		<button type="submit" class="btn btn-info">Valider</button>
	</form>
</body>
</html>