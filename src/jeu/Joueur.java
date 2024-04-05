package jeu;

import java.util.List;

import paquet.Bataille;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.Limite;

public class Joueur {
	
	private String nom;
	private ZoneDeJeu zDeJeu;
	private MainAsList main;
	
	public Joueur(String nom, ZoneDeJeu zDeJeu, MainAsList main){
		this.nom = nom;
		this.zDeJeu = zDeJeu;
		this.main = main;
	}
	
	public String getNom() {
		return nom;
	}
	
	public IMain getMain() {
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
	
	public void deposer(Borne borne) {
		zDeJeu.deposer(borne);
	}
	
	public void deposer(Limite lim) {
		zDeJeu.deposer(lim);
	}
	
	public void deposer(Bataille bat) {
		zDeJeu.deposer(bat);
	}
	
	public void deposer(Botte botte) {
		zDeJeu.deposer(botte);
	}
	
	public int getKM() {
		return zDeJeu.donnerKmParcourus();
	}
	
	public int donnerLimitationVitesse() {
		return zDeJeu.donnerLimitationVitesse();
	}
	
	public boolean estBloque() {
		return !zDeJeu.peutAvancer();
	}
}
