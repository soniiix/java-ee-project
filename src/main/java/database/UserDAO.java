package database;

import model.User;
import java.sql.*;

public class UserDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/javaee";
    private static final String USER = "user1";
    private static final String PASSWORD = "password";

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (nom, prenom, login, mdp) VALUES (?, ?, ?, ?)";
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	                PreparedStatement statement = connection.prepareStatement(sql)) {
		        	statement.setString(1, user.getNom());
		        	statement.setString(2, user.getPrenom());
		        	statement.setString(3, user.getLogin());
		        	statement.setString(4, user.getMdp());
		
		            return statement.executeUpdate() > 0;
	        	}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            
	            statement.setString(1, login);
	            System.out.println("Exécution de la requête : " + statement.toString()); // Debug
	
	            ResultSet rs = statement.executeQuery();
	
	            if (rs.next()) {
	            	return new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);

	            } else {
	                System.out.println("Aucun utilisateur trouvé pour le login : " + login);
	            }
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
