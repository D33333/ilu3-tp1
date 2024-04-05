package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.ZoneDeJeu;
import paquet.Attaque;
import paquet.Botte;
import paquet.Cartes;
import paquet.Parade;
import paquet.Probleme.Type;

class TestZoneDeJeu implements Cartes {

	@Test
	void lancer_testsZDJ() {
		ZoneDeJeu zDeJeu = new ZoneDeJeu();
		Attaque acc = new Attaque(1,Type.ACCIDENT);
		Botte asDuVolant = new Botte(1,Type.ACCIDENT);
		Attaque panneEss = new Attaque(1,Type.ESSENCE);
		Botte essence = new Botte(1,Type.ESSENCE);
		zDeJeu.deposer((Attaque) Cartes.FEU_ROUGE);
		assertTrue(zDeJeu.estBloque());
		zDeJeu.deposer((Botte) Cartes.PRIORITAIRE);
		assertFalse(zDeJeu.estBloque());
		zDeJeu.deposer(acc);
		assertTrue(zDeJeu.estBloque());
		zDeJeu.deposer(asDuVolant);
		assertFalse(zDeJeu.estBloque());
		zDeJeu.deposer(panneEss);
		assertTrue(zDeJeu.estBloque());
		zDeJeu.deposer(essence);
		assertFalse(zDeJeu.estBloque());
		//Cartes bien ajoutées
		assertEquals(3,zDeJeu.getBottes().size());
		assertEquals(3,zDeJeu.getPileDeBataille().size());
		
		//Efface les bottes
		zDeJeu.enleverToutesLesBottes();
		assertTrue(zDeJeu.estBloque());
		
		//Ajout du feu vert
		zDeJeu.deposer((Parade) Cartes.FEU_VERT);
		assertFalse(zDeJeu.estBloque());
	}

}
