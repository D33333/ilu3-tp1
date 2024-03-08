package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeu.Sabot;
import paquet.Attaque;
import paquet.Botte;
import paquet.Carte;
import paquet.JeuDeCartes;
import paquet.Parade;
import paquet.Probleme.Type;

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
		List<Carte> cartes = jeu.getListeDeCartes();
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
}
