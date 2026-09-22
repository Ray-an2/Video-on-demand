package location;

import java.io.Serializable;

/**
 * Gestion des acteurs, de la classe Acteur qui hérite de la class Artiste.
 *
 * @author Brossard Rayan
 */
public class Acteur extends Artiste implements Serializable {
  
  //************************** ATTRIBUTS
  /**
   * Une chaîne de caractère qui représente la liste de tout les acteurs.
   */
  protected String list; 
  
  
  //************************** CONSTRUCTEUR
  
  /**
   * Constructeur : initialise les informations de l'acteur.
   *
   * @param nom le nom de l'artiste
   * @param prenom le prénom de l'artiste
   * @param nation la nationalité de l'artiste
   */
  public Acteur(String nom, String prenom, String nation) {
    super(nom, prenom, nation);
  }
  
  //************************** METHODES
  
  /**
   * Représentation textuelle des informations personnelles de l'acteur, avec le nom,
   * prénom, l'âge et la nationalité de l'acteur. 
   *
   * @return Représentation des informations personnelles de l'acteur.
   */
  @Override
  public String toString() {
	  return super.nom + " " + super.prenom + " (" + super.nation + ")";
  }
}
