package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import location.Acteur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.Acteur Acteur}.
 *
 * @author Rayan Brossard
 * @see location.Acteur
 */

public class TestActeur {
  /**
   * Une information complète : prénom, nom et nationalité.
   */
  private Acteur infoComplete;
  
  /**
   * Instancie une information complète pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    infoComplete = new Acteur("Valjean", "Jean", "Français");
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * Vérifie qu'on renomme son nom en Colle.
   */
  @Test
  void testNomColle() {
    infoComplete.setNom("Colle");
    assertEquals(infoComplete.getNom(), "Colle");
  }
  
  /**
   * Vérifie qu'on renomme son prénom en Piou.
   */
  @Test
  void testPrenomPiou() {
    infoComplete.setPrenom("Piou");
    assertEquals(infoComplete.getPrenom(), "Piou");
  }
  
  /**
   * Vérifie qu'on renomme sa nationalité en Anglais.
   */
  @Test
  void testNationAng() {
    infoComplete.setNation("Anglais");
    assertEquals(infoComplete.getNation(), "Anglais");
  }
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    Acteur dif = new Acteur("Hollande", "François", "Français");
    assertEquals(dif.getNom(), "Hollande");
    assertEquals(dif.getPrenom(), "François");
    assertEquals(dif.getNation(), "Français");
  }
  
  /**
   * Vérifie que la méthode toString() retourne bien la représentation textuelle de l'acteur.
   */
  @Test
  void testToString() {
    // Création d'un objet Acteur
    Acteur acteur = new Acteur("Valjean", "Jean", "Français");

    // Résultat attendu
    String resultatAttendu = "Valjean Jean (Français)";

    // Vérification du résultat de la méthode toString()
    assertEquals(resultatAttendu, acteur.toString(), "La méthode toString() ne retourne pas le bon format.");
  }
}
