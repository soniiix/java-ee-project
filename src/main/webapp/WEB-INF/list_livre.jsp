<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Liste des livres</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body>
    	<jsp:include page="header.jsp" />
    	
	    <c:if test="${not empty error}">
		    <div class="container mt-4 alert alert-danger" role="alert">
		        ${error}
		    </div>
		</c:if>
        <div class="container mt-5">
        	<h2 class="mb-4">Liste des livres</h2>
        	
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Titre</th>
                        <th scope="col">Auteur</th>
                        <th scope="col">Genre</th>
                        <th scope="col">Année</th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="livre" items="${livres}">		          
	                   <tr>
	                       <td>${livre.titre}</td>
	                       <td>${livre.auteur}</td>
	                       <td>${livre.genre}</td>
	                       <td>${livre.anneePublication}</td>
	                       <td>
		                       <a class="text-info" href="${pageContext.request.contextPath}/livre_edit?id=${livre.id}">
		                       		<i class="bi bi-pencil-square"></i>
		                       </a>
	                       </td>
	                       <td>
		                       <a class="text-info" href="${pageContext.request.contextPath}/livre_delete?id=${livre.id}">
		                       		<i class="bi bi-trash3"></i>
		                       </a>
	                       </td>
	                   </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="">
            	<a href="form_livre.jsp" class="btn btn-info mt-4">Ajouter un livre</a>
            </div>
        </div>
    </body>
</html>
