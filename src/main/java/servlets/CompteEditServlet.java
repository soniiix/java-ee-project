package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.UserDAO;
import model.User;

/**
 * Servlet implementation class CompteEditServlet
 */
@WebServlet("/modifier_compte")
public class CompteEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user_login");
            return;
        }

        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/modifier_compte.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user_login");
            return;
        }

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");

        if (nom == null || prenom == null || login == null || nom.isEmpty() || prenom.isEmpty() || login.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("/WEB-INF/modifier_compte.jsp").forward(request, response);
            return;
        }

        user.setNom(nom);
        user.setPrenom(prenom);
        user.setLogin(login);

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.updateUser(user);

        if (success) {
            session.setAttribute("user", user); // Met à jour la session
            response.sendRedirect(request.getContextPath() + "/compte");
        } else {
            request.setAttribute("error", "Une erreur est survenue lors de la mise à jour.");
            request.getRequestDispatcher("/WEB-INF/modifier_compte.jsp").forward(request, response);
        }
    }

}
