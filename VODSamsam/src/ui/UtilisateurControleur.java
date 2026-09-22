
package ui;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import location.Acteur;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.Genre;
import location.GestionArtiste;
import location.GestionEvaluation;
import location.GestionFilm;
import location.GestionUtilisateur;
import location.InformationPersonnelle;
import location.LocationException;
import location.NonConnecteException;
import location.Utilisateur;

/**
 * Controleur JavaFX de la fenêtre utilisateur.
 *
 * @author Eric Cariou
 *
 */
public class UtilisateurControleur {

	@FXML
	private CheckBox checkFilmLouable;

	@FXML
	private TextField entreeAdresseUtilisateur;

	@FXML
	private TextField entreeAgeLimiteFilm;

	@FXML
	private TextField entreeAgeUtilisateur;

	@FXML
	private TextField entreeAnneeFilm;

	@FXML
	private TextField entreeAuteurEvaluation;

	@FXML
	private TextField entreeEvaluationMoyenne;

	@FXML
	private TextField entreeGenresFilm;

	@FXML
	private TextField entreeMotDePasseUtilisateur;

	@FXML
	private TextField entreeNationaliteArtiste;

	@FXML
	private TextField entreeNomArtiste;

	@FXML
	private TextField entreeNomPrenomRealisateurFilm;

	@FXML
	private TextField entreeNomUtilisateur;

	@FXML
	private TextField entreePrenomArtiste;

	@FXML
	private TextField entreePrenomUtilisateur;

	@FXML
	private TextField entreePseudoUtilisateur;

	@FXML
	private TextField entreeTitreFilm;

	@FXML
	private Label labelListeFilms;

	@FXML
	private Label labelListeArtistes;

	@FXML
	private ListView<String> listeArtistes;

	@FXML
	private ListView<String> listeEvaluations;

	@FXML
	private ListView<String> listeFilms;

	@FXML
	private ListView<String> listeFilmsEnLocation;

	@FXML
	private ListView<String> listeGenresFilm;

	@FXML
	private ChoiceBox<Integer> listeNoteEvaluation;

	@FXML
	private TextArea texteCommentaire;

	@FXML
	private StackPane paneAffiche;

	private GestionArtiste gestionArtiste = new GestionArtiste();
	private GestionFilm gestionFilm = new GestionFilm();
	private GestionEvaluation gestionEvaluation = new GestionEvaluation();
	private GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();

	@FXML
	void actionBoutonAfficherActeursFilmSelectionne(ActionEvent event) {

		String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
		Film film = gestionFilm.getFilm(titreFilm);
		if (film == null) {
			afficherPopupErreur("Film non trouvé.");
			return;
		}
		Set<Acteur> acteurs = film.getActeurs();

		Iterator<Acteur> it = acteurs.iterator();
		Set<String> nomPrenom = new HashSet<>();

		while (it.hasNext()) {
			Acteur acteur = it.next();
			nomPrenom.add(acteur.getNom() + " " + acteur.getPrenom());
		}
		listeArtistes.getItems().clear();
		if (listeArtistes.getItems().addAll(nomPrenom)) {
			afficherPopupInformation("Acteurs du film affichés avec succès.");
		} else {
			afficherPopupErreur("Acteurs du film non trouvés.");
		}

	}

	@FXML
	void actionBoutonAfficherArtistesActeurs(ActionEvent event) {

		listeArtistes.getItems().clear();
		Set<Artiste> acteurs = gestionArtiste.ensembleActeurs();
		for (Artiste acteur : acteurs) {
			listeArtistes.getItems().add(acteur.getPrenom() + " " + acteur.getNom());
		}

		afficherPopupInformation("Acteurs affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {

		listeArtistes.getItems().clear();
		Set<Artiste> realisateurs = gestionArtiste.ensembleRealisateurs();
		for (Artiste realisateur : realisateurs) {
			listeArtistes.getItems().add(realisateur.getPrenom() + " " + realisateur.getNom());
		}

		afficherPopupInformation("Réalisateurs affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherFilmLoue(ActionEvent event) {

		listeFilmsEnLocation.getItems().clear();
		try {
			Set<Film> filmsLoues = gestionFilm.filmsEnLocation();
			for (Film film : filmsLoues) {
				listeFilmsEnLocation.getItems().add(film.getTitre());
			}
		} catch (NonConnecteException e) {
			afficherPopupErreur("Aucun utilisateur n'est connecté.");
		}

		afficherPopupInformation("Films loués affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherFilmRealisateurSelectionne(ActionEvent event) {

		String[] nomRealisateur = listeArtistes.getSelectionModel().getSelectedItem().split(" ");
		Artiste realisateur = gestionArtiste.getRealisateur(nomRealisateur[0], nomRealisateur[1]);
		if (realisateur == null) {
			afficherPopupErreur("Réalisateur non trouvé.");
			return;
		}

		listeFilms.getItems().clear();
		Set<Film> filmsRealisateur = gestionArtiste.ensembleFilmsRealisateur(realisateur);
		for (Film film : filmsRealisateur) {
			listeFilms.getItems().add(film.getTitre());
		}

		afficherPopupInformation("Films du réalisateur affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {

		String[] nomActeur = listeArtistes.getSelectionModel().getSelectedItem().split(" ");
		Artiste acteur = gestionArtiste.getActeur(nomActeur[1], nomActeur[0]);
		if (acteur == null) {
			afficherPopupErreur("Acteur non trouvé.");
			return;
		}

		listeFilms.getItems().clear();
		Set<Film> filmsActeur = gestionArtiste.ensembleFilmsActeur(acteur);
		for (Film film : filmsActeur) {
			listeFilms.getItems().add(film.getTitre());
		}

		afficherPopupInformation("Films de l'acteur affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherFilmsGenre(ActionEvent event) {

		String genre = listeGenresFilm.getSelectionModel().getSelectedItem();
		if (genre == null) {
			afficherPopupErreur("Genre non sélectionné.");
			return;
		}

		listeFilms.getItems().clear();
		Set<Film> filmsGenre = gestionFilm.ensembleFilmsGenre(Genre.valueOf(genre));
		for (Film film : filmsGenre) {
			listeFilms.getItems().add(film.getTitre());
		}

		afficherPopupInformation("Films du genre affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {

		String[] nomRealisateur = listeArtistes.getSelectionModel().getSelectedItem().split(" ");
		Artiste realisateur = gestionArtiste.getRealisateur(nomRealisateur[1], nomRealisateur[0]);
		if (realisateur == null) {
			afficherPopupErreur("Réalisateur non trouvé.");
			return;
		}

		listeFilms.getItems().clear();
		Set<Film> filmsRealisateur = gestionArtiste.ensembleFilmsRealisateur(realisateur);
		for (Film film : filmsRealisateur) {
			listeFilms.getItems().add(film.getTitre());
		}

		afficherPopupInformation("Films du réalisateur affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherMonEvaluation(ActionEvent event) {

		Utilisateur utilisateur = gestionUtilisateur.getUtilisateurConnecte();
		if (utilisateur == null) {
			afficherPopupErreur("Aucun utilisateur n'est connecté.");
			return;
		}

		listeEvaluations.getItems().clear();
		Set<Evaluation> evaluations = gestionFilm.getFilm(listeFilms.getSelectionModel().getSelectedItem())
				.getEvaluations();
		for (Evaluation evaluation : evaluations) {
			listeEvaluations.getItems().add(evaluation.toString());
		}

		afficherPopupInformation("Évaluations affichées avec succès.");
	}

	@FXML
	void actionBoutonAfficherTousArtistes(ActionEvent event) {

		listeArtistes.getItems().clear();
		Set<Artiste> acteurs = gestionArtiste.ensembleActeurs();
		Set<Artiste> realisateurs = gestionArtiste.ensembleRealisateurs();

		for (Artiste acteur : acteurs) {
			listeArtistes.getItems().add(acteur.getNom() + " " + acteur.getPrenom());
		}

		for (Artiste realisateur : realisateurs) {
			listeArtistes.getItems().add(realisateur.getNom() + " " + realisateur.getPrenom());
		}

		afficherPopupInformation("Artistes affichés avec succès.");
	}

	@FXML
	void actionBoutonAfficherTousFilms(ActionEvent event) {

		listeFilms.getItems().clear();
		Set<Film> films = gestionFilm.ensembleFilms();
		for (Film film : films) {
			listeFilms.getItems().add(film.getTitre());
		}

		afficherPopupInformation("Films affichés avec succès.");
	}

	@FXML
	void actionBoutonChercherActeur(ActionEvent event) {

		String nomActeur = entreeNomArtiste.getText();
		String prenomActeur = entreePrenomArtiste.getText();
		if (nomActeur == null || prenomActeur.isEmpty()) {
			afficherPopupErreur("Veuillez saisir le nom de l'acteur.");
			return;
		}

		Artiste acteur = gestionArtiste.getActeur(nomActeur, prenomActeur);
		if (acteur == null) {
			afficherPopupErreur("Acteur non trouvé.");
			return;
		}

		listeArtistes.getItems().clear();
		listeArtistes.getItems().add(acteur.getNom() + " " + acteur.getPrenom());

		afficherPopupInformation("Acteur trouvé avec succès.");
	}

	//
	@FXML
	void actionBoutonChercherFilm(ActionEvent event) {
		String titreFilm = entreeTitreFilm.getText();
		if (titreFilm == null || titreFilm.isEmpty()) {
			afficherPopupErreur("Veuillez entrer un titre de film.");
			return;
		}

		Film film = gestionFilm.getFilm(titreFilm);
		if (film == null) {
			afficherPopupErreur("Film non trouvé.");
			return;
		}

		listeFilms.getItems().clear();
		listeFilms.getItems().add(film.getTitre());

		afficherPopupInformation("Film trouvé avec succès.");
	}

	@FXML
	void actionBoutonChercherRealisateur(ActionEvent event) {
		String nomRealisateur = entreeNomArtiste.getText();
		String prenomRealisateur = entreePrenomArtiste.getText();
		if (nomRealisateur == null || nomRealisateur.isEmpty()) {
			afficherPopupErreur("Veuillez entrer le nom et prénom du réalisateur.");
			return;
		}

		Artiste realisateur = gestionArtiste.getRealisateur(nomRealisateur, prenomRealisateur);
		if (realisateur == null) {
			afficherPopupErreur("Réalisateur non trouvé.");
			return;
		}

		listeArtistes.getItems().clear();
		listeArtistes.getItems().add(realisateur.getNom() + " " + realisateur.getPrenom());

		afficherPopupInformation("Réalisateur trouvé avec succès.");

	}

	@FXML
	void actionBoutonConnexion(ActionEvent event) {
		String pseudo = entreePseudoUtilisateur.getText();
		String motDePasse = entreeMotDePasseUtilisateur.getText();
		if (pseudo == null || pseudo.isEmpty() || motDePasse == null || motDePasse.isEmpty()) {
			afficherPopupErreur("Veuillez entrer un pseudo et un mot de passe.");
			return;
		}

		try {
			gestionUtilisateur.connexion(pseudo, motDePasse);
			afficherPopupInformation("Connexion réussie.");
		} catch (Exception e) {
			afficherPopupErreur("Échec de la connexion : " + e.getMessage());
		}
	}

	@FXML
	void actionBoutonCreerMonEvaluation(ActionEvent event) {
		String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
		if (titreFilm == null) {
			afficherPopupErreur("Veuillez sélectionner un film.");
			return;
		}

		Film film = gestionFilm.getFilm(titreFilm);
		if (film == null) {
			afficherPopupErreur("Film non trouvé.");
			return;
		}

		int note = listeNoteEvaluation.getValue();
		String commentaire = texteCommentaire.getText();
		Utilisateur utilisateur = gestionUtilisateur.getUtilisateurConnecte();
		if (utilisateur == null) {
			afficherPopupErreur("Aucun utilisateur n'est connecté.");
			return;
		}

		try {
			Evaluation evaluation;
			if (commentaire != null && !commentaire.isEmpty()) {
				evaluation = new Evaluation(note, commentaire, film, utilisateur);
			} else {
				evaluation = new Evaluation(note, film, utilisateur);
			}
			afficherPopupInformation("Évaluation créée avec succès.");
		} catch (Exception e) {
			afficherPopupErreur("Erreur lors de la création de l'évaluation : " + e.getMessage());
		}
	}

	@FXML
	void actionBoutonDeconnexion(ActionEvent event) throws NonConnecteException {
		gestionUtilisateur.deconnexion();
		afficherPopupInformation("Déconnexion réussie.");
	}

	@FXML
	void actionBoutonFinLocation(ActionEvent event) {
		String titreFilm = listeFilmsEnLocation.getSelectionModel().getSelectedItem();
		if (titreFilm == null || titreFilm.isEmpty()) {
			afficherPopupErreur("Veuillez sélectionner un film.");
			return;
		}

		try {
			gestionFilm.finLocationFilm(titreFilm);
			afficherPopupInformation("Location terminée avec succès.");
			actionBoutonAfficherFilmLoue(null);
		} catch (Exception e) {
			afficherPopupErreur("Erreur lors de la fin de location : " + e.getMessage());
		}
	}

	@FXML
	void actionBoutonInscription(ActionEvent event) throws IOException {
		String nom = entreeNomUtilisateur.getText();
		String prenom = entreePrenomUtilisateur.getText();
		String adresse = entreeAdresseUtilisateur.getText();
		String pseudo = entreePseudoUtilisateur.getText();
		String motDePasse = entreeMotDePasseUtilisateur.getText();

		if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || adresse == null || adresse.isEmpty()
				|| pseudo == null || pseudo.isEmpty() || motDePasse == null || motDePasse.isEmpty()) {
			afficherPopupErreur("Veuillez remplir tous les champs.");
			return;
		}

		InformationPersonnelle info = new InformationPersonnelle(nom, prenom, adresse, 30);
		int resultat = gestionUtilisateur.inscription(pseudo, motDePasse, info);
		if (resultat == 0) {
			afficherPopupInformation("Inscription réussie.");
		} else if (resultat == 1) {
			afficherPopupErreur("Le pseudo est déjà pris.");
		} else if (resultat == 2) {
			afficherPopupErreur("Veuillez remplir tous les champs.");
		} else if (resultat == 3) {
			afficherPopupErreur("Les informations personnelles sont incomplètes.");
		}

	}

	@FXML
	void actionBoutonLouerFilmSelectionne(ActionEvent event) throws NonConnecteException, LocationException {
		String titreFilm = listeFilms.getSelectionModel().getSelectedItem();
		if (titreFilm == null) {
			afficherPopupErreur("Veuillez sélectionner un film.");
			return;
		}

		Film film = gestionFilm.getFilm(titreFilm);
		gestionFilm.louerFilm(film);
		;
		afficherPopupInformation("Film loué avec succès.");

	}

	@FXML
	void actionBoutonModifierMonEvaluation(ActionEvent event) {
		String evaluationSelectionnee = listeEvaluations.getSelectionModel().getSelectedItem();
		if (evaluationSelectionnee == null) {
			afficherPopupErreur("Veuillez sélectionner une évaluation à modifier.");
			return;
		}

		Integer nouvelleNote = listeNoteEvaluation.getValue();
		String nouveauCommentaire = texteCommentaire.getText();
		if (nouvelleNote == null || nouvelleNote < 0 || nouvelleNote > 5) {
			afficherPopupErreur("Veuillez entrer une note valide (entre 0 et 5).");
			return;
		}

		if (nouveauCommentaire != null && nouveauCommentaire.isEmpty()) {
			nouveauCommentaire = null;
		}
		Utilisateur utilisateur = gestionUtilisateur.getUtilisateurConnecte();
		if (utilisateur == null) {
			afficherPopupErreur("Aucun utilisateur n'est actuellement connecté.");
			return;
		}

		try {
			evaluationSelectionnee.setNote(nouvelleNote);
			evaluationSelectionnee.setCommentaire(nouveauCommentaire);
			afficherPopupInformation("Votre évaluation a été modifiée avec succès !");
		} catch (Exception e) {
			afficherPopupErreur("Une erreur s'est produite lors de la modification : " + e.getMessage());
		}
	}

	@FXML
	void actionSelectionArtiste(MouseEvent event) {
		String artisteSelectionne = listeArtistes.getSelectionModel().getSelectedItem();
		if (artisteSelectionne != null) {
			afficherPopupInformation("Artiste sélectionné : " + artisteSelectionne);
		}
	}

	@FXML
	void actionSelectionEvaluation(MouseEvent event) {
		String evaluationSelectionnee = listeEvaluations.getSelectionModel().getSelectedItem();
		if (evaluationSelectionnee != null) {
			afficherPopupInformation("Évaluation sélectionnée : " + evaluationSelectionnee);
		}
	}

	@FXML
	void actionSelectionFilm(MouseEvent event) {
		String filmSelectionne = listeFilms.getSelectionModel().getSelectedItem();
		if (filmSelectionne != null) {
			afficherPopupInformation("Film sélectionné : " + filmSelectionne);
		}
	}

	@FXML
	void initialize() {
		for (Genre genre : Genre.values()) {
			listeGenresFilm.getItems().add(genre.toString());
		}
	}

	private void afficherPopup(String message, AlertType type) {
		Alert alert = new Alert(type);
		if (type == AlertType.ERROR) {
			alert.setTitle("Erreur");
		} else {
			alert.setTitle("Information");
		}
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.setResizable(true);
		alert.showAndWait();
	}

	private void afficherPopupErreur(String message) {
		this.afficherPopup(message, AlertType.ERROR);
	}

	private void afficherPopupInformation(String message) {
		this.afficherPopup(message, AlertType.INFORMATION);
	}
}
