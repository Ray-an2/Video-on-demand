package location;

import io.Sauvegarde;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * Classe de gestion des films.
 * 
 *
 * @author Olabiyi ELEGBEDE
 */
public class GestionFilm implements Serializable {
  
  /**
   * Liste de tous les films de l'application.
   */
  private Set<Film> listeFilms = new HashSet<>();
  /**
   * Un film en particulier.
   */
  private Film film;
  /**
   * La sauvegarde appelée pour récupérer les différents composants du fchier de
   * sauvegarde.
   */
  private Sauvegarde sauvegarde;
  /**
   * L'utilisateur connecté.
   */
  private Utilisateur utilisateurConnecte;
  /**
   * Liste des films loués par un utilisateur.
   */
  private Set<Film> filmsLoues = new HashSet<>();
  
  /**
   * Constructeur : initialise le gestionnaire de film.
   * 
   */
  public GestionFilm() {
    sauvegarde = new Sauvegarde();
    try {
      sauvegarde.chargerDonnees("src/monFichier.txt");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (sauvegarde.getFilms() != null) {
      listeFilms = sauvegarde.getFilms();
    }
    
  }
  
  /**
   * Ajoute un nouveau film à la liste des films de l'application.
   *
   * @param film le film qui est ajouté
   * 
   */
  public void ajoutFilm(Film film) {
    if (film != null) {
      listeFilms.add(film);
    }
  }
  
  
  /**
   * Recherche un film dans la liste des films.
   *
   * @param titre le titre du film
   * 
   * @return le film correspondant au titre, ou null s'il n'est pas trouvé
   */
  
  public Film getFilm(String titre) {
    Iterator<Film> it = listeFilms.iterator();
    while (it.hasNext()) {
      Film film = it.next();
      if (film.getTitre().equalsIgnoreCase(titre)) {
        return film;
      }
    }
    return null;
  }
  
  
  /**
   * Permet de créer un nouveau film.
   *
   * @param titre le titre du film
   * @param realisateur le réalisateur du film
   * @param annee l'année du film
   * @param ageLimite l'age limite des spectateurs du film
   * @return le film si il est bien créé et null sinon
   */
  public Film creerFilm(String titre, Artiste realisateur, int annee,
      int ageLimite) {
    
    
    if (titre.trim().isEmpty()) {
      return null;
    }
    
    if (getFilm(titre) == null && realisateur != null) {
      
      this.film = new Film(titre, annee, (Realisateur) realisateur);
      
      listeFilms.add(film);
      
      if (realisateur.getFilms() == null) {
        Set<Film> newFilms = new HashSet<Film>();
        newFilms.add(film);
        realisateur.setFilms(newFilms);
      }
      realisateur.getFilms().add(this.film);
      return film;
    }
    
    
    return null;
  }
  
  /**
   * Ajoute des acteurs à un film, en vérifiant qu'ils ne sont pas déjà associés
   * à ce dernier. Cette méthode permet d'ajouter un ou plusieurs acteurs à un
   * film. Si un acteur n'est pas déjà associé au film, il est ajouté à la liste
   * des acteurs du film, et le film est également ajouté à la liste des films
   * de l'acteur.
   *
   * @param film le film auquel les acteurs doivent être ajoutés
   *
   * @param acteurs un ou plusieurs acteurs à ajouter au film
   *
   * @return {@code true} si au moins un acteur a été ajouté, {@code false}
   *         sinon
   *
   * @throws NullPointerException si le film ou un des acteurs est nul
   *
   * @throws IllegalStateException si la méthode `getFilm(film.getTitre())`
   *         retourne {@code null}
   */
  public boolean ajouterActeurs(Film film, Artiste... acteurs) {
    GestionArtiste gestionArtiste = new GestionArtiste();
    int acteursAjoutes = 0;
    
    if (film == null) {
      return false; // Si le film est null, on ne peut rien faire
    }
    
    // Initialisation de la liste des acteurs si elle est null
    if (film.getActeurs() == null) {
      film.setActeurs(new HashSet<>());
    }
    
    for (Artiste acteur : acteurs) {
      // Vérifie si l'acteur existe déjà dans la liste du film
      Artiste artisteTrouve =
          gestionArtiste.getArtiste(acteur.getNom(), acteur.getPrenom());
      if (artisteTrouve == null || !film.getActeurs().contains(artisteTrouve)) {
        // Ajoute l'acteur au film
        film.getActeurs().add((Acteur) artisteTrouve);
        
        // Associe le film à l'acteur
        if (acteur.getFilms() == null) {
          acteur.setFilms(new HashSet<>());
        }
        acteur.getFilms().add(film);
        
        acteursAjoutes++;
      }
    }
    
    // Retourne true si au moins un acteur a été ajouté
    return acteursAjoutes > 0;
  }
  
  
  /**
   * Ajoute des genres à un film, en évitant les doublons. Cette méthode permet
   * d'associer un ou plusieurs genres à un film. Si un genre n'est pas déjà
   * associé au film, il est ajouté à la liste des genres du film.
   *
   * @param film le film auquel les genres doivent être ajoutés
   *
   * @param genres un ou plusieurs genres à ajouter au film
   *
   * @return {@code true} si au moins un genre a été ajouté, {@code false} sinon
   *
   * @throws NullPointerException si le film est nul ou si un des genres est nul
   *
   * @throws IllegalStateException si la méthode `getFilm(film.getTitre())`
   *         retourne {@code null}
   */
  public boolean ajouterGenres(Film film, Genre... genres) {
    if (film == null) {
      return false; // Pas de film, pas de genres à ajouter
    }
    
    // Initialisation de la collection des genres si elle est nulle
    if (film.getGenre() == null) {
      film.setGenre(new HashSet<>());
    }
    
    int genresAjoutes = 0;
    
    for (Genre genre : genres) {
      
      if (film.getGenre().add(genre)) {
        genresAjoutes++;
      }
    }
    
    
    return genresAjoutes > 0;
  }
  
  
  /**
   * Supprime un film de la liste des films et met à jour les artistes associés.
   * Cette méthode supprime un film de la liste principale des films. Elle
   * s'assure également que le film est retiré de la liste des films associés à
   * chaque artiste. Si le film n'existe pas dans la liste des films, aucune
   * modification n'est effectuée.
   *
   * @param film le film à supprimer
   *
   * @return {@code true} si le film a été supprimé avec succès, {@code false}
   *         si le film est inexistant dans la liste ou si le paramètre est nul
   *         
   * @throws NullPointerException si la liste des artistes ou la liste des films
   *         est nulle
   */
  
  
  
  public boolean supprimerFilm(Film film) {
    GestionArtiste gestionArtiste = new GestionArtiste();
    Film filmAsupp = getFilm(film.getTitre());
    
    if (listeFilms.contains(filmAsupp)) {
      Iterator<Artiste> iterator = gestionArtiste.getListeArtistes().iterator();
      while (iterator.hasNext()) {
        Artiste artiste = iterator.next();
        // Chercher le film à supprimer dans la liste des films de l'artiste
        Film filmArtiste = null;
        for (Film f : artiste.getFilms()) {
          if (f.getTitre().equals(film.getTitre())) {
            filmArtiste = f;
            System.out.println("hum");
            break;
          }
        }
        
        
        if (filmArtiste != null) {
          artiste.getFilms().remove(filmArtiste);
          System.out.println(artiste.getFilms());
          System.out.println("-----");
        }
      }
      return listeFilms.remove(filmAsupp);
    }
    return false;
  }
  
  /**
   * Retourne l'ensemble des films présents dans la vidéothèque.
   *
   * @return un Set contenant tous les films de la vidéothèque
   */
  public Set<Film> ensembleFilms() {
    // TODO Auto-generated method stub
    return listeFilms;
  }
  
  /**
   * Marque un film comme disponible pour la location.
   *
   * @param film le film à rendre disponible
   * @return true si le statut du film a été changé en disponible, false si le
   *         film était déjà disponible
   */
  public boolean ouvrirLocation(Film film) {
    if (!film.getdisponible()) {
      film.setdisponible(true);
      return true;
    }
    
    return false;
  }
  
  /**
   * Marque un film comme non disponible pour la location.
   *
   * @param film le film à rendre indisponible
   * @return true si le statut du film a été changé en indisponible, false si le
   *         film était déjà indisponible
   */
  
  public boolean fermerLocation(Film film) {
    if (film.getdisponible()) {
      film.setdisponible(false);
      return true;
    }
    
    return false;
  }
  
  /**
   * Ajoute ou met à jour l'affiche d'un film.
   *
   * @param film le film auquel ajouter l'affiche
   * @param file le chemin du fichier de l'affiche
   * @return true si l'affiche a été ajoutée avec succès, false si le film
   *         n'existe pas
   * @throws IOException si une erreur survient lors de la manipulation du
   *         fichier
   */
  
  public boolean ajouterAffiche(Film film, String file) throws IOException {
    if (film == null || file == null || file.isEmpty()) {
      return false;
    } else {
      film.setAffiche(file);
      return true;
    }
    
  }
  
  /**
   * Retourne l'ensemble des films d'un genre spécifique.
   *
   * @param genre le genre des films à rechercher
   * @return un Set contenant les films du genre spécifié, null si le genre est
   *         null
   */
  public Set<Film> ensembleFilmsGenre(Genre genre) {
    if (genre == null) {
      return null;
    }
    
    Set<Film> result = new HashSet<>();
    for (Film f : listeFilms) {
      if (f.getGenre() != null && f.getGenre().contains(genre)) {
        result.add(f);
      }
    }
    return result;
  }
  
  
  /**
   * Retourne l'ensemble des films d'un genre spécifié par son nom.
   *
   * @param genre le nom du genre des films à rechercher
   * @return un Set contenant les films du genre spécifié, null si le genre est
   *         null, vide ou invalide
   */
  
  public Set<Film> ensembleFilmsGenre(String genre) {
    if (genre == null || genre.isEmpty()) {
      return null;
    }
    
    try {
      
      Genre genreEnum = Genre.valueOf(genre.toUpperCase());
      return ensembleFilmsGenre(genreEnum);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
  
  
  /**
   * Enregistre la location d'un film par l'utilisateur connecté. Cette méthode
   * vérifie si l'utilisateur est connecté, si le film est disponible, et si
   * l'utilisateur n'a pas déjà atteint sa limite de locations.
   *
   * @param film le film à louer
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException si le film est null, indisponible, ou si
   *         l'utilisateur a atteint sa limite de locations
   */
  public void louerFilm(Film film)
      throws NonConnecteException, LocationException {
    // Verifie si l'utilisateur est connecte
    if (utilisateurConnecte == null) {
      throw new NonConnecteException("Aucun utilisateur n'est connecté.");
    }
    
    // Verifie si le film est valide et disponible
    if (film == null || !film.getdisponible()) {
      throw new LocationException();
    }
    
    // Verifie si l'utilisateur a atteint la limite de films loués
    if (utilisateurConnecte.filmsEnLocation().size() >= 3) {
      throw new LocationException();
    }
    
    
    // Ajoute le film à la liste des films loués de l'utilisateur et met à jour
    // la disponibilité
    if (film != null) {
      this.filmsLoues.add(film);
      film.setdisponible(false);
      
    }
    
    
    
  }
  
  /**
   * Enregistre la fin de location d'un film par l'utilisateur connecté.
   *
   * @param film le film dont la location se termine
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   * @throws LocationException si le film n'est pas actuellement loué par
   *         l'utilisateur
   */
  public void finLocationFilm(Film film)
      throws NonConnecteException, LocationException {
    
    if (!utilisateurConnecte.filmsEnLocation().contains(film)) {
      throw new LocationException();
    }
    filmsLoues.remove(film);
    film.setdisponible(true);
  }
  
  /**
   * Vérifie si un film peut être loué.
   *
   * @param film le film à vérifier
   * @return true si le film existe et est disponible, false sinon
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  public boolean estLouable(Film film) throws NonConnecteException {
    
    return film != null && film.getdisponible();
  }
  
  /**
   * Retourne l'ensemble des films actuellement loués par l'utilisateur
   * connecté.
   *
   * @return un Set contenant les films en location
   * @throws NonConnecteException si aucun utilisateur n'est connecté
   */
  public Set<Film> filmsEnLocation() throws NonConnecteException {
    
    return utilisateurConnecte.filmsEnLocation();
  }
  
  
  
}
