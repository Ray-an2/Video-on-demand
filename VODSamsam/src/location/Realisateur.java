package location;

import java.io.Serializable;

/**
 * Gestion des réalisateurs, de la classe Realisateur qui hérite de la class Artiste.
 *
 * @author Brossard Rayan
 */
public class Realisateur extends Artiste implements Serializable {
  
  //************************** ATTRIBUTS
  /**
   * Une chaîne de caractère qui représente la liste de tout les réalisateurs.
   */
  protected String list;
  
  //************************** CONSTRUCTEUR

  /**
   * Constructeur : initialise les informations du réalisateur.
   *
   * @param nom le nom du réalisateur
   * @param prenom le prénom du réalisateur
   * @param nation la nationalité du réalisateur
   */
  public Realisateur(String nom, String prenom, String nation) {
    super(nom, prenom, nation);
  }
  
  //************************** METHODES
  
  /**
   * Représentation textuelle des informations personnelles du réalisateur, avec le nom,
   * prénom, l'âge et la nationalité du réalisateur. 
   *
   * @return Représentation des informations personnelles du réalisateur.
   */
  @Override
  public String toString() {
    return super.nom + " " + super.prenom + " (" + super.nation + ")";
  }
}
