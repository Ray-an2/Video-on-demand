package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import location.Evaluation;
import location.Film;
import location.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link location.Evaluation
 * Evaluation}.
 *
 * @author Olabiyi ELEGBEDE
 * @see location.Evaluation
 */

public class TestEvaluation {
  /**
   * Une évaluation complète : La note, le commentaire, le film évalué  et l'utilisateur qui évalue.
   */
  private Evaluation evaluation;
  
  
  /**
   * Une évaluation sans commentaire : La note, le film évalué  et l'utilisateur qui évalue.
   */
  private Evaluation evaluationBasique;
  
  /**
   * Un utilisateur.
   */
  private Utilisateur james;
  private Utilisateur rayane;
  
  /**
   * Une évaluation complète : La note, le commentaire, le film évalué  et l'utilisateur qui évalue.
   */
  private Film titanic;
  
  
  /**
   * Instancie une évaluation  pour les tests.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    evaluationBasique =
        new Evaluation(3, titanic, rayane);
    evaluation =
        new Evaluation(3, "Bon film", titanic, james);
  }
  
  /**
   * Ne fait rien après les tests : à modifier au besoin.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  

  /**
   * Vérifie que l'on peut positionner une de 4 .
   */
  @Test
  void testNote() {
    evaluation.setNote(4);
    assertEquals(evaluation.getNote(), 4);
  }
  
  /**
   * Vérifie qu'on ne peut pas positionner un âge négatif sur une information
   * basique.
   */
  @Test
  void testNoteNegative() {
    evaluation.setNote(-4);
    assertTrue(evaluation.getNote() != -4);
  }
  
  @Test
  void testNoteSuperieurA5() {
    evaluation.setNote(6);
    assertEquals(evaluation.getNote(), 6);
  }
  
  @Test
  void testNoteBasiqueNegative() {
    evaluationBasique.setNote(-4);
    assertTrue(evaluationBasique.getNote() != -4);
  }
  
  @Test
  void testNoteBasiqueSuperieurA5() {
    evaluationBasique.setNote(6);
    assertEquals(evaluationBasique.getNote(), 6);
  }
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    Evaluation eval =
        new Evaluation(4, "Excellent", titanic, rayane);
    assertEquals(eval.getNote(), 4);
    assertEquals(eval.getCommentaire(), "Excellent");
 
  }
}
