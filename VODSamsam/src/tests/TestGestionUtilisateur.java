package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import location.GestionUtilisateur;
import location.InformationPersonnelle;
import location.NonConnecteException;

/**
 * Tests JUnit pour la classe {@link location.GestionUtilisateur GestionUtilisateur}.
 *
 * @author Brossard Rayan
 * @see location.GestionUtilisateur
 */
public class TestGestionUtilisateur {
	
	/**
	 * Instance de GestionUtilisateur pour les tests.
	 */
    private GestionUtilisateur gestionUtilisateur;

    /**
     * Configuration initiale : instancie un objet GestionUtilisateur.
     */
    @BeforeEach
    void setUp() throws Exception {
        gestionUtilisateur = new GestionUtilisateur();
    }

    /**
     * Nettoyage après chaque test.
     */
    @AfterEach
    void tearDown() throws Exception {
        gestionUtilisateur = null;
    }

    /**
     * Test de l'inscription d'un utilisateur avec des informations valides.
     * @throws IOException 
     */
    @Test
    void testInscriptionReussie() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        int result = gestionUtilisateur.inscription("mariefc", "123456", info);
        assertEquals(0, result, "L'inscription devrait réussir avec les bonnes informations.");
    }

    /**
     * Test de l'inscription avec un pseudo déjà utilisé.
     * @throws IOException
     */
    @Test
    void testInscriptionPseudoDejaUtilise() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        gestionUtilisateur.inscription("mariefc", "123456", info);
        int result = gestionUtilisateur.inscription("mariefc", "password456", info);
        assertEquals(1, result, "L'inscription devrait échouer avec un pseudo déjà utilisé.");
    }

    /**
     * Test de l'inscription avec un pseudo ou mot de passe vide.
     * @throws IOException 
     */
    @Test
    void testInscriptionPseudoOuMotDePasseVide() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        int result = gestionUtilisateur.inscription("", "password123", info);
        assertEquals(2, result, "L'inscription devrait échouer avec un pseudo vide.");

        result = gestionUtilisateur.inscription("jeandup", "", info);
        assertEquals(2, result, "L'inscription devrait échouer avec un mot de passe vide.");
    }

    /**
     * Test de l'inscription avec des informations personnelles incorrectes.
     * @throws IOException 
     */
    @Test
    void testInscriptionInfoIncorrecte() throws IOException {
        int result = gestionUtilisateur.inscription("mariefc", "123456", null);
        assertEquals(3, result, "L'inscription devrait échouer avec des informations personnelles incorrectes.");
    }

    /**
     * Test de la connexion avec des identifiants valides.
     * @throws IOException 
     */
    @Test
    void testConnexionReussie() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        gestionUtilisateur.inscription("mariefc", "123456", info);
        assertTrue(gestionUtilisateur.connexion("mariefc", "123456"), "La connexion devrait réussir avec les bons identifiants.");
    }

    /**
     * Test de la connexion avec un mot de passe incorrect.
     * @throws IOException 
     */
    @Test
    void testConnexionMotDePasseIncorrect() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        gestionUtilisateur.inscription("mariefc", "123456", info);
        assertFalse(gestionUtilisateur.connexion("mariefc", "wrongpassword"), "La connexion devrait échouer avec un mot de passe incorrect.");
    }

    /**
     * Test de la connexion avec un pseudo inexistant.
     */
    @Test
    void testConnexionPseudoInexistant() {
        assertFalse(gestionUtilisateur.connexion("inexistant", "123456"), "La connexion devrait échouer avec un pseudo inexistant.");
    }

    /**
     * Test de la déconnexion réussie.
     * @throws IOException 
     */
    @Test
    void testDeconnexionReussie() throws NonConnecteException, IOException {
        InformationPersonnelle info = new InformationPersonnelle("Fance", "Marie", "12 rue de Chist", 22);
        gestionUtilisateur.inscription("mariefc", "123456", info);
        gestionUtilisateur.connexion("mariefc", "123456");
        gestionUtilisateur.deconnexion();
        assertFalse(gestionUtilisateur.estConnecte(), "L'utilisateur devrait être déconnecté.");
    }

    /**
     * Test de la déconnexion sans utilisateur connecté.
     */
    @Test
    void testDeconnexionNonConnecte() {
        assertThrows(NonConnecteException.class, () -> gestionUtilisateur.deconnexion(), "Une exception devrait être levée si aucun utilisateur n'est connecté.");
    }

    /**
     * Test de la suppression d'un utilisateur existant.
     * @throws IOException 
     */
    @Test
    void testSuppressionUtilisateur() throws IOException {
        InformationPersonnelle info = new InformationPersonnelle("Point", "Benoit", "29bis avenue de Brest", 38);
        gestionUtilisateur.inscription("benoitp", "password123", info);
        assertTrue(gestionUtilisateur.supprimerUtilisateur("benoitp"), "La suppression de l'utilisateur devrait réussir.");
        assertNull(gestionUtilisateur.getUtilisateur("benoitp"), "L'utilisateur supprimé ne devrait plus exister.");
    }

    /**
     * Test de la suppression d'un utilisateur inexistant.
     */
    @Test
    void testSuppressionUtilisateurInexistant() {
        assertFalse(gestionUtilisateur.supprimerUtilisateur("inexistant"), "La suppression devrait échouer pour un utilisateur inexistant.");
    }
}
