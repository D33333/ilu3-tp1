package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jeu.Joueur;
import jeu.MainAsList;
import jeu.Sabot;
import jeu.ZoneDeJeu;
import paquet.Attaque;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.DebutLimite;
import paquet.FinLimite;
import paquet.Parade;
import paquet.Probleme.Type;

class TestJoueur {
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
	void lancer_testsJ_ajoutBornes() {
		ZoneDeJeu zDeJeu = new ZoneDeJeu();
		MainAsList main = new MainAsList();
		Joueur jj = new Joueur("JJ",zDeJeu,main);
		Borne b = new Borne(3,25);
		jj.donner(b);
		assertEquals(0,jj.getKM());
		jj.deposer(b);
		assertEquals(25,jj.getKM());
		jj.deposer(b);
		assertEquals(50,jj.getKM());
	}
	
	@Test
	void lancer_testsJ_getLimite() {
		ZoneDeJeu zDeJeu = new ZoneDeJeu();
		MainAsList main = new MainAsList();
		Joueur jj = new Joueur("JJ",zDeJeu,main);
		DebutLimite debutL = new DebutLimite(50);
		FinLimite finL = new FinLimite(50);
		jj.donner(debutL);
		jj.donner(finL);
		assertEquals(200,jj.donnerLimitationVitesse()); //aucune limite
		jj.deposer(debutL);
		assertEquals(50,jj.donnerLimitationVitesse());
		jj.deposer(finL);
		assertEquals(200,jj.donnerLimitationVitesse());
	}
}
