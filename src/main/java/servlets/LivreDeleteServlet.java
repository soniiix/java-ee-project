package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOConfigurationException;
import dao.DAOFactory;
import dao.LivreDAO;

/**
 * Servlet implementation class LivreDeleteServlet
 */
@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                
                try {
	                DAOFactory daoFactory = DAOFactory.getInstance();
	                LivreDAO livreDAO = new LivreDAO(daoFactory);
	                
	                livreDAO.deleteLivre(id);
                } catch (DAOConfigurationException e) {
                    e.printStackTrace();
                }

                response.sendRedirect(request.getContextPath() + "/livres");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Erreur, ID de livre invalide.");
                request.getRequestDispatcher("/WEB-INF/list_livre.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/livres");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
