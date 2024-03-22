package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import paquet.Carte;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	public static <E> E extraire(List<E> liste) {
		Random random = new Random();
		
		int indice = random.nextInt(liste.size());
		E elem = liste.get(indice);
		liste.remove(indice);
		return elem;
	}

	public static <E> E extraire2(List<E> liste) {
		Random random = new Random();
		
		int indice = random.nextInt(liste.size());
		ListIterator<E> iterateur = liste.listIterator();
		E elem = null;
		while(!liste.isEmpty() && indice!=0) {
			elem = iterateur.next();
			indice--;
		}
		if (indice==0) {
			liste.remove(indice);
		}
		return elem;
	}
	
	public static List<Carte> melanger(List<Carte> liste){
		List<Carte> listeMelangee = new ArrayList<>();
		while(!liste.isEmpty()) {
			listeMelangee.add(extraire(liste));
		}
		return listeMelangee;
	}
	
	private static <E> boolean memeNbOccs(E elem, List<E> liste1, List<E> liste2) {
		return Collections.frequency(liste1, elem)== Collections.frequency(liste2, elem);
	}
	
	public static <E> boolean verifierMelange(List<E> liste1, List<E> liste2) {
		if (liste1.size() != liste2.size()) {
			return false;
		}
		for (E elem : liste2) {
			if (!memeNbOccs(elem,liste1,liste2)) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Carte> rassember(List<Carte> listeCartes) {
		int firstInd = 0;
		ListIterator<Carte> iterateurRecherche = listeCartes.listIterator();
		while (iterateurRecherche.hasNext()) {
			Carte elem = iterateurRecherche.next();
			int compt=0;
			while(iterateurRecherche.hasNext()) {
				if (iterateurRecherche.next() == elem) {
					iterateurRecherche.remove();
					compt++;
				}
			}
			//On revient en arrière
			while(iterateurRecherche.previousIndex()>firstInd) {
				iterateurRecherche.previous();
			}
			for(int i = 0; i < compt; i++) {
				iterateurRecherche.add(elem);
				firstInd++;
			}
			firstInd++;
		}
		return listeCartes;
	}
	/**
	 * Ou 
	 * Pour chaque carte e de la liste à trier
	 * ListIterator<T> it = listeTriee.listIterator();
	 * while (it.hasNext() && !it.next().equals(e));
	 * it.add(e);
	 */
	
	public static <E> boolean verifierRassemblement(List<E> liste) {
		ListIterator<E> iterateurBalais = liste.listIterator();
		int firstInd = 0;
		while (iterateurBalais.hasNext()) {
			E elem = iterateurBalais.next();
			while(iterateurBalais.hasNext() && iterateurBalais.nextIndex()<firstInd) {
				iterateurBalais.next();
			}
			ListIterator<E> iterateurRecherche = liste.listIterator();
			while(iterateurRecherche.hasNext() && iterateurRecherche.next() != elem) {
				
			}
			while(iterateurRecherche.hasNext() && iterateurRecherche.next() == elem) {
				firstInd++;
			}
			while(iterateurRecherche.hasNext()) {
				if (iterateurRecherche.next() == elem) {
					return false;
				}
			}
		}
		return true;
	}
}
