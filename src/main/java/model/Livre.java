package model;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private int anneePublication;
	private String genre;
	
	public Livre (int id, String titre, String auteur, int anneePublication, String genre) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.anneePublication = anneePublication;
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}
	
}
