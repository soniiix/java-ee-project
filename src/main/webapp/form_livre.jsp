<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajouter un livre</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="WEB-INF/header.jsp" />
	
	<c:if test="${not empty error}">
	    <div class="container mt-4 alert alert-danger" role="alert">
	        ${error}
	    </div>
	</c:if>
	
	<form action="livre_register" method="POST" class="container mt-5">
       	<h2 class="mb-4">Ajouter un livre</h2>
		<div class="mb-4">
		    <label class="form-label">Titre</label>
		    <input type="text" class="form-control" name="titre" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Auteur</label>
		    <input type="text" class="form-control" name="auteur" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Année de publication</label>
		    <input type="number" class="form-control" name="anneePublication" required>
		</div>
		<div class="mb-4">
		    <label class="form-label">Genre</label>
		    <input type="text" class="form-control" name="genre" required>
		</div>

		<button type="submit" class="btn btn-info">Enregistrer</button>
	</form>
</body>
</html>