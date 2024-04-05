package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import paquet.Carte;

public class MainAsList implements IMain {
	
	List<Carte> cartes = new ArrayList<>();
	
	public MainAsList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterator<Carte> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prendre(Carte c) {
		cartes.add(c);		
	}

	public boolean possede(Carte c) {
		return cartes.contains(c);
	}
	
	@Override
	public void jouer(Carte c) {
		assert(possede(c));
		cartes.remove(c);
	}
	
	@Override
	public String toString() {
		StringBuilder chaine = new StringBuilder("Dans la main il y a :\n");
		for (Carte carte : cartes) {
			chaine.append(" - "+carte+"\n");
		}
		return chaine.toString();
	}
}
