package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import paquet.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes =0; //on ajoute des cartes au fur et à mesure dans la pioche
	private int nombreOperations = 0;
	
	public Sabot(int capacite) {
		cartes = new Carte[capacite];
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}

	private void ajouterCarte(Carte carte) throws IllegalArgumentException {
		if(nbCartes < cartes.length) {
			cartes[nbCartes] = carte;
			nbCartes++;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void ajouterFamilleCarte(Carte carte) {
		int nbCartesFamille = carte.getNombre();
		for (int i = 0; i < nbCartesFamille; i++) {
			ajouterCarte(carte);
		}
	}
	
	public void ajouterFamilleCarte(Carte[] cartes) {
		int i_carte = 0;
		while (i_carte < cartes.length && cartes[i_carte]!=null) {
			ajouterFamilleCarte(cartes[i_carte]);
			i_carte++;
		}
	}

	// Utilisation des itérateurs pour parcourir les cartes
	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur = iterator();
		Carte carte = iterateur.next();
		iterateur.remove();
		return carte;
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private int nombreOperationsReference = nombreOperations;
		private boolean nextEffectue = false;
		
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new IllegalStateException();
			}
		}
		
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur-1; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
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
}
