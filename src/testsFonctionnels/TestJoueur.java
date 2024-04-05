package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import jeu.Joueur;
import jeu.MainAsList;
import jeu.ZoneDeJeu;
import paquet.Borne;
import paquet.DebutLimite;
import paquet.FinLimite;

class TestJoueur {
	
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
		DebutLimite debutL = new DebutLimite(1);
		FinLimite finL = new FinLimite(1);
		jj.donner(debutL);
		jj.donner(finL);
		assertEquals(200,jj.donnerLimitationVitesse()); //aucune limite
		jj.deposer(debutL);
		assertEquals(50,jj.donnerLimitationVitesse());
		jj.deposer(finL);
		assertEquals(200,jj.donnerLimitationVitesse());
	}
}
