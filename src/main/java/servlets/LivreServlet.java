package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Livre;

import database.LivreDAO;

/**
 * Servlet implementation class LivreServlet
 */
@WebServlet("/livres")
public class LivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreDAO livreDAO = new LivreDAO();
        
        String search = request.getParameter("q");
        String genre = request.getParameter("genre");
        String anneeStr = request.getParameter("annee");
        Integer annee = (anneeStr != null && !anneeStr.isEmpty()) ? Integer.parseInt(anneeStr) : null;

        ArrayList<Livre> livres = livreDAO.getLivresFiltres(search, genre, annee);
        ArrayList<String> genres = livreDAO.getGenres();
        ArrayList<Integer> annees = livreDAO.getAnnees();

        request.setAttribute("livres", livres);
        request.setAttribute("genres", genres);
        request.setAttribute("annees", annees);

        this.getServletContext().getRequestDispatcher("/WEB-INF/list_livre.jsp")
            .forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
