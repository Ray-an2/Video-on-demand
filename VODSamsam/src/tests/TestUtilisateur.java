package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import location.InformationPersonnelle;
import location.Utilisateur;
import location.NonConnecteException;

/**
 * Tests JUnit de la classe {@link location.Utilisateur Utilisateur}.
 *
 * @author Rayan Brossard
 * @see location.Utilisateur
 */
public class TestUtilisateur {

	/**
	 * Instance de Utilisateur pour les tests.
	 */
	private Utilisateur utilisateur;

	/**
	 * Configuration initiale : création d'un utilisateur avec des informations
	 * personnelles.
	 */
	@BeforeEach
	void setUp() {
		InformationPersonnelle info = new InformationPersonnelle("Jean", "Dupont", "123 rue de Paris", 35);
		utilisateur = new Utilisateur("jeandup", "01234", info);
	}

	/**
	 * Nettoyage après chaque test (si nécessaire).
	 */
	@AfterEach
	void tearDown() {
		utilisateur = null;
	}

	/**
	 * Test de la récupération du pseudo de l'utilisateur.
	 */
	@Test
	void testGetPseudo() {
		assertEquals("jeandup", utilisateur.getPseudo());
	}

	/**
	 * Test de la récupération du mot de passe de l'utilisateur.
	 */
	@Test
	void testGetMotDePasse() {
		assertEquals("01234", utilisateur.getMotDePasse());
	}

	/**
	 * Test de la récupération des informations personnelles de l'utilisateur.
	 */
	@Test
	void testGetInfo() {
		InformationPersonnelle info = utilisateur.getInfo();
		assertEquals("Jean", info.getPrenom());
		assertEquals("Dupont", info.getNom());
		assertEquals("123 rue de Paris", info.getAdresse());
		assertEquals(35, info.getAge());
	}

	/**
	 * Test de la connexion d'un utilisateur.
	 */
	@Test
	void testConnexion() {
		assertTrue(utilisateur.connexion("jeandup", "01234"), "Connexion avec les bons identifiants devrait réussir");
		assertFalse(utilisateur.connexion("jeandup", "wrongpassword"),
				"Connexion avec un mauvais mot de passe devrait échouer");
	}

	/**
	 * Test de la déconnexion d'un utilisateur non connecté.
	 */
	@Test
	void testDeconnexionNonConnecte() {
		assertThrows(NonConnecteException.class, () -> {
			utilisateur.deconnexion();
		});
	}
}