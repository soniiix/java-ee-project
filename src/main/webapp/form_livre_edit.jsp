<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Modifier un livre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <jsp:include page="/WEB-INF/header.jsp" />
    
    <div class="container mt-5">
        <h2 class="mb-4">Modifier un livre</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/livre_update" method="post">
            <input type="hidden" name="id" value="${livre.id}" />
            
            <div class="mb-3">
                <label for="titre" class="form-label">Titre</label>
                <input type="text" class="form-control" id="titre" name="titre" value="${livre.titre}" required />
            </div>

            <div class="mb-3">
                <label for="auteur" class="form-label">Auteur</label>
                <input type="text" class="form-control" id="auteur" name="auteur" value="${livre.auteur}" required />
            </div>

            <div class="mb-3">
                <label for="anneePublication" class="form-label">Année de Publication</label>
                <input type="number" class="form-control" id="anneePublication" name="anneePublication" value="${livre.anneePublication}" required />
            </div>

            <div class="mb-3">
                <label for="genre" class="form-label">Genre</label>
                <input type="text" class="form-control" id="genre" name="genre" value="${livre.genre}" required />
            </div>

            <button type="submit" class="btn btn-info">Sauvegarder</button>
        </form>
    </div>
</body>
</html>
