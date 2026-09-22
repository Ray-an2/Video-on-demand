package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Classe représentant un film dans le système de location. Un film possède un
 * titre, une année de sortie, un genre, un réalisateur, une liste d'acteurs,
 * une liste d'évaluations, et un état de disponibilité.
 *
 * @author Ainhoa ESONO MANGUE
 */


public class Film implements Serializable {
  
  
  private static final long serialVersionUID = 4026408353251835506L;
  String titre;
  String affiche;
  private int annee;
  private Set<Genre> genre;
  private Realisateur realisateur;
  private Set<Acteur> acteurs;
  private Set<Evaluation> evaluations;
  private boolean disponible;
  
  
  /**
   * Constructeur pour initialiser un objet Film avec ses attributs de base.
   *
   * @param titre le titre du film
   * @param annee l'année de sortie du film
   * @param genre le genre du film (exemple : COMEDIE, DRAME, etc.)
   * @param realisateur le réalisateur du film
   */
  
  public Film(String titre, int annee, Genre genre, String affiche,
      Realisateur realisateur) {
    this.titre = titre;
    this.annee = annee;
    this.genre = new HashSet<>();
    this.affiche = affiche;
    this.realisateur = realisateur;
    this.acteurs = new HashSet<>();
    this.evaluations = new HashSet<>();
    this.disponible = true;
  }
  
  
  /**
   * Constructeur pour initialiser un objet Film sans le genre.
   *
   * @param titre le titre du film
   * @param annee l'année de sortie du film
   * @param realisateur le réalisateur du film
   */
  
  public Film(String titre, int annee, Realisateur realisateur) {
    this.titre = titre;
    this.annee = annee;
    this.realisateur = realisateur;
    this.acteurs = new HashSet<>();
    this.evaluations = new HashSet<>();
  }
  
  /**
   * Obtenir le titre du film.
   *
   * @return le titre du film
   */
  public String getTitre() {
    return titre;
  }
  
  /**
   * Définit le titre du film.
   *
   * @param titre le nouveau titre
   */
  public void setTitre(String titre) {
    this.titre = titre;
  }
  
  /**
   * Obtenir l'année du film.
   *
   * @return l'année du film
   */
  public int getAnnee() {
    return annee;
  }
  
  /**
   * Définit l'année du film.
   *
   * @param annee la nouvelle anne
   */
  public void setAnnee(int annee) {
    this.annee = annee;
  }
  
  /**
   * Obtenir le genre du film.
   *
   * @return le genre du film
   */
  public Set<Genre> getGenre() {
    return genre;
  }
  
  /**
   * Obtenir l'affiche du film.
   *
   * @return l'affiche du film
   */
  public String getAffiche() {
    return affiche;
  }
  
  /**
   * Définit l'affiche du film.
   *
   * @param affiche la nouvelle affiche
   */
  public void setAffiche(String affiche) {
    this.affiche = affiche;
  }
  
  /**
   * Définit le genre du film.
   *
   * @param genre le nouveau genre
   */
  public void addGenre(Genre genre) {
    if (this.genre == null) {
      this.genre = new HashSet<>();
    }
    if (genre != null) {
      this.genre.add(genre);
    }
  }
  
  /**
   * Obtenir le realisateur du film.
   *
   * @return le realisateur du film
   */
  public Realisateur getRealisateur() {
    return realisateur;
  }
  
  /**
   * Définit le réalisateur du film.
   *
   * @param realisateur le nouveau réalisateur
   */
  public void setRealisateur(Realisateur realisateur) {
    this.realisateur = realisateur;
  }
  
  /**
   * Obtenir la liste des acteurs du film.
   *
   * @return l'ensemble des acteurs du film
   */
  public Set<Acteur> getActeurs() {
    return acteurs;
    
  }
  
  /**
   * Ajoute un acteur au film.
   *
   * @param acteur l'acteur à ajouter
   */
  public void setActeurs(Acteur acteur) {
    if (this.acteurs == null) {
      this.acteurs = new HashSet<>();
    }
    if (acteur != null) {
      this.acteurs.add(acteur);
    }

  }
  
  /**
   * Modifie la liste des acteurs.
   *
   * @param acteurs l'acteur à ajouter
   */
  public void setActeurs(Set<Acteur> acteurs) {
    this.acteurs = acteurs;
    
  }
  
  /**
   * Obtenir l'evaluation du film.
   *
   * @return l'evaluation du film
   */
  public Set<Evaluation> getEvaluations() {
    return evaluations;
  }
  
  /**
   * Ajoute une évaluation au film.
   *
   * @param evaluation l'évaluation à ajouter
   */
  public void ajoutEvaluation(Evaluation evaluation) {
    this.evaluations.add(evaluation);
  }
  
  /**
   * Vérifie si le film est disponible pour la location.
   *
   * @return true si le film est disponible, false sinon
   */
  public boolean getdisponible() {
    return disponible;
  }
  
  
  /**
   * Modifie l'état de disponibilité du film.
   *
   * @param disponible true si le film est disponible, false sinon
   */
  public void setdisponible(boolean disponible) {
    this.disponible = disponible;
  }
  
  
  /**
   * Retourne une représentation textuelle du film.
   *
   * @return une chaîne décrivant le film
   */
  public String toString() {
    return "Film{" + "titre " + titre + ",annee" + annee + ",genre" + genre
        + ", affiche" + affiche + "realisateur" + realisateur + "acteurs"
        + acteurs + "evaluations" + evaluations + "disponible" + disponible
        + "}";
  }


 


  public void setGenre(Set<Genre> genres) {
    this.genre = genres;
    
  }
  
  
}
