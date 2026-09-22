package location;

import io.Sauvegarde;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Classe de gestion des artistes.
 * 
 *
 * @author Olabiyi ELEGBEDE
 */
public class GestionArtiste implements Serializable {
  
  /** Collection des artistes gérés par le système. */
  private Set<Artiste> listeArtistes = new HashSet<>();
  
  /** Rôle de l'artiste (Acteur ou Réalisateur). */
  private String role;
  
  /** Instance d'acteur temporaire pour la création. */
  private Acteur acteur;
  
  /** Instance de réalisateur temporaire pour la création. */
  private Realisateur real;
  
  /** Gestionnaire de sauvegarde des données. */
  private Sauvegarde sauvegarde;
  
  
  /**
   * Constructeur par défaut. Initialise la gestion des artistes et charge les
   * données existantes depuis le fichier de sauvegarde.
   */
  public GestionArtiste() {
    sauvegarde = new Sauvegarde();
    try {
      sauvegarde.chargerDonnees("src/monFichier.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sauvegarde.getArtistes() != null) {
      listeArtistes = sauvegarde.getArtistes();
    }
  }
  
  
  /**
   * Retourne la collection complète des artistes.
   *
   * @return l'ensemble des artistes gérés par le système
   */
  public Set<Artiste> getListeArtistes() {
    return listeArtistes;
  }
  
  /**
   * Définit une nouvelle collection d'artistes.
   *
   * @param listeArtistes la nouvelle collection d'artistes à utiliser
   */
  public void setListeArtistes(Set<Artiste> listeArtistes) {
    this.listeArtistes = listeArtistes;
  }
  
  
  /**
   * Définit le rôle pour la création des prochains artistes.
   *
   * @param role le rôle à définir ("Acteur" ou "Realisateur")
   */
  public void setRole(String role) {
    this.role = role;
  }
  
  /**
   * Recherche un artiste par son nom et prénom.
   *
   * @param nom le nom de l'artiste recherché
   * @param prenom le prénom de l'artiste recherché
   * @return l'artiste trouvé ou null si aucun artiste ne correspond
   */
  public Artiste getArtiste(String nom, String prenom) {
    if (listeArtistes != null) {
      Iterator<Artiste> it = listeArtistes.iterator();
      while (it.hasNext()) {
        Artiste artiste = it.next();
        if (artiste.getNom().equalsIgnoreCase(nom)
            && artiste.getPrenom().equalsIgnoreCase(prenom)) {
          return artiste;
        }
      }
    }
    return null;
  }
  
  /**
   * Ajoute un nouvel artiste à la collection si celui-ci n'existe pas déjà.
   *
   * @param artiste l'artiste à ajouter
   */
  public void ajoutArtiste(Artiste artiste) {
    if (artiste != null && !listeArtistes.contains(artiste)) {
      listeArtistes.add(artiste);
    }
  }
  
  
  /**
   * Supprime un artiste de la collection.
   *
   * @param artiste l'artiste à supprimer
   * @return true si l'artiste a été supprimé, false sinon
   */
  public boolean supprimerArtiste(Artiste artiste) {
    Artiste artisteAsupprimer =
        getArtiste(artiste.getNom(), artiste.getPrenom());
    if (listeArtistes.contains(artisteAsupprimer)) {
      GestionFilm gestionFilm = new GestionFilm();
      
      for (Film film : gestionFilm.ensembleFilms()) {
        if (film.getRealisateur() != null
            && film.getRealisateur().equals(artisteAsupprimer)) {
          film.setRealisateur(null);
        }
        
        
        if (film.getActeurs() != null) {
          film.getActeurs()
              .removeIf(acteur -> acteur.equals(artisteAsupprimer));
        }
      }
      
      
      return listeArtistes.remove(artisteAsupprimer);
    }
    return false;
  }
  
  
  /**
   * Crée un nouvel artiste avec les informations fournies.
   *
   * @param nom le nom du nouvel artiste
   * @param prenom le prénom du nouvel artiste
   * @param nationalite la nationalité du nouvel artiste
   * @return le nouvel artiste créé, ou null si la création a échoué
   */
  public Artiste creerArtiste(String nom, String prenom, String nationalite) {
    if (getArtiste(nom, prenom) == null) {
      if (nom.trim().isEmpty()) {
        return null;
      } else {
        if (this.role.equals("Acteur")) {
          acteur = new Acteur(nom, prenom, nationalite);
          listeArtistes.add(acteur);
          return acteur;
        } else {
          real = new Realisateur(nom, prenom, nationalite);
          listeArtistes.add(real);
          return real;
        }
      }
    }
    return null;
  }
  
  /**
   * Retourne l'ensemble des acteurs présents dans la collection.
   *
   * @return un ensemble contenant uniquement les artistes de type Acteur
   */
  public Set<Artiste> ensembleActeurs() {
    Set<Artiste> acteurs = new HashSet<Artiste>();
    Iterator<Artiste> it = listeArtistes.iterator();
    while (it.hasNext()) {
      Artiste artiste = it.next();
      if (artiste instanceof Acteur) {
        acteurs.add((Acteur) artiste);
      }
    }
    return acteurs;
  }
  
  /**
   * Retourne l'ensemble des réalisateurs présents dans la collection.
   *
   * @return un ensemble contenant uniquement les artistes de type Realisateur
   */
  public Set<Artiste> ensembleRealisateurs() {
    Set<Artiste> real = new HashSet<Artiste>();
    Iterator<Artiste> it = listeArtistes.iterator();
    while (it.hasNext()) {
      Artiste artiste = it.next();
      if (artiste instanceof Realisateur) {
        real.add((Realisateur) artiste);
      }
    }
    return real;
  }
  
  /**
   * Retourne l'ensemble des films réalisés par un réalisateur spécifique.
   *
   * @param realisateur le réalisateur dont on veut obtenir les films
   * @return l'ensemble des films du réalisateur
   */
  public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
    return getArtiste(realisateur.getNom(), realisateur.getPrenom()).getFilms();
  }
  
  /**
   * Retourne l'ensemble des films associés à un réalisateur spécifié par son
   * nom et prénom. Cette méthode permet de récupérer tous les films réalisés
   * par un réalisateur, en utilisant son nom et prénom pour l'identifier.
   *
   * @param nom le nom du réalisateur
   *
   * @param prenom le prénom du réalisateur
   *
   * @return un ensemble de films réalisés par le réalisateur, ou {@code null}
   *         si le réalisateur est introuvable
   */
  public Set<Film> ensembleFilmsRealisateur(String nom, String prenom) {
    Artiste realisateur = getArtiste(nom, prenom);
    return ensembleFilmsRealisateur(realisateur);
  }
  
  /**
   * Retourne l'ensemble des films dans lesquels un acteur a joué.
   *
   * @param acteur l'acteur dont on veut obtenir les films
   * @return l'ensemble des films de l'acteur
   */
  public Set<Film> ensembleFilmsActeur(Artiste acteur) {
    return getArtiste(acteur.getNom(), acteur.getPrenom()).getFilms();
  }
  
  
  /**
   * Retourne l'ensemble des films associés à un acteur spécifié par son nom et
   * prénom. Cette méthode permet de récupérer tous les films dans lesquels un
   * acteur a joué, en utilisant son nom et prénom pour identifier l'acteur.
   *
   * @param nom le nom de l'acteur
   *
   * @param prenom le prénom de l'acteur
   *
   * @return un ensemble de films auxquels l'acteur est associé, ou {@code null}
   *         si l'acteur est introuvable
   */
  public Set<Film> ensembleFilmsActeur(String nom, String prenom) {
    Artiste acteur = getArtiste(nom, prenom);
    return ensembleFilmsActeur(acteur);
  }
  
  
  
  /**
   * Recherche un réalisateur par son nom et prénom. Cette méthode permet de
   * récupérer un réalisateur dans la liste des artistes en fonction de son nom
   * et prénom. Si le réalisateur n'existe pas, la méthode retourne
   * {@code null}.
   *
   * @param nom le nom du réalisateur
   *
   * @param prenom le prénom du réalisateur
   *
   * @return l'objet {@link Artiste} représentant le réalisateur, ou
   *         {@code null} si aucun réalisateur n'a été trouvé avec les
   *         informations fournies
   */
  public Artiste getRealisateur(String nom, String prenom) {
    if (nom == null || prenom == null) {
      return null;
    }
    
    for (Artiste realisateur : listeArtistes) {
      if (realisateur.getNom().equals(nom)
          && realisateur.getPrenom().equals(prenom)) {
        return realisateur;
      }
    }
    
    return null;
  }
  
  /**
   * Recherche un acteur par son nom et prénom. Cette méthode permet de
   * récupérer un acteur dans la liste des artistes en fonction de son nom et
   * prénom. Si l'acteur n'existe pas, la méthode retourne {@code null}.
   *
   * @param nom le nom de l'acteur
   *
   * @param prenom le prénom de l'acteur
   *
   * @return l'objet {@link Artiste} représentant l'acteur, ou {@code null} si
   *         aucun acteur n'a été trouvé avec les informations fournies
   */
  public Artiste getActeur(String nom, String prenom) {
    if (nom == null || prenom == null) {
      return null;
    }
    
    for (Artiste acteur : listeArtistes) {
      if (acteur.getNom().equals(nom) && acteur.getPrenom().equals(prenom)) {
        return acteur;
      }
    }
    
    return null;
  }
  
}
