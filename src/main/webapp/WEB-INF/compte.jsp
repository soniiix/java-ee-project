<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Compte</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<jsp:include page="header.jsp"/>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/user_login");
        return;
    }
%>
<div class="container mt-5">
    <h2>Informations du compte</h2>
    <div class="card mt-4 mb-4">
		 <ul class="list-group list-group-flush">
			<li class="list-group-item"><strong>Nom</strong> : <%= user.getNom() %></li>
			<li class="list-group-item"><strong>Prénom</strong> : <%= user.getPrenom() %></li>
			<li class="list-group-item"><strong>Login</strong> : <%= user.getLogin() %></li>
		 </ul>
	</div>
	<a href="${pageContext.request.contextPath}/modifier_compte" class="btn btn-info">Modifier</a>
    <a href="${pageContext.request.contextPath}/user_logout" class="btn btn-danger">Déconnexion</a>
</div>
