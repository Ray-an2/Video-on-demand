package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import location.Acteur;
import location.Artiste;
import location.GestionArtiste;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.GestionArtiste GestionArtiste}.
 *
 * @author Olabiyi ELEGBEDE
 * @see location.GestionArtiste
 */
public class TestGestionArtiste {
  
  /**
   * Instance de GestionArtiste pour les tests.
   */
  private GestionArtiste gestionArtiste;
  
  /**
   * Artistes utilisés pour les tests.
   */
  private Artiste acteur1;
  private Artiste acteur2;
  private Artiste realisateur1;
  
  /**
   * Initialise les données de test.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    gestionArtiste = new GestionArtiste();
    gestionArtiste.setRole("Acteur");
    acteur1 = gestionArtiste.creerArtiste("Depp", "Johnny", "Américain");
    acteur2 = gestionArtiste.creerArtiste("DiCaprio", "Leonardo", "Américain");
    
    gestionArtiste.setRole("Realisateur");
    realisateur1 =
        gestionArtiste.creerArtiste("Nolan", "Christopher", "Britannique");
  }
  
  /**
   * Nettoie les données après les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Vérifie la création d'un artiste.
   */
  @Test
  void testCreerArtiste() {
    gestionArtiste.setRole("Acteur");
    Artiste nouvelActeur =
        gestionArtiste.creerArtiste("Pitt", "Brad", "Américain");
    assertNotNull(nouvelActeur);
    assertTrue(nouvelActeur instanceof Acteur);
    assertEquals("Pitt", nouvelActeur.getNom());
  }
  
  /**
   * Vérifie qu'on ne peut pas créer un artiste avec un nom vide.
   */
  @Test
  void testCreerArtisteNomVide() {
    Artiste artisteNomVide =
        gestionArtiste.creerArtiste("", "Prénom", "Nationalité");
    assertNull(artisteNomVide);
  }
  
  /**
   * Vérifie la recherche d'un artiste.
   */
  @Test
  void testGetArtiste() {
    Artiste artiste = gestionArtiste.getArtiste("Depp", "Johnny");
    assertNotNull(artiste);
    assertEquals("Depp", artiste.getNom());
    assertEquals("Johnny", artiste.getPrenom());
  }
  
  /**
   * Vérifie la suppression d'un artiste.
   */
  @Test
  void testSupprimerArtiste() {
    boolean resultat = gestionArtiste.supprimerArtiste(acteur1);
    assertTrue(resultat);
    assertNull(gestionArtiste.getArtiste("Depp", "Johnny"));
  }
  
  /**
   * Vérifie l'obtention de l'ensemble des acteurs.
   */
  @Test
  void testEnsembleActeurs() {
    Set<Artiste> acteurs = gestionArtiste.ensembleActeurs();
    assertEquals(2, acteurs.size());
    assertTrue(acteurs.contains(acteur1));
    assertTrue(acteurs.contains(acteur2));
  }
  
  /**
   * Vérifie l'obtention de l'ensemble des réalisateurs.
   */
  @Test
  void testEnsembleRealisateurs() {
    Set<Artiste> realisateurs = gestionArtiste.ensembleRealisateurs();
    assertEquals(1, realisateurs.size());
    assertTrue(realisateurs.contains(realisateur1));
  }
}
