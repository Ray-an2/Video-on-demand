package location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Gestion des évaluations des utilisateurs.
 *
 * @author Ainhoa ESONO MANGUE
 */
public class GestionEvaluation implements Serializable {
	
  /**Collection des films.*/
  private Set<Film> listeFilms = new HashSet<>();
  
  /**
   * Retourne l'ensemble des évaluations pour un film donné.

   * @param film le film pour lequel on souhaite les évaluations
   * @return un ensemble des évaluations ou null si le film est null
   */
  public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
    if (film == null) {
      return null;
    }
    return film.getEvaluations();
  }
  
  /**
   * Retourne l'ensemble des évaluations pour un film donné par son titre.

   * @param titre le titre du film
   * @return un ensemble des évaluations ou null si le film n'existe pas
   */
  public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
    Film film = getFilm(titre);
    return ensembleEvaluationsFilm(film);
  }
  
  /**
   * Calcule la moyenne des évaluations pour un film donné.

   * @param film le film pour lequel on calcule la moyenne
   * @return la moyenne des évaluations ou une valeur spéciale si le film est
   *         null ou sans évaluations
   */
  public double evaluationMoyenne(Film film) {
    if (film == null) {
      return -2; // Film null
    }
    
    Set<Evaluation> evaluations = film.getEvaluations(); // Recupere les evaluations du film
    if (evaluations == null || evaluations.isEmpty()) {
      return 0; // Aucune évaluation
    }
    
    double somme = 0;
    for (Evaluation eval : evaluations) {
      somme += eval.getNote(); // On suppose que `Evaluation` a une méthode `getNote()`
                              
    }
    
    return somme / evaluations.size();
  }
  
  /**
   * Calcule la moyenne des évaluations pour un film donné par son titre.

   * @param titre le titre du film
   * @return la moyenne des évaluations ou une valeur spéciale si le film
   *         n'existe pas ou sans évaluations
   */
  public double evaluationMoyenne(String titre) {
    Film film = getFilm(titre);
    return evaluationMoyenne(film);
  }
  
  /**
   * Récupère un film de la liste des films par son titre.

   * @param titre le titre du film recherché
   * @return le film correspondant ou null si aucun film n'a ce titre
   */
  private Film getFilm(String titre) {
    for (Film film : listeFilms) {
      if (film.getTitre().equalsIgnoreCase(titre)) { // Recherche insensible à  la casse
                                                    
        return film;
      }
    }
    return null;
  }
}
