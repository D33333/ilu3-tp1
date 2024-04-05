package jeu;

import paquet.Attaque;
import paquet.Carte;
import paquet.DebutLimite;
import paquet.FinLimite;
import paquet.Limite;

public class Coup {

	private Carte carte;
	private Joueur joueurCible;
	
	public Coup(Carte carte, Joueur joueurCible) {
		this.carte = carte;
		this.joueurCible = joueurCible;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueurCible() {
		//peut-être null auquel cas remettre la carte dans le sabot
		return joueurCible;
	}
	
	public boolean estValide(Joueur joueur) {
		boolean surSoi = joueurCible.equals(joueur);
		if (carte instanceof Attaque || carte instanceof DebutLimite) {
			return !surSoi;
		} else {
			return surSoi;
		}
	}
	
	@Override
	public boolean equals(Object objet) {
		if (objet instanceof Coup) {
			Coup coup = (Coup) objet;
			return coup.getCarte().equals(this.carte) && coup.getJoueurCible().equals(this.joueurCible);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*(carte.hashCode()+joueurCible.hashCode());
	}
}