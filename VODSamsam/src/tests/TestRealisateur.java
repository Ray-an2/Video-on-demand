package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import location.Realisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.Realisateur Realisateur}.
 *
 * @author Rayan Brossard
 * @see location.Realisateur
 */

public class TestRealisateur {
  /**
   * Une information complète : prénom, nom et nationalité.
   */
  private Realisateur infoComplete;
  
  /**
   * Instancie une information complète pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoComplete = new Realisateur("Noire", "Henry", "Français");
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {
    
  }
  
  /**
   * Vérifie qu'on renomme son nom en Dameure.
   */
  @Test
  void testNomDameure() {
    infoComplete.setNom("Dameure");
    assertEquals(infoComplete.getNom(), "Dameure");
  }
  
  /**
   * Vérifie qu'on renomme son prénom en Jean.
   */
  @Test
  void testPrenomJean() {
    infoComplete.setPrenom("Jean");
    assertEquals(infoComplete.getPrenom(), "Jean");
  }
  
  /**
   * Vérifie qu'on renomme sa nationalité en Espagnole.
   */
  @Test
  void testNationEsp() {
    infoComplete.setNation("Espagnole");
    assertEquals(infoComplete.getNation(), "Espagnole");
  }
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    Realisateur dif = new Realisateur("Hollande", "François", "Français");
    assertEquals(dif.getNom(), "Hollande");
    assertEquals(dif.getPrenom(), "François");
    assertEquals(dif.getNation(), "Français");
  }
  
  /**
   * Vérifie que la méthode toString() retourne bien la représentation textuelle du réalisateur.
   */
  @Test
  void testToString() {
      // Création d'un objet Realisateur
      Realisateur realisateur = new Realisateur("Noire", "Henry", "Français");

      // Résultat attendu
      String resultatAttendu = "Noire Henry (Français)";

      // Vérification du résultat de la méthode toString()
      assertEquals(resultatAttendu, realisateur.toString(), "La méthode toString() ne retourne pas le bon format.");
  }
}
