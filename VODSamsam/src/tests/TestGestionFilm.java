package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Set;
import location.Film;
import location.Genre;
import location.GestionFilm;
import location.LocationException;
import location.NonConnecteException;
import location.Realisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.GestionFilm GestionFilm}.
 *
 * @author Ainhoa ESONO MANGUE
 * @see location.GestionFilm
 */
public class TestGestionFilm {
  
  /**
   * Instance de GestionFilm pour les tests.
   */
  private GestionFilm gestionFilm;
  
  /**
   * Films et réalisateurs utilisés pour les tests.
   */
  private Film film1;
  private Film film2;
  private Realisateur realisateur1;
  private Realisateur realisateur2;
  
  /**
   * Initialise les données de test.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    gestionFilm = new GestionFilm();
    
    // Création des réalisateurs pour les tests
    realisateur1 = new Realisateur("Nolan", "Christopher", "Britannique");
    realisateur2 = new Realisateur("Cameron", "James", "Américain");
    
    // Création des films avec les réalisateurs
    film1 = new Film("Inception", 2010, realisateur1);
    film2 = new Film("Titanic", 1997, realisateur2);
    
    // Ajout des films à la gestion
    gestionFilm.ajoutFilm(film1);
    gestionFilm.ajoutFilm(film2);
  }
  
  /**
   * Nettoie les données après les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Vérifie l'ajout d'un film.
   */
  @Test
  void testAjouterFilm() {
    Film nouveauFilm = new Film("Interstellar", 2014, realisateur1);
    gestionFilm.ajoutFilm(nouveauFilm);
    assertNotNull(gestionFilm.getFilm("Interstellar"));
  }
  
  /**
   * Vérifie la suppression d'un film.
   */
  @Test
  void testSupprimerFilm() {
    boolean resultat = gestionFilm.supprimerFilm(film1);
    assertTrue(resultat);
    assertNull(gestionFilm.getFilm("Inception"));
  }
  
  /**
   * Vérifie la location d'un film.
   */
  @Test
  void testLouerFilm() throws NonConnecteException, LocationException {
    gestionFilm.louerFilm(film1);
    assertFalse(film1.getdisponible());
  }
  
  /**
   * Vérifie qu'on ne peut pas louer un film déjà loué.
   */
  @Test
  void testLouerFilmIndisponible() {
    film1.setdisponible(false);
    assertThrows(LocationException.class, () -> gestionFilm.louerFilm(film1));
  }
  
  /**
   * Vérifie la fin de location d'un film.
   */
  @Test
  void testFinLocationFilm() throws NonConnecteException, LocationException {
    gestionFilm.louerFilm(film1);
    gestionFilm.finLocationFilm(film1);
    assertTrue(film1.getdisponible());
  }
  
  /**
   * Vérifie la recherche de films par genre.
   */
  @Test
  void testEnsembleFilmsGenre() {
    film1.addGenre(Genre.Action);
    film2.addGenre(Genre.Drame);
    
    Set<Film> filmsAction = gestionFilm.ensembleFilmsGenre(Genre.Action);
    assertEquals(1, filmsAction.size());
    assertTrue(filmsAction.contains(film1));
  }
  
  /**
   * Vérifie la recherche de films par genre (String).
   */
  @Test
  void testEnsembleFilmsGenreString() {
    film1.addGenre(Genre.Action);
    film2.addGenre(Genre.Drame);
    
    Set<Film> filmsAction = gestionFilm.ensembleFilmsGenre("ACTION");
    assertNotNull(filmsAction);
    assertEquals(1, filmsAction.size());
  }
  
  /**
   * Vérifie qu'un genre invalide retourne null.
   */
  @Test
  void testEnsembleFilmsGenreInvalide() {
    Set<Film> filmsInvalides =
        gestionFilm.ensembleFilmsGenre("GENRE_INEXISTANT");
    assertNull(filmsInvalides);
  }
  
  /**
   * Vérifie l'ajout d'une affiche.
   */
  @Test
  void testAjouterAffiche() throws IOException {
    boolean resultat =
        gestionFilm.ajouterAffiche(film1, "chemin/vers/affiche.jpg");
    assertTrue(resultat);
  }
  
  /**
   * Vérifie si un film est louable.
   */
  @Test
  void testEstLouable() throws NonConnecteException {
    assertTrue(gestionFilm.estLouable(film1));
    film1.setdisponible(false);
    assertFalse(gestionFilm.estLouable(film1));
  }
  
  /**
   * Vérifie l'ensemble des films en location.
   */
  @Test
  void testFilmsEnLocation() throws NonConnecteException, LocationException {
    gestionFilm.louerFilm(film1);
    Set<Film> filmsLoues = gestionFilm.filmsEnLocation();
    assertEquals(1, filmsLoues.size());
    assertTrue(filmsLoues.contains(film1));
  }
  
  /**
   * Vérifie qu'on ne peut pas louer plus de 3 films.
   */
  @Test
  void testLimiteLocationFilms()
      throws NonConnecteException, LocationException {
    Film film3 = new Film("Avatar", 2009, realisateur2);
    Film film4 = new Film("Dunkerque", 2017, realisateur1);
    gestionFilm.ajoutFilm(film3);
    gestionFilm.ajoutFilm(film4);
    
    gestionFilm.louerFilm(film1);
    gestionFilm.louerFilm(film2);
    gestionFilm.louerFilm(film3);
    
    assertThrows(LocationException.class, () -> gestionFilm.louerFilm(film4));
  }
}
