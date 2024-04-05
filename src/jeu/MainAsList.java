package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import paquet.Carte;

public class MainAsList implements IMain {
	
	List<Carte> cartes = new LinkedList<>();
	
	public MainAsList() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Carte> getCartes() {
		return cartes;
	}

	@Override
	public Iterator<Carte> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prendre(Carte c) {
		cartes.add(c);
		System.out.println("contient : "+cartes.contains(c)+" "+c.equals(c));
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
	
	public boolean retirerDeLaMain(Carte carte) {
		if (cartes.contains(carte)) {
			return cartes.remove(carte);
		}
		return false;
	}
}
