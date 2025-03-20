package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = new UserDAO(daoFactory);

            User user = new User(nom, prenom, login, mdp);

            if (userDAO.registerUser(user)) {
            	request.setAttribute("success", "Compte créé ! Veuillez vous connecter ci-dessous.");
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Erreur lors de l'inscription.");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
	}

}
