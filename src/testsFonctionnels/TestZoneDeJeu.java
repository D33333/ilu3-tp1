package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Joueur;
import jeu.MainAsList;
import jeu.ZoneDeJeu;
import paquet.Attaque;
import paquet.Borne;
import paquet.Botte;
import paquet.Probleme.Type;

class TestZoneDeJeu {

	@Test
	void lancer_testsZDJ() {
		ZoneDeJeu zDeJeu = new ZoneDeJeu();
		Attaque feuRouge = new Attaque(1,Type.FEU);
		Botte vPrio = new Botte(1,Type.FEU);
		Attaque acc = new Attaque(1,Type.ACCIDENT);
		Botte asDuVolant = new Botte(1,Type.ACCIDENT);
		Attaque panneEss = new Attaque(1,Type.ESSENCE);
		Botte asDuVolant = new Botte(1,Type.ACCIDENT);
		Borne b = new Borne(3,25);
		jj.donner(b);
		assertEquals(0,jj.getKM());
		jj.deposer(b);
		assertEquals(25,jj.getKM());
		jj.deposer(b);
		assertEquals(50,jj.getKM());
	}

}
