package location;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

/**
 * Gestion des utilisateurs, de la classe Utilisateur.
 *
 * @author Ainhoa ESONO MANGUE
 */

public class Utilisateur implements InterUtilisateur, Serializable {
	/**
	 * Classe responsable de la gestion des utilisateurs dans l'application. Permet
	 * l'inscription, la connexion, la déconnexion et la gestion des utilisateurs.
	 */
	private GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
	
	/**Gestion des évaluations des utilisateurs.*/
	private GestionEvaluation gestionEvaluation = new GestionEvaluation();
	
	/**Classe de gestion des films.*/
	private GestionFilm gestionFilm = new GestionFilm();
	
	/**Classe de gestion des artistes.*/
	private GestionArtiste gestionArtiste = new GestionArtiste();
	// ************************** ATTRIBUTS

	/** Le pseudo de l'utilisateur. */
	private String pseudo;

	/** Le mot de passe de l'utilisateur. */
	private String motdepasse;

	/** Les informations personnelles de l'utilisateur. */
	private InformationPersonnelle info;

	// ************************** CONSTRUCTEUR

	/**
	 * Constructeur : initialise les informations de l'utilisateur.
	 *
	 * @param pseudo     le pseudo de l'utilisateur
	 * @param motdepasse le mot de passe de l'utilisateur
	 * @param info       les informations personnelles de l'utilisateur
	 */
	public Utilisateur(String pseudo, String motdepasse, InformationPersonnelle info) {
		this.pseudo = pseudo;
		this.motdepasse = motdepasse;
		this.info = info;
	}

	// ************************** ACCESSEURS

	/**
	 * Retourne le pseudo de l'utilisateur.
	 *
	 * @return le pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Retourne le mot de passe de l'utilisateur.
	 *
	 * @return le mot de passe
	 */
	public String getMotDePasse() {
		return motdepasse;
	}

	/**
	 * Retourne les informations personnelles de l'utilisateur.
	 *
	 * @return les informations personnelles
	 */
	public InformationPersonnelle getInfo() {
		return info;
	}

	// ************************** MÉTHODES DE L'INTERFACE `InterUtilisateur`

	/**
	 * Permet à un utilisateur de s'inscrire avec un pseudo, un mot de passe et des
	 * informations personnelles.
	 *
	 * @param pseudo le pseudo choisi
	 * @param mdp    le mot de passe choisi
	 * @param info   les informations personnelles
	 * @return un code de statut d'inscription
	 */
	@Override
	public int inscription(String pseudo, String mdp, InformationPersonnelle info) {
		try {
			return this.gestionUtilisateur.inscription(pseudo, mdp, info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Permet à un utilisateur de se connecter avec son pseudo et son mot de passe.
	 *
	 * @param pseudo le pseudo de l'utilisateur
	 * @param mdp    le mot de passe de l'utilisateur
	 * @return true si la connexion réussit, false sinon
	 */
	@Override
	public boolean connexion(String pseudo, String mdp) {
		return this.gestionUtilisateur.connexion(pseudo, mdp);
	}

	/**
	 * Déconnecte l'utilisateur actuellement connecté.
	 *
	 * @throws NonConnecteException si aucun utilisateur n'est connecté
	 */
	@Override
	public void deconnexion() throws NonConnecteException {
	}

	@Override
	public void louerFilm(Film film) throws NonConnecteException, LocationException {
		// Implémentation de la location de film
	}

	@Override
	public void finLocationFilm(Film film) throws NonConnecteException, LocationException {
		// Implémentation pour terminer la location d'un film
	}

	@Override
	public boolean estLouable(Film film) throws NonConnecteException {
		return this.gestionFilm.estLouable(film);
	}

	@Override
	public Set<Film> filmsEnLocation() throws NonConnecteException {
		return this.gestionFilm.filmsEnLocation();
	}

	@Override
	public void ajouterEvaluation(Film film, Evaluation eval) throws NonConnecteException, LocationException {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifierEvaluation(Film film, Evaluation eval) throws NonConnecteException, LocationException {

	}

	@Override
	public Set<Film> ensembleFilms() {
		// TODO Auto-generated method stub
		return this.gestionFilm.ensembleFilms();
	}

	@Override
	public Set<Artiste> ensembleActeurs() {
		// TODO Auto-generated method stub
		return this.gestionArtiste.ensembleActeurs();
	}

	@Override
	public Set<Artiste> ensembleRealisateurs() {
		// TODO Auto-generated method stub
		return this.gestionArtiste.ensembleRealisateurs();
	}

	@Override
	public Artiste getActeur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return this.gestionArtiste.getActeur(nom, prenom);
	}

	@Override
	public Artiste getRealisateur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return this.gestionArtiste.getRealisateur(nom, prenom);
	}

	@Override
	public Film getFilm(String titre) {
		// TODO Auto-generated method stub
		return this.gestionFilm.getFilm(titre);
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {

		return this.gestionArtiste.ensembleFilmsRealisateur(realisateur);
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(String nom, String prenom) {

		return this.gestionArtiste.ensembleFilmsRealisateur(nom, prenom);
	}

	@Override
	public Set<Film> ensembleFilmsActeur(Artiste acteur) {

		return this.gestionArtiste.ensembleFilmsActeur(acteur);
	}

	@Override
	public Set<Film> ensembleFilmsActeur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return this.gestionArtiste.ensembleFilmsActeur(nom, prenom);
	}

	@Override
	public Set<Film> ensembleFilmsGenre(Genre genre) {

		return this.gestionFilm.ensembleFilmsGenre(genre);
	}

	@Override
	public Set<Film> ensembleFilmsGenre(String genre) {
		// TODO Auto-generated method stub
		return this.gestionFilm.ensembleFilmsGenre(genre);
	}

	@Override
	public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
		// TODO Auto-generated method stub
		return this.gestionEvaluation.ensembleEvaluationsFilm(film);
	}

	@Override
	public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
		// TODO Auto-generated method stub
		return this.gestionEvaluation.ensembleEvaluationsFilm(titre);
	}

	@Override
	public double evaluationMoyenne(Film film) {
		// TODO Auto-generated method stub
		return this.gestionEvaluation.evaluationMoyenne(film);
	}

	@Override
	public double evaluationMoyenne(String titre) {
		// TODO Auto-generated method stub
		return this.gestionEvaluation.evaluationMoyenne(titre);
	}
}
