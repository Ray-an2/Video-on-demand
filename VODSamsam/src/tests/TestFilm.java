package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import location.Acteur;
import location.Film;
import location.Genre;
import location.Realisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.Film Film}.
 *
 * @author Ainhoa ESONO MANGUE
 * @see location.Film
 */
class TestFilm {
  
  /**
   * Un Film.
   */
  private Film film;
  
  /**
   * Instancie unun réalisateur et le Film pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    Realisateur realisateur = new Realisateur("Roger", " Allers", "Americain");
    film = new Film("Le Roi Lion", 1994, Genre.Animation, "lion.jpg", realisateur);
  }
  
  /**
   * Libère les ressources après chaque test.
   */
  @AfterEach
  void tearDown() {
    film = null;
  }
  
  /**
   * Vérifie que l'on peut positionner un titre correctement.
   */
  @Test
  void testTitre() {
    film.setTitre("Le Roi Lion");
    assertEquals("Le Roi Lion", film.getTitre(),
        "Le titre ne doit pas être modifié.");
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner une année négative.
   */
  @Test
  void testAnneeNegatif() {
    film.setAnnee(-1994);
    assertTrue(film.getAnnee() >= 0, "L'année ne peut pas être négative.");
  }
  
  /**
   * Vérifie que l'on peut positionner un genre correctement.
   */
  @Test
  void testGenre() {
    film.addGenre(Genre.Animation);
    assertEquals(Genre.Animation, film.getGenre(),
        "Le genre ne doit pas être modifié.");
  }
  
  /**
   * Vérifie que l'on peut positionner un réalisateur correctement.
   */
  @Test
  void testRealisateur() {
    Realisateur nouveauRealisateur =
        new Realisateur("Roger", "Aller", "Americain");
    film.setRealisateur(nouveauRealisateur);
    assertEquals(nouveauRealisateur, film.getRealisateur(),
        "Le réalisateur ne doit pas être modifié.");
  }
  
  /**
   * Vérifie l'ajout d'acteurs au film.
   */
  
  @Test
  void testAjoutActeur() {
    Acteur acteur = new Acteur("Thomas", "jonathan", "Americain");
    film.setActeurs(acteur);
    assertFalse(film.getActeurs().isEmpty(),
        "La liste des acteurs ne doit pas être vide.");
    assertTrue(film.getActeurs().contains(acteur),
        "L'acteur doit être présent dans la liste.");
  }
  
  /**
   * Vérifie qu'on ne peut pas ajouter un acteur null au film.
   */
  
  @Test
  void testAjoutActeurNull() {
    Acteur acteur = null;
    film.setActeurs(acteur);
    assertTrue(film.getActeurs().isEmpty(),
        "La liste des acteurs ne doit pas contenir de null.");
  }
  
  /**
   * Vérifie que la liste des acteurs est vide au début.
   */
  
  @Test
  void testActeursVidesInitialement() {
    assertTrue(film.getActeurs().isEmpty(),
        "La liste des acteurs doit être vide au début.");
  }
  
  @Test
  void testEvaluationsVidesInitialement() {
    assertTrue(film.getEvaluations().isEmpty(),
        "La liste des évaluations doit être vide à l'initialisation.");
  }
  
  /**
   * Vérifie que le film est disponible à l'initialisation.
   */
  
  @Test
  void testDisponibiliteInitiale() {
    assertTrue(film.getdisponible(),
        "Le film doit être disponible à l'initialisation.");
  }
}
