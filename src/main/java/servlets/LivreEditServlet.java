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
import model.Livre;

/**
 * Servlet implementation class LivreEditServlet
 */
@WebServlet("/livre_edit")
public class LivreEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LivreDAO livreDAO = new LivreDAO(daoFactory);
            
            Livre livre = livreDAO.getLivreById(id);
            
            if (livre != null) {
                request.setAttribute("livre", livre);
                this.getServletContext().getRequestDispatcher("/form_livre_edit.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Livre non trouvé.");
                this.getServletContext().getRequestDispatcher("/livres").forward(request, response);
            }
        } catch (DAOConfigurationException e) {
            e.printStackTrace();
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
