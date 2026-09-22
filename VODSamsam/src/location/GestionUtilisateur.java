package location;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import io.Sauvegarde;

/**
 * Classe responsable de la gestion des utilisateurs dans l'application. Permet
 * l'inscription, la connexion, la déconnexion et la gestion des utilisateurs.
 * 
 * @author Brossard Rayan
 */
public class GestionUtilisateur implements Serializable {

	// ************************** ATTRIBUTS

	/**
	 * Stocke les utilisateurs inscrits dans l'application.
	 */
	private Set<Utilisateur> utilisateurs = new HashSet<>();

	/**
	 * Référence de l'utilisateur actuellement connecté. Null si aucun utilisateur
	 * n'est connecté.
	 */
	private Utilisateur utilisateurConnecte = null;

	/**
	 * Sauvegarde des données de l'utilisateur.
	 */
	private Sauvegarde sauvegarde;

	// ************************** CONSTRUCTEUR

	/**
	 * Constructeur : Initialise la sauvegarde de l'utilisateur.
	 *
	 */
	public GestionUtilisateur() {
		sauvegarde = new Sauvegarde();
		try {
			sauvegarde.chargerDonnees("src/monFichier.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sauvegarde.getUtilisateurs() != null) {
			utilisateurs = sauvegarde.getUtilisateurs();
		}
	}

	// ************************** METHODES

	/**
	 * Inscrit un nouvel utilisateur dans le système.
	 * 
	 * @param pseudo le pseudo de l'utilisateur
	 * @param mdp    le mot de passe de l'utilisateur
	 * @param info   les informations personnelles de l'utilisateur
	 * @return 0 si l'inscription a réussi, 1 si le pseudo est déjà utilisé, 2 si le
	 *         pseudo ou le mot de passe est vide, 3 si les informations
	 *         personnelles sont incorrectes.
	 * @throws IOException
	 */
	public int inscription(String pseudo, String mdp, InformationPersonnelle info) throws IOException {
		if (pseudo == null || pseudo.isEmpty() || mdp == null || mdp.isEmpty()) {
			return 2;
		}

		if (info == null || info.getNom() == null || info.getPrenom() == null) {
			return 3;
		}

		Iterator<Utilisateur> iterator = this.utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur utilisateur = iterator.next();
			if (utilisateur.getPseudo().equals(pseudo)) {
				return 1;
			}
		}

		Utilisateur utilisateur = new Utilisateur(pseudo, mdp, info);
		utilisateurs.add(utilisateur);

		sauvegarde.setUtilisateurs(utilisateurs);
		sauvegarde.sauvegarderDonnees("src/monFichier.txt");
		return 0;
	}

	/**
	 * Connecte un utilisateur existant.
	 * 
	 * @param pseudo le pseudo de l'utilisateur
	 * @param mdp    le mot de passe de l'utilisateur
	 * @return true si la connexion a réussi, false sinon.
	 */
	public boolean connexion(String pseudo, String mdp) {

		Iterator<Utilisateur> iterator = this.utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur utilisateur = iterator.next();
			if (utilisateur.getPseudo().equals(pseudo) && utilisateur.getMotDePasse().equals(mdp)) {
				utilisateurConnecte = utilisateur;
				return true;
			}

		}

		return false;

	}

	/**
	 * Déconnecte l'utilisateur actuellement connecté.
	 * 
	 * @throws NonConnecteException si aucun utilisateur n'est connecté.
	 */
	public void deconnexion() throws NonConnecteException {
		if (utilisateurConnecte == null) {
			throw new NonConnecteException("Aucun utilisateur n'est connecté.");
		}
		utilisateurConnecte = null;
	}

	/**
	 * Vérifie si un utilisateur est actuellement connecté.
	 * 
	 * @return null si aucun utilisateur n'est connecté.
	 */
	public boolean estConnecte() {
		return utilisateurConnecte != null;
	}

	/**
	 * Retourne l'utilisateur actuellement connecté.
	 * 
	 * @return l'utilisateur connecté, ou null si aucun utilisateur n'est connecté.
	 */
	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	/**
	 * Retourne le pseudo de l'utilisateur inscrit.
	 * 
	 * @param pseudo le pseudo de l'utilisateur
	 * @return le pseudo de l'utilisateur, ou null si aucun pseudo d'utilisateur
	 *         n'est inscrit.
	 */
	public Utilisateur getUtilisateur(String pseudo) {
		Iterator<Utilisateur> iterator = this.utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur utilisateur = iterator.next();
			if (utilisateur.getPseudo().equals(pseudo)) {

				return utilisateur;
			}

		}
		return null;

	}

	/**
	 * Suppprime l'utilisateur si il est inscrit.
	 * 
	 * @param pseudo le pseudo de l'utilisateur
	 * @return le pseudo de l'utilisateur, ou false si aucun pseudp d'utilisateur
	 *         n'est inscrit.
	 */
	public boolean supprimerUtilisateur(String pseudo) {
		Iterator<Utilisateur> iterator = this.utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur utilisateur = iterator.next();
			if (utilisateur.getPseudo().equals(pseudo)) {

				return utilisateurs.remove(utilisateur);
			}

		}
		return false;
	}

	/**
	 * Retourne l'ensemble des utilisateurs inscrit.
	 * 
	 * @return l'ensemble des utilisateurs inscrit.
	 */
	public Set<Utilisateur> ensembleUtilisateurs() {

		return utilisateurs;
	}

}
