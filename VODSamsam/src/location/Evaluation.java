package location;

// A COMPLETER

/**
 * Classe représentant la note et le commenataire attribué par un utilisateur à un film.
 * 
 *
 * @author Olabiyi ELEGBEDE
 */
public class Evaluation {
  /**
   * La note attribuée au film par un utilisateur.
   */
  private int note;
  /**
   * Le commentaire d'un utilisateur sur le film évalué.
   */
  private String commentaire;
  
  /**
   * Le film Evalué.
   */
  private Film filmEvalue;
  /**
   * Représente ici l'utilisateur qui évalue le film.
   */
  private Utilisateur utilisateur;
  
  /**
   * Renvoie la note d'un film.
   *
   * @return la note d'un film.
   */
  public int getNote() {
    return note;
  }
  
  /**
   * Modifie la note d'un film.
   *
   *@param note la nouvelle note (doit être supérieure ou égale à 0 et inférieure ou égale à 5).
   */
  
  public void setNote(int note) {
    if (note >= 0 && note <= 5) {
      this.note = note;
    } else {
      System.out.println("La note doit rspecter les standards ");
      this.note = 0;
    }
  }
  /**
   * Renvoie le commentaire sur un film.
   *
   * @return le commentaire sur un film.
   */
  
  public String getCommentaire() {
    return commentaire;
  }
  /**
   * Modifie le commentaire sur un film.
   *
   * @param commentaire le nouveau commentaire.
   */
  
  public void setCommentaire(String commentaire) {
    this.commentaire = commentaire;
  }
  
  /**
   * Crée une évaluation avec toutes ses informations.
   *
   * @param note la note d'un film.
   * @param commentaire le commentaire sur un film.
   * @param filmEvalue le film évalué.
   * @param utilisateur l'utilisateur qui évalue.
   */
  
  public Evaluation(int note, String commentaire, Film filmEvalue,
      Utilisateur utilisateur) {
    super();
    this.note = note;
    this.commentaire = commentaire;
    this.filmEvalue = filmEvalue;
    this.utilisateur = utilisateur;
  }
  
  /**
   * Crée une évaluation sans le commentaire.
   *
   * @param note la note d'un film.
   * @param filmEvalue le film évalué.
   * @param utilisateur l'utilisateur qui évalue.
   */
  
  public Evaluation(int note, Film filmEvalue,
      Utilisateur utilisateur) {
    super();
    this.note = note;
    this.filmEvalue = filmEvalue;
    this.utilisateur = utilisateur;
  }
  
  /**
   * Renvoie le Film évalué.
   *
   * @return le film évalué.
   */
  
  public Film getFilmEvalue() {
    return filmEvalue;
  }
  
  /**
   * Modifie le film évalué.
   *
   * @param filmEvalue le nouveau film.
   */
  
  public void setFilmEvalue(Film filmEvalue) {
    this.filmEvalue = filmEvalue;
  }
  
  /**
   * Renvoie l'utilisateur qui évalue.
   *
   * @return l'utilisateur qui évalue.
   */
  
  public Utilisateur getUtilisateur() {
    return utilisateur;
  }
  
  /**
   * Modifie l'utilisateur qui a évalué un film.
   *
   * @param utilisateur le nouvel utilisateur.
   */
  
  public void setUtilisateur(Utilisateur utilisateur) {
    this.utilisateur = utilisateur;
  }
  
  
  
}
