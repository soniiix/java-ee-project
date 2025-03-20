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
 * Servlet implementation class LivreRegister
 */
@WebServlet("/livre_register")
public class LivreRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String titre = request.getParameter("titre");
		String auteur = request.getParameter("auteur");
		String anneePublication = request.getParameter("anneePublication");
		String genre = request.getParameter("genre");
		
		if (titre.isEmpty() || auteur.isEmpty() || anneePublication.isEmpty() || genre.isEmpty()) {
			request.setAttribute("error", "Erreur, tous les champs doivent être remplis.");
			request.getRequestDispatcher("/form_livre.jsp").forward(request, response);
			return;
		}

		int annee;
		try {
			annee = Integer.parseInt(anneePublication);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Erreur, l'année de publication doit être un nombre valide.");
			request.getRequestDispatcher("/form_livre.jsp").forward(request, response);
			return;
		}

		Livre newLivre = new Livre(0, titre, auteur, annee, genre);

		try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            LivreDAO livreDAO = new LivreDAO(daoFactory);
            
            boolean success = livreDAO.addLivre(newLivre);
    		
    		if (success) {
    			response.sendRedirect(request.getContextPath() + "/livres");
    		} else {
    			request.setAttribute("error", "Erreur lors de l'ajout du livre. Veuillez réessayer.");
    			request.getRequestDispatcher("/form_livre.jsp").forward(request, response);
    		}
        } catch (DAOConfigurationException e) {
            e.printStackTrace();
        }
	}

}
