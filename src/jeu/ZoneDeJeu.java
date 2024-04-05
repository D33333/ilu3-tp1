package jeu;

import paquet.Attaque;
import paquet.Bataille;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.Cartes;
import paquet.DebutLimite;
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
		for (Borne borne : collectionDeBornes) {
			somme += borne.getKm();
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
		if (pileDeLimites.isEmpty() || pileDeLimites.get(pileDeLimites.size()-1) instanceof FinLimite || bottes.contains(Cartes.PRIORITAIRE)) {
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

	public boolean estBloque() {
		boolean estPrio = bottes.contains(Cartes.PRIORITAIRE);
		//System.out.println("EstPrio : "+estPrio);
		boolean pileVideEtPrio = false;
		boolean sommetParFeu = false;
		boolean sommetParPrio = false;
		boolean sommetAttFeuPrio = false;
		boolean sommetAttBottePrio = false;
		if (pileDeBataille.isEmpty()) {
			pileVideEtPrio = estPrio;
		} else {
			Carte sommet = pileDeBataille.get(pileDeBataille.size()-1);
			//System.out.println("Sommet : "+sommet);
			if (sommet instanceof Parade) {
				Parade p = (Parade) sommet;
				sommetParFeu = p.getType().equals(Type.FEU);
				sommetParPrio = estPrio;
			}
			if (sommet instanceof Attaque) {
				Attaque a = (Attaque) sommet;
				sommetAttFeuPrio = a.getType().equals(Type.FEU) && estPrio;
				sommetAttBottePrio = !a.getType().equals(Type.FEU) && aBotte(a.getType()) && estPrio;
			}
		}
		//System.out.println("1 : "+pileVideEtPrio+" 2 : "+sommetParFeu+" 3 : "+sommetParPrio+" 4 : "+sommetAttFeuPrio+" 5 : "+sommetAttBottePrio);
		return !(pileVideEtPrio || sommetParFeu || sommetParPrio || sommetAttFeuPrio || sommetAttBottePrio);
	}
	
	public void enleverToutesLesBottes() {
		bottes.removeAll(bottes);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne) {
			Borne b = (Borne) carte;
			return !this.estBloque() && b.getKm()<=this.donnerLimitationVitesse() && this.donnerKmParcourus()<1000;
		} else if (carte instanceof Botte) {
			return true;
		} else if (carte instanceof DebutLimite) {
			return !bottes.contains(new Botte(1,Type.FEU)) && this.donnerLimitationVitesse()==200;
		} else if (carte instanceof FinLimite) {
			return !bottes.contains(new Botte(1,Type.FEU)) && this.donnerLimitationVitesse()==50;
		} else { //Bataille
			Bataille carteJouee = (Bataille) carte;
			Bataille top = new Attaque(1,Type.FEU);
			if (!pileDeBataille.isEmpty()) {
				top = pileDeBataille.get(pileDeBataille.size()-1);
			} else if (carte.equals(new Botte(1,Type.FEU)) || carte.equals(new Attaque(1,Type.FEU))) {
				top = new Parade(1,Type.FEU);
			}
			
			if (top instanceof Attaque) {
				Attaque att = (Attaque) top;
				return !aBotte(att.getType()) && carteJouee.getType().equals(att.getType());
			} else if (top instanceof Parade) {
				return (!aBotte(carteJouee.getType()));
			}
		}
		return false;
	}
	
	public boolean deposer(Carte c) {
		if (!this.estDepotAutorise(c)) {
			return false;
		}
		if (c instanceof Borne) {
			collectionDeBornes.add((Borne) c);
			return true;
		} else if (c instanceof Botte) {
			bottes.add((Botte) c);
			if (!pileDeBataille.isEmpty()) {
				Bataille bat = pileDeBataille.get(pileDeBataille.size()-1);
				if (bat instanceof Attaque && ((Attaque) bat).getType().equals(((Botte) c).getType())) {
					pileDeBataille.remove(pileDeBataille.size()-1);
				}
			}
			return true;
		} else if (c instanceof DebutLimite) {
			pileDeLimites.add((DebutLimite) c);
			return true;
		} else if (c instanceof FinLimite) {
			pileDeLimites.add((FinLimite) c);
			return true;
		} else if (c instanceof Bataille) {
			if (!pileDeBataille.isEmpty()) {
				Bataille bat = pileDeBataille.get(pileDeBataille.size()-1);
				if (c instanceof Attaque && bat instanceof Attaque) {
					return false;
				}
			}
			pileDeBataille.add((Bataille) c);
			return true;
		}
		return false;
	}
}
