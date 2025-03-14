package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Livre;

public class LivreDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/javaee";
    private static final String USER = "user1";
    private static final String PASSWORD = "password";

    public ArrayList<Livre> getAllLivres() {
        ArrayList<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livres";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Livre livre = new Livre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getInt("anneePublication"),
                        resultSet.getString("genre")
                    );
                    livres.add(livre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livres;
    }
    
    public boolean addLivre(Livre livre) {
        String sql = "INSERT INTO livres (titre, auteur, anneePublication, genre) VALUES (?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, livre.getTitre());
                statement.setString(2, livre.getAuteur());
                statement.setInt(3, livre.getAnneePublication());
                statement.setString(4, livre.getGenre());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Livre getLivreById(int id) {
        Livre livre = null;
        String sql = "SELECT * FROM livres WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql)) {
        	
        	statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                livre = new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("anneePublication"),
                    rs.getString("genre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return livre;
    }
    
    public void updateLivre(Livre livre) {
        String sql = "UPDATE livres SET titre = ?, auteur = ?, anneePublication = ?, genre = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setString(1, livre.getTitre());
        	statement.setString(2, livre.getAuteur());
        	statement.setInt(3, livre.getAnneePublication());
        	statement.setString(4, livre.getGenre());
        	statement.setInt(5, livre.getId());
        	statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteLivre(int id) {
        String query = "DELETE FROM livres WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
