package ui;

import io.Sauvegarde;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import location.Artiste;
import location.Film;
import location.Genre;
import location.GestionArtiste;
import location.GestionFilm;

/**
 * Controleur JavaFX de la fenêtre d'administration.
 *
 * @author Eric Cariou
 *
 */
public class AdministrationControleur {
  
  @FXML
  private CheckBox checkBoxLocationFilm;
  
  @FXML
  private TextField entreeAffiche;
  
  @FXML
  private TextField entreeAnneeFilm;
  
  @FXML
  private TextField entreeNationaliteArtiste;
  
  @FXML
  private TextField entreeNomArtiste;
  
  @FXML
  private TextField entreeNomPrenomRealisateur;
  
  @FXML
  private TextField entreePrenomArtiste;
  
  @FXML
  private TextField entreeTitreFilm;
  
  @FXML
  private Label labelListeArtistes;
  
  @FXML
  private Label labelListeFilms;
  
  @FXML
  private ListView<Artiste> listeArtistes;
  
  @FXML
  // private ChoiceBox<String> listeChoixAgeLimite;
  private ChoiceBox<Integer> listeChoixAgeLimite;
  
  @FXML
  // private ChoiceBox<String> listeChoixAgeLimite;
  private ChoiceBox<String> listeRole;
  
  @FXML
  private ListView<Film> listeFilms;
  
  @FXML
  private ListView<Genre> listeGenresFilm;
  
  @FXML
  private ListView<Genre> listeTousGenres;
  
  private Sauvegarde sauvegarde = new Sauvegarde();
  
  private GestionArtiste gestionArtiste = new GestionArtiste();
  
  private GestionFilm gestionFilm = new GestionFilm();
  
  @FXML
  void actionBoutonAfficherArtistesActeurs(ActionEvent event) {
    listeArtistes.getItems().clear();
    listeArtistes.getItems().addAll(gestionArtiste.ensembleActeurs());
  }
  
  @FXML
  void actionBoutonAfficherArtistesRealisateurs(ActionEvent event) {
    listeArtistes.getItems().clear();
    listeArtistes.getItems().addAll(gestionArtiste.ensembleRealisateurs());
  }
  
  @FXML
  void actionBoutonAfficherFilmsActeurSelectionne(ActionEvent event) {
    listeFilms.getItems().clear();
    
    if (gestionArtiste.ensembleFilmsActeur(
        listeArtistes.getSelectionModel().getSelectedItem()) == null) {
      afficherPopupErreur("Aucun film associé à ce film");
      return;
    }
    listeFilms.getItems().addAll(gestionArtiste.ensembleFilmsActeur(
        listeArtistes.getSelectionModel().getSelectedItem()));
  }
  
  @FXML
  void actionBoutonAfficherFilmsDuRealisateur(ActionEvent event) {
    listeFilms.getItems().clear();
    
    if (gestionArtiste.ensembleFilmsRealisateur(
        listeArtistes.getSelectionModel().getSelectedItem()) == null) {
      afficherPopupErreur("Aucun film associé à ce film");
      return;
    }
    
    
    listeFilms.getItems().addAll(gestionArtiste.ensembleFilmsRealisateur(
        listeArtistes.getSelectionModel().getSelectedItem()));
    
  }
  
  @FXML
  void actionBoutonAfficherFilmsRealisateurSelectionne(ActionEvent event) {
    listeFilms.getItems().clear();
    
    if (gestionArtiste.ensembleFilmsRealisateur(
        listeArtistes.getSelectionModel().getSelectedItem()) == null) {
      afficherPopupErreur("Aucun film associé à ce réalisateur");
      return;
    }
    
    listeFilms.getItems().addAll(gestionArtiste.ensembleFilmsRealisateur(
        listeArtistes.getSelectionModel().getSelectedItem()));
    
    
  }
  
  @FXML
  void actionBoutonAfficherTousActeursFilm(ActionEvent event) {
    listeArtistes.getItems().clear();
    listeArtistes.getItems()
        .addAll(listeFilms.getSelectionModel().getSelectedItem().getActeurs());
  }
  
  @FXML
  void actionBoutonAfficherTousArtistes(ActionEvent event) {
    listeArtistes.getItems().clear();
    listeArtistes.getItems().addAll(gestionArtiste.getListeArtistes());
  }
  
  @FXML
  void actionBoutonAjouterActeurFilm(ActionEvent event) {
    boolean act = gestionFilm.ajouterActeurs(
        listeFilms.getSelectionModel().getSelectedItem(), listeArtistes
            .getSelectionModel().getSelectedItems().toArray(new Artiste[0]));
    
    if (act) {
      afficherPopupInformation("acteur(s) bien ajouté(s) au film");
    } else {
      afficherPopupErreur("Oups, le(s) acteur(s) non ajouté(s)");
    }
    
    
    
  }
  
  @FXML
  void actionBoutonAjouterGenreFilm(ActionEvent event) {
    boolean act = gestionFilm.ajouterGenres(
        listeFilms.getSelectionModel().getSelectedItem(), listeTousGenres
            .getSelectionModel().getSelectedItems().toArray(new Genre[0]));
    
    if (act) {
      afficherPopupInformation("Genre(s) bien ajouté(s) au film");
      // listeFilms.getSelectionModel().getSelectedItem().getGenre();
    } else {
      afficherPopupErreur("Oups, le(s) genre(s) non ajouté(s)");
    }
  }
  
  @FXML
  void actionBoutonChercherArtiste(ActionEvent event) {
    listeArtistes.getItems().clear();
    if (entreePrenomArtiste.getText().isEmpty()
        || entreeNomArtiste.getText().isEmpty()) {
      afficherPopupErreur("Champs vides. Veuillez les remplir svp");
      
    }
    if (gestionArtiste.getArtiste(entreeNomArtiste.getText(),
        entreePrenomArtiste.getText()) == null) {
      afficherPopupErreur("Aucun artiste correspondant");
    } else {
      listeArtistes.getItems().addAll(gestionArtiste.getArtiste(
          entreeNomArtiste.getText(), entreePrenomArtiste.getText()));
    }
    
  }
  
  @FXML
  void actionBoutonChercherFilm(ActionEvent event) {
    listeFilms.getItems().clear();
    if (entreeTitreFilm.getText().isEmpty()) {
      afficherPopupErreur("Champ titre vide. Veuillez le remplir svp");
      
    }
    if (gestionFilm.getFilm(entreeTitreFilm.getText()) == null) {
      afficherPopupErreur("Aucun film correspondant");
    } else {
      listeFilms.getItems()
          .addAll(gestionFilm.getFilm(entreeTitreFilm.getText()));
    }
  }
  
  @FXML
  void actionBoutonChoisirArtisteSelectionneRealisateur(ActionEvent event) {
    entreeNomPrenomRealisateur.setText(
        listeArtistes.getSelectionModel().getSelectedItem().getNom() + " "
            + listeArtistes.getSelectionModel().getSelectedItem().getPrenom());
    
  }
  
  
  @FXML
  void actionBoutonEnregistrerArtiste(ActionEvent event) {
    if (entreePrenomArtiste.getText().isEmpty()
        || entreeNationaliteArtiste.getText().isEmpty()
        || entreeNomArtiste.getText().isEmpty()
        || listeRole.getValue() == null) {
      afficherPopupErreur("Champs vides. Veuillez les remplir svp");
      
    }
    
    
    gestionArtiste.setRole(listeRole.getValue());
    
    Artiste artiste = gestionArtiste.creerArtiste(entreeNomArtiste.getText(),
        entreePrenomArtiste.getText(), entreeNationaliteArtiste.getText());
    
    if (artiste != null) {
      afficherPopupInformation("Artiste créé avec succès");
      System.out.println(artiste.getNom());
    } else {
      afficherPopupErreur("Oups, l'artiste n'a pas pu être créé");
    }
  }
  
  @FXML
  void actionBoutonEnregistrerFilm(ActionEvent event) {
    if (entreeTitreFilm.getText().isEmpty()
        || entreeAnneeFilm.getText().isEmpty()
        
        || listeChoixAgeLimite.getValue() == null) {
      afficherPopupErreur("Champs vides. Veuillez les remplir svp");
      
    }
    Film film = gestionFilm.creerFilm(entreeTitreFilm.getText(),
        listeArtistes.getSelectionModel().getSelectedItem(),
        Integer.parseInt(entreeAnneeFilm.getText()),
        listeChoixAgeLimite.getValue());
    if (film != null) {
      afficherPopupInformation("Film créé avec succès");
      System.out.println(film.getTitre());
    } else {
      afficherPopupErreur("Oups, le film n'a pas pu être créé");
    }
  }
  
  @FXML
  void actionBoutonNouveauArtiste(ActionEvent event) {
    entreeNomArtiste.setText("");
    entreeNationaliteArtiste.setText("");
    entreePrenomArtiste.setText("");
    listeRole.setValue(null);
    
  }
  
  @FXML
  void actionBoutonNouveauFilm(ActionEvent event) {
    entreeTitreFilm.setText("");
    entreeAnneeFilm.setText("");
    listeChoixAgeLimite.setValue(null);
    entreeNomPrenomRealisateur.setText("");
    
  }
  
  @FXML
  void actionBoutonParcourirAffiche(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    
    fileChooser.setTitle("Choisissez un fichier");
    File fichier = fileChooser.showOpenDialog(
        ((javafx.scene.Node) event.getSource()).getScene().getWindow());
    try {
      afficherPopupInformation("Affiche bien ajoutée");
      gestionFilm.ajouterAffiche(
          listeFilms.getSelectionModel().getSelectedItem(),
          fichier.getAbsolutePath());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      afficherPopupErreur("Oups, affiche non ajoutée");
      e.printStackTrace();
    }
  }
  
  @FXML
  void actionBoutonSupprimerArtiste(ActionEvent event) {
    boolean sup = gestionArtiste
        .supprimerArtiste(listeArtistes.getSelectionModel().getSelectedItem());
    if (sup) {
      afficherPopupInformation("Supression bien effectuée");
      
    } else {
      afficherPopupErreur("Oups,la supression a échoué");
    }
  }
  
  @FXML
  void actionBoutonSupprimerFilm(ActionEvent event) {
    boolean sup = gestionFilm
        .supprimerFilm(listeFilms.getSelectionModel().getSelectedItem());
    if (sup) {
      afficherPopupInformation("Supression bien effectuée");
      
    } else {
      afficherPopupErreur("Oups,la supression a échoué");
    }
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("À propos");
    alert.setHeaderText("Application de gestion de films");
    alert.setContentText(
        "Cette application permet de gérer une collection de films.\n"
            + "L'admin peut ajouter et supprimer des films, ainsi que gérer leur disponibilité. "
            + "Les utilisateurs quant à eux "
            + "peuvent louer les films et donner des évaluations.");
    
    
    alert.showAndWait();
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Platform.exit();
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    sauvegarde.setArtistes(gestionArtiste.getListeArtistes());
    sauvegarde.setFilms(gestionFilm.ensembleFilms());
    try {
      sauvegarde.sauvegarderDonnees("src/monFichier.txt");
      afficherPopupInformation("Sauvegarde bien effectuée");
    } catch (IOException e) {
      afficherPopupErreur("Oups,la sauvegarde a échoué");
      e.printStackTrace();
    }
    // Ajouter le mot clé serializable sur les classes qu'on veut sauvegarder
  }
  
  @FXML
  void actionListeSelectionArtiste(MouseEvent event) {
    
  }
  
  private boolean isInternalChange = false; 
  
  @FXML
  void initialize() {
    listeChoixAgeLimite.getItems().addAll(0, 10, 12, 16, 18, 21);
    listeRole.getItems().addAll("Acteur", "Realisateur");
    listeArtistes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    listeTousGenres.getItems().addAll(Genre.values());
    listeTousGenres.getSelectionModel()
        .setSelectionMode(SelectionMode.MULTIPLE);
    
    // Listener pour la CheckBox
    checkBoxLocationFilm.selectedProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (isInternalChange) {
            return; 
            
          }
          
          if (newValue) {
            if (gestionFilm.ouvrirLocation(
                listeFilms.getSelectionModel().getSelectedItem())) {
              afficherPopupInformation("Location ouverte");
            } else {
              afficherPopupErreur("Echec de l'ouverture de la location");
            }
          } else {
            if (gestionFilm.fermerLocation(
                listeFilms.getSelectionModel().getSelectedItem())) {
              afficherPopupInformation("Location fermée");
            } else {
              afficherPopupErreur("Echec de la fermeture de la location");
            }
          }
        });
    
    System.out.println(gestionFilm.ensembleFilms());
  }
  
  @FXML
  void actionListeSelectionFilm(MouseEvent event) {
    
    listeGenresFilm.getItems().clear();
    
    
    if (listeFilms.getSelectionModel().getSelectedItem() != null) {
      Film filmSelectionne = listeFilms.getSelectionModel().getSelectedItem();
      
      
      if (filmSelectionne.getGenre() != null) {
        listeGenresFilm.getItems().addAll(filmSelectionne.getGenre());
      }
      
      
      isInternalChange = true;
      checkBoxLocationFilm.setSelected(filmSelectionne.getdisponible());
      isInternalChange = false; 
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
