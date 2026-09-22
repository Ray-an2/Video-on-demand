package io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import location.Artiste;
import location.Evaluation;
import location.Film;
import location.Utilisateur;


/**
 * Implémentations des méthodes méthodes permettant de sauvegarder les données
 * de l'application dans un fichier.
 *
 * @author Ainhoa ESONO MANGUE
 */
public class Sauvegarde implements InterSauvegarde,Serializable {
  
  private Set<Film> films;
  private Set<Artiste> artistes;
  private Set<Evaluation> evaluations;
  private Set<Utilisateur> utilisateurs;
  
  
  /**
   * Constructeur de la classe Sauvegarde.
   *
   * @param films ensemble des films
   * @param artistes ensemble des artistes
   * @param evaluations ensemble des évaluations
   * @param utilisateurs ensemble des utilisateurs
   */
  public Sauvegarde(Set<Film> films, Set<Artiste> artistes,
      Set<Evaluation> evaluations, Set<Utilisateur> utilisateurs) {
    this.films = new HashSet<>(films);
    this.artistes = new HashSet<>(artistes);
    this.evaluations = new HashSet<>(evaluations);
    this.utilisateurs = new HashSet<>(utilisateurs);
  }
  
  
  public Sauvegarde() {
    
    this.films = new HashSet<>();
    this.artistes = new HashSet<>();
    this.evaluations = new HashSet<>();
    this.utilisateurs = new HashSet<>();
  }
  
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    try (ObjectOutputStream sauver =
        new ObjectOutputStream(new FileOutputStream(nomFichier))) {
      sauver.writeObject(films);
      sauver.writeObject(artistes);
      sauver.writeObject(evaluations);
      sauver.writeObject(utilisateurs);
    }
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    File file = new File(nomFichier);
    if (!file.exists() || isFileEmpty(nomFichier)) {
      System.out.println(
          "Fichier vide ou inexistant. Initialisation des données par défaut.");
      return;
    }
    
    try (ObjectInputStream charger =
        new ObjectInputStream(new FileInputStream(nomFichier))) {
      films = (Set<Film>) charger.readObject();
      artistes = (Set<Artiste>) charger.readObject();
      evaluations = (Set<Evaluation>) charger.readObject();
      utilisateurs = (Set<Utilisateur>) charger.readObject();
    } catch (ClassNotFoundException e) {
      throw new IOException(
          "Erreur lors du chargement des données : classe introuvable", e);
    }
  }
  
  public boolean isFileEmpty(String fileName) {
    File file = new File(fileName);
    return file.length() == 0;
  }
  
  
  // Getters pour récupérer les données après le chargement
  
  public Set<Film> getFilms() {
    return films;
  }
  
  public Set<Artiste> getArtistes() {
    return artistes;
  }
  
  public Set<Evaluation> getEvaluations() {
    return evaluations;
  }
  
  public Set<Utilisateur> getUtilisateurs() {
    return utilisateurs;
  }
  
  
  // Setters pour mettre à jour les ensembles de données
  
  public void setFilms(Set<Film> films) {
    this.films = new HashSet<>(films);
  }
  
  public void setArtistes(Set<Artiste> artistes) {
    this.artistes = new HashSet<>(artistes);
  }
  
  public void setEvaluations(Set<Evaluation> evaluations) {
    this.evaluations = new HashSet<>(evaluations);
  }
  
  public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
    this.utilisateurs = new HashSet<>(utilisateurs);
  }
  
}
