package jeu;

import java.util.Set;

import paquet.Carte;
import paquet.JeuDeCartes;

public class Jeu {
	Set<Joueur> joueurs;
	Sabot sabot = new Sabot(106);
	public final static int NBCARTES = 6;
	
	public Jeu(Set<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public void inscrire(Joueur joueur) {
		joueurs.add(joueur);
	}
	
	public void remplirSabot(JeuDeCartes jeu) {
		for (Carte c : jeu.getListeCartes()) {
			sabot.ajouterFamilleCarte(c);
		}
	}

	public void distribuerCartes() {
		int i = 0;
		while(i<NBCARTES) {
			for (Joueur j : joueurs) {
				j.donner(sabot.piocher());
			}
			i++;
		}
	}
	
	public void jouerTour() {
		for (Joueur j : joueurs) {
			Carte carte = sabot.piocher();
			j.donner(carte);
			System.out.println("Le joueur "+j+" a pioche: "+carte);
			System.out.println("Il a dans sa main : "+j.getMain());
			Coup coupChoisi = j.choisirCoup(joueurs);
			System.out.println(coupChoisi);
			j.retirerDeLaMain(coupChoisi.getCarte());
			if (coupChoisi.getJoueurCible()==null) {
				sabot.ajouterFamilleCarte(coupChoisi.getCarte());
			} else {
				coupChoisi.getJoueurCible().deposer(coupChoisi.getCarte());
			}
		}
	}
}
