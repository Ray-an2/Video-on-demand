package location;

import java.io.IOException;
import java.util.Set;
import io.Sauvegarde;

/**
 * Implémentation des services de gestion des films pour l'administrateur de
 * l'application.
 *
 * @author Olabiyi ELEGBEDE
 */
public class Administration implements InterAdministration {
	
	/**Classe de gestion des films.*/
	private GestionFilm gestionFilm = new GestionFilm();
	
	/**Classe de gestion des artistes.*/
	private GestionArtiste gestionArtiste = new GestionArtiste();

	/*
	 * public Administration() {
	 * 
	 * //Je charge les données ici pour récupérer l'ensemble des films et l'ensemble
	 * des artistes }
	 */

	@Override
	public Artiste creerArtiste(String nom, String prenom, String nationalite) {

		return this.gestionArtiste.creerArtiste(nom, prenom, nationalite);
		/*
		 * ici je vérifie d'abord que dans l'ensemble des artistes je n'ai pas un
		 * artiste qui a déjà un nom qui correspond déjà
		 */
		/*
		 * if (getArtiste(nom, prenom) == null) { if (nom.trim().isEmpty()) { return
		 * null; } else { if (role.equals("Acteur")) { acteur = new Acteur(nom, prenom,
		 * nationalite); gestionArtiste.ajoutArtiste(acteur); return acteur; }
		 * 
		 * else { real = new Realisateur(nom, prenom, nationalite);
		 * gestionArtiste.ajoutArtiste(real); return real; } } } return null;
		 */

	}

	@Override
	public boolean supprimerArtiste(Artiste artiste) {

		return gestionArtiste.supprimerArtiste(artiste);
	}

	@Override
	public Film creerFilm(String titre, Artiste realisateur, int annee, int ageLimite) {

		return gestionFilm.creerFilm(titre, realisateur, annee, ageLimite);

	}

	@Override
	public boolean ajouterActeurs(Film film, Artiste... acteurs) {
		return gestionFilm.ajouterActeurs(film, acteurs);
	}

	@Override
	public boolean ajouterGenres(Film film, Genre... genres) {
		// TODO Auto-generated method stub
		return gestionFilm.ajouterGenres(film, genres);
	}

	@Override
	public boolean ajouterAffiche(Film film, String file) throws IOException {
		// TODO Auto-generated method stub
		return gestionFilm.ajouterAffiche(film, file);
	}

	@Override
	public boolean supprimerFilm(Film film) {
		// TODO Auto-generated method stub
		return gestionFilm.supprimerFilm(film);
	}

	@Override
	public Set<Film> ensembleFilms() {
		// TODO Auto-generated method stub
		return gestionFilm.ensembleFilms();
	}

	@Override
	public Set<Artiste> ensembleActeurs() {
		// TODO Auto-generated method stub
		return gestionArtiste.ensembleActeurs();
	}

	@Override
	public Set<Artiste> ensembleRealisateurs() {
		// TODO Auto-generated method stub
		return gestionArtiste.ensembleRealisateurs();
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
		// TODO Auto-generated method stub
		return gestionArtiste.ensembleFilmsRealisateur(realisateur);
	}

	@Override
	public Set<Film> ensembleFilmsActeur(Artiste acteur) {
		// TODO Auto-generated method stub
		return gestionArtiste.ensembleFilmsActeur(acteur);
	}

	@Override
	public Artiste getArtiste(String nom, String prenom) {

		return gestionArtiste.getArtiste(nom, prenom);
	}

	@Override
	public Film getFilm(String titre) {
		return gestionFilm.getFilm(titre);
	}

	@Override
	public boolean ouvrirLocation(Film film) {
		// TODO Auto-generated method stub
		return gestionFilm.ouvrirLocation(film);
	}

	@Override
	public boolean fermerLocation(Film film) {
		// TODO Auto-generated method stub
		return gestionFilm.fermerLocation(film);
	}

}
