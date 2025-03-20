package dao;

import model.User;
import java.sql.*;

public class UserDAO {
    private DAOFactory daoFactory;

    public UserDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (nom, prenom, login, mdp) VALUES (?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getMdp());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            System.out.println("Exécution de la requête : " + statement.toString()); // Debug

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("mdp"), true);
            } else {
                System.out.println("Aucun utilisateur trouvé pour le login : " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET nom = ?, prenom = ?, login = ? WHERE login = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getLogin());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
