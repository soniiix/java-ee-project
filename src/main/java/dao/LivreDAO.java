package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Livre;

public class LivreDAO {
    private DAOFactory daoFactory;

    public LivreDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ArrayList<Livre> getAllLivres() {
        ArrayList<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livres";

        try (Connection connection = daoFactory.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public ArrayList<String> getGenres() {
        ArrayList<String> genres = new ArrayList<>();
        String sql = "SELECT DISTINCT(genre) FROM livres;";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                genres.add(resultSet.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public ArrayList<Integer> getAnnees() {
        ArrayList<Integer> annees = new ArrayList<>();
        String sql = "SELECT DISTINCT(anneePublication) FROM livres;";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                annees.add(resultSet.getInt("anneePublication"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annees;
    }

    public boolean addLivre(Livre livre) {
        String sql = "INSERT INTO livres (titre, auteur, anneePublication, genre) VALUES (?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setInt(3, livre.getAnneePublication());
            statement.setString(4, livre.getGenre());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Livre getLivreById(int id) {
        Livre livre = null;
        String sql = "SELECT * FROM livres WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
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

    public ArrayList<Livre> getLivresFiltres(String search, String genre, Integer annee) {
        ArrayList<Livre> livres = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM livres WHERE 1=1");

        if (search != null && !search.isEmpty()) {
            sql.append(" AND (titre LIKE ? OR auteur LIKE ?)");
        }
        if (genre != null && !genre.isEmpty()) {
            sql.append(" AND genre = ?");
        }
        if (annee != null) {
            sql.append(" AND anneePublication = ?");
        }

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (search != null && !search.isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
                statement.setString(paramIndex++, "%" + search + "%");
            }
            if (genre != null && !genre.isEmpty()) {
                statement.setString(paramIndex++, genre);
            }
            if (annee != null) {
                statement.setInt(paramIndex++, annee);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    livres.add(new Livre(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getInt("anneePublication"),
                        resultSet.getString("genre")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    public void updateLivre(Livre livre) {
        String sql = "UPDATE livres SET titre = ?, auteur = ?, anneePublication = ?, genre = ? WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
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
        String sql = "DELETE FROM livres WHERE id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
