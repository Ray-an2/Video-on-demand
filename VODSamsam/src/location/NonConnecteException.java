package location;

/**
 * Exception levée quand il y a un problème avec le compte d'un utilisateur ou
 * sa connexion.
 *
 * @author Eric Cariou
 */
public class NonConnecteException extends Exception {

	/**
	 * Identifiant de sérialisation.
	 */
	private static final long serialVersionUID = -2876441299971092712L;
	
	// Constructeur avec message personnalisé
	  public NonConnecteException(String message) {
	      super(message);
	  }

}
