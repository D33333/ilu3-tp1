package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import paquet.Carte;

public class Joueur {
	
	private String nom;
	private List<Carte> pileDeLimites = new ArrayList<>();
	private List<Carte> pileDeBataille = new ArrayList<>();
	private List<Carte> collectionDeBornes = new ArrayList<>();
	private Set<Carte> bottes;
	private MainAsList main;
	
	public Joueur(String nom, List<Carte> lims, List<Carte> bat, List<Carte> bornes, Set<Carte> bottes, MainAsList main){
		this.nom = nom;
		this.pileDeLimites = lims;
		this.pileDeBataille = bat;
		this.collectionDeBornes = bornes;
		this.bottes = bottes;
		this.main = main;
	}
	
	public String getNom() {
		return nom;
	}

	public List<Carte> getPileDeLimites() {
		return pileDeLimites;
	}

	public List<Carte> getPileDeBataille() {
		return pileDeBataille;
	}

	public List<Carte> getCollectionDeBornes() {
		return collectionDeBornes;
	}

	public Set<Carte> getBottes() {
		return bottes;
	}
	
	public Main getMain() {
		return main;
	}
	
	@Override
	public boolean equals(Object objet) {
		if (objet instanceof Joueur) {
			Joueur j = (Joueur) objet;
			return j.getNom().equals(this.nom);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public void donner(Carte c) {
		main.prendre(c);
		assert(main.possede(c));
	}
	
	public Carte prendreCarte(List<Carte> sabot) {
		if (sabot.isEmpty()) {
			return null;
		}
		Carte c = sabot.get(0);
		sabot.remove(0);
		return c;
	}
	
	public int getKM() {
		int somme = 0;
		for (Carte borne : collectionDeBornes) {
			somme += borne.getNombre();
		}
		return somme;
	}
}
