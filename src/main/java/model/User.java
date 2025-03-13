package model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	
	public User(String nom, String prenom, String login, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = hashPassword(mdp);
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.mdp);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = hashPassword(mdp);
    }
}
