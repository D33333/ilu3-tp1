package jeu;

import paquet.Attaque;
import paquet.Bataille;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.Cartes;
import paquet.FinLimite;
import paquet.Limite;
import paquet.Parade;
import paquet.Probleme.Type;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ZoneDeJeu implements Cartes {
	
	private List<Limite> pileDeLimites = new LinkedList<>();
	private List<Bataille> pileDeBataille = new LinkedList<>();
	private List<Borne> collectionDeBornes = new LinkedList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	public List<Limite> getPileDeLimites() {
		return pileDeLimites;
	}

	public List<Bataille> getPileDeBataille() {
		return pileDeBataille;
	}

	public List<Borne> getCollectionDeBornes() {
		return collectionDeBornes;
	}

	public Set<Botte> getBottes() {
		return bottes;
	}
	
	public int donnerKmParcourus() {
		int somme = 0;
		for (Carte borne : collectionDeBornes) {
			somme += borne.getNombre();
		}
		return somme;
	}
	
	public void deposer(Borne borne) {
		collectionDeBornes.add(borne);
	}
	
	public void deposer(Limite lim) {
		pileDeLimites.add(lim);
	}
	
	public void deposer(Bataille bat) {
		pileDeBataille.add(bat);
	}
	
	public void deposer(Botte botte) {
		bottes.add(botte);
	}

	public int donnerLimitationVitesse() {
		if (pileDeLimites.isEmpty() || pileDeLimites.get(0) instanceof FinLimite || bottes.contains(PRIORITAIRE)) {
			return 200;
		} else {
			return 50;
		}
	}
	
	private boolean aBotte(Type type) {
		for (Botte b : bottes) {
			if (b.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public boolean peutAvancer() {
		boolean estPrio = bottes.contains(PRIORITAIRE);
		boolean pileVideEtPrio = pileDeBataille.isEmpty() && estPrio;
		Carte sommet = pileDeBataille.get(0);
		boolean sommetParFeu = false;
		boolean sommetParPrio = false;
		if (sommet instanceof Parade) {
			Parade p = (Parade) sommet;
			sommetParFeu = p.getType().equals(Type.FEU);
			sommetParPrio = estPrio;
		}
		boolean sommetAttFeuPrio = false;
		boolean sommetAttBottePrio = false;
		if (sommet instanceof Attaque) {
			Attaque a = (Attaque) sommet;
			sommetAttFeuPrio = a.getType().equals(Type.FEU) && estPrio;
			sommetAttBottePrio = !a.getType().equals(Type.FEU) && aBotte(a.getType()) && estPrio;
		}
		return pileVideEtPrio || sommetParFeu || sommetParPrio || sommetAttFeuPrio || sommetAttBottePrio;
	}
}
