<header class="py-3 bg-dark shadow-lg">
    <div class="container d-flex justify-content-between align-items-center">
        <h1 class="text-light fs-4 m-0">Bibliothèque</h1>
        
        <ul class="nav nav-pills">
            <li class="nav-item">
                <a class="nav-link text-light fw-semibold link-info" href="${pageContext.request.contextPath}/livres">
                    Liste des livres
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-light fw-semibold link-info" href="form_livre.jsp">
                    Ajouter un livre
                </a>
            </li>
        </ul>
        
        <div class="d-flex align-items-center gap-3">
        	<%
                if (session.getAttribute("user") != null) {
            %>
                <a href="${pageContext.request.contextPath}/compte" class="nav-link text-light fw-semibold link-info">
                    Compte
                </a>
                <a href="${pageContext.request.contextPath}/user_logout" class="nav-link text-danger fw-semibold">
                    Déconnexion
                </a>
            <%
                } else {
            %>
                <a href="${pageContext.request.contextPath}/user_login" class="nav-link text-light fw-semibold link-info">
                    Connexion
                </a>
            <%
                }
            %>
        	
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-person-circle me-2" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
            </svg>
            
        </div>
    </div>
</header>