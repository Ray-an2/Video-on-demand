package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Gestion des artistes, de la classe Artiste.
 *
 * @author Brossard Rayan
 */
public abstract class Artiste implements Serializable {
  
  // ************************** ATTRIBUTS
  
  /**
   * Une chaîne de caractère qui représente le nom de l'artiste.
   */
  protected String nom;
  
  /**
   * Une chaîne de caractère qui représente le prénom de l'artiste.
   */
  protected String prenom;
  
  /**
   * Une chaîne de caractère qui représente la nationalité de l'artiste.
   */
  protected String nation;
  
  /**
   * Liste des films d'un artiste.
   */
  protected Set<Film> films;
  
  
  // ************************** METHODES
  
  /**
   * Crée une personne avec toutes ses informations.
   *
   * @param nom le nom de la personne
   * @param prenom le prénom de la personne
   * @param nation nationalité de la personne
   */
  public Artiste(String nom, String prenom, String nation) {
    this.nom = nom;
    this.prenom = prenom;
    this.nation = nation;
    films = new HashSet<Film>();
  }
  
  /**
   * Le accesseur récupère le nom de l'artiste.
   *
   * @return le nom de l'artiste.
   */
  public String getNom() {
    return nom;
  }
  
  /**
   * Le setteur met à jour le nom de l'artiste.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  /**
   * Le accesseur récupère le prénom de l'artiste.
   *
   * @return le prénom de l'artiste.
   */
  public String getPrenom() {
    return prenom;
  }
  
  /**
   * Le setteur met à jour le prénom de l'artiste.
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
  
  /**
   * Le accesseur récupère la nationalité de l'artiste.
   *
   * @return la nationalité de l'artiste.
   */
  public String getNation() {
    return nation;
  }
  
  /**
   * Le setteur met à jour la nationalité de l'artiste.
   */
  public void setNation(String nation) {
    this.nation = nation;
  }
  
  /**
   * Le accesseur récupère le film de l'artiste.
   */
  public Set<Film> getFilms() {
    return this.films;
  }
  
  /**
   * Le setteur met à jour le film de l'artiste.
   */
  public void setFilms(Set<Film> films) {
    this.films = films;
  }
}
