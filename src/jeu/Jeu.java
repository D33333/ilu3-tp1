package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import paquet.Carte;
import paquet.JeuDeCartes;

public class Jeu {
	private Sabot sabot = new Sabot(106);
	private LinkedHashSet<Joueur> joueurs;
	public final static int NBCARTES = 6;
	
	public Jeu(LinkedHashSet<Joueur> joueurs) {
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
	
	public void jouerTour(Joueur joueurAyantMain) {
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
	
	//Itérateur sur les joueurs
	private class Iterateur implements Iterator<Joueur> {
		private int indiceIterateur = 0;
		//private int nombreOperationsReference = nombreOperations;
		private boolean nextEffectue = false;
		
		public boolean hasNext() {
			return indiceIterateur < joueurs.size();
		}
		
		public Joueur next() {
			verificationConcurrence();
			if (hasNext()) {
				Joueur joueur = joueurs.get(indiceIterateur);
				indiceIterateur++;
				nextEffectue = true;
				return joueur;
			} else {
				throw new IllegalStateException();
			}
		}
		
		public void remove() {
			verificationConcurrence();
			if (joueurs.size() < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur-1; i < joueurs.size()-1; i++) {
				joueurs[i] = joueurs[i+1];
			}
			nextEffectue = false;
			indiceIterateur --;
			nbCartes --;
			nombreOperations++;
			nombreOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}
	
	public Joueur donnerJoueurSuivant() {
		
	}
	
	public boolean sabotEstVide() {
		return sabot.estVide();
	}
	
	public void lancer() {
		
	}
}
