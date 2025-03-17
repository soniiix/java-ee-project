<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Liste des livres</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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

            <form method="get" action="${pageContext.request.contextPath}/livres" class="row g-3 mb-4">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="q" placeholder="Rechercher un livre ou un auteur" value="${param.q}">
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="genre">
                        <option value="">Tous les genres</option>
                        <c:forEach var="g" items="${genres}">
                            <option value="${g}" ${param.genre == g ? 'selected' : ''}>${g}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="annee">
                        <option value="">Toutes les années</option>
                        <c:forEach var="a" items="${annees}">
                            <option value="${a}" ${param.annee == a ? 'selected' : ''}>${a}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-info w-100">
                        Filtrer
                    </button>
                </div>
            </form>

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

            <div>
                <a href="form_livre.jsp" class="btn btn-info mt-4">Ajouter un livre</a>
            </div>
        </div>
    </body>
</html>
