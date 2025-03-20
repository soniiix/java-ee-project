package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = new UserDAO(daoFactory);

            User user = userDAO.getUserByLogin(login);
            
            if (user == null) {
                request.setAttribute("error", "Utilisateur introuvable.");
            } else if (!user.checkPassword(mdp)) {
                request.setAttribute("error", "Mot de passe incorrect.");
            } else {
            	System.out.println("ok");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/livres");
                return;
            }
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
    }

}
