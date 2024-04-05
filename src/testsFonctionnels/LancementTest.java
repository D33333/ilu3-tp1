package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeu.Joueur;
import jeu.MainAsList;
import jeu.Sabot;
import paquet.Attaque;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.JeuDeCartes;
import paquet.Parade;
import paquet.Probleme.Type;
import utils.Utils;

class LancementTest {
	//Création des cartes et du sabot
	Carte accident = new Attaque(3, Type.ACCIDENT);
	Carte reparation = new Parade(3, Type.ACCIDENT);
	Carte asDuVolant = new Botte(1, Type.ACCIDENT);
	
	Sabot sabot = new Sabot(110);
			
	@BeforeEach
	public void init() {
		System.out.println("Initialisation...");
		//Ajout des cartes dans le sabot
		sabot.ajouterFamilleCarte(accident, reparation, asDuVolant);
	}
	
	@Test
	void lancer_tests_tp1_methode1() {
		System.out.println("Méthode 1 pour piocher question a");
		while(!sabot.estVide()) {
			System.out.println("je pioche "+sabot.piocher());
		}
	}
	
	@Test
	void lancer_tests_tp1_methode2() {
		System.out.println("Méthode 2 pour piocher question b");
		Iterator<Carte> iterateur = sabot.iterator();
		while(!sabot.estVide()) {
			Carte carteI = iterateur.next();
			iterateur.remove();
			System.out.println("je pioche "+carteI);
			//question c
			sabot.piocher();
			sabot.ajouterFamilleCarte(asDuVolant);
		}
	}
	
	@Test
	void lancer_tests_tp2_comparaisons() {
		//Comparaisons
		Carte carte1 = sabot.piocher();
		System.out.println("Ma carte de départ est : "+carte1);
		while(!sabot.estVide()) {
			Carte carte2 = sabot.piocher();
			System.out.println("je compare "+carte1+" à "+carte2+" : "+(carte1==carte2));
		}
	}
	
	@Test
	void lancer_tests_tp2_afficher_jeu() {
		//Affichage de toutes les cartes du jeu
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> cartes = jeu.getListeCartes();
		for (Carte carte : cartes) {
			System.out.println("Carte : "+carte);
		}
	}
	
	@Test
	void lancer_tests_tp2_check_count() {
		//Affichage de toutes les cartes du jeu
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println("Le nombre de carte a été respecté : "+jeu.checkCount());
	}
	
	private static <E> int nbOccs(List<E> liste, E e) {
		int compt = 0;
		for (E elem : liste) {
			if(e == elem) {
				compt++;
			}
		}
		return compt;
	}
	
	private static <E> boolean checkMemeOccs(List<E> liste, List<E> liste2) {
		int sizeListe = liste.size();
		int sizeListe2 = liste2.size();
		if(sizeListe != sizeListe2) {
			return false;
		}
		for (E elem : liste) {
			if(nbOccs(liste,elem)!=nbOccs(liste2,elem)) {
				return false;
			}
		}
		return true;
	}
	
	@Test
	void lancer_tests_tp2_utils() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = jeu.getListeCartes();
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = Utils.melanger(listeCartes);
		//Vérifier le nombre d'occurrences de chaque élément
		System.out.println("nb occurrences inchangé ? "
				+ checkMemeOccs(listeCarteNonMelangee,listeCartes));
		//Vérifier rassemblement
		System.out.println("liste mélangée sans erreur ? "
		+ Utils.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = Utils.rassember(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + Utils.verifierRassemblement(listeCartes));
	}
	
	@Test
	void lancer_tests_tp2_melangeAuto() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCartesAutomatiquementMelangees = jeu.getListeCartes();
		System.out.println(listeCartesAutomatiquementMelangees);
		//Vérifier le nombre d'occurrences de chaque élément
		System.out.println("nb occurrences inchangé ? "
				+ jeu.checkCount());
	}
	
	@Test
	void lancer_tests_tp3_getKM() {
		List<Carte> pileDeLimites = new ArrayList<>();
		List<Carte> pileDeBataille = new ArrayList<>();
		List<Carte> collectionDeBornes = new ArrayList<>();
		Set<Carte> bottes = new HashSet<Carte>();
		MainAsList main = new MainAsList();
		Joueur j = new Joueur("Bob", pileDeLimites, pileDeBataille, collectionDeBornes, bottes, main);
		Carte borne50 = new Borne(2,50);
		j.donner(borne50);
		assertEquals(100,j.getKM());
	}
}
