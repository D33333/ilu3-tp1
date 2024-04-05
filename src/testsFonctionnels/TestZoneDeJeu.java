package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import jeu.ZoneDeJeu;
import paquet.Attaque;
import paquet.Borne;
import paquet.Botte;
import paquet.Carte;
import paquet.Cartes;
import paquet.DebutLimite;
import paquet.FinLimite;
import paquet.Limite;
import paquet.Parade;
import paquet.Probleme.Type;

class TestZoneDeJeu implements Cartes {

	@Test
	void tests_tp3_lancer_testsZDJ() {
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

	@Test
	void tests_tp4_lancer_testsZDJ() {
		ZoneDeJeu zDeJeu = new ZoneDeJeu();
		boolean depotOK;
		Carte carte;
		
		carte = new Attaque(1,Type.FEU);//feu rouge
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
		
		carte = new Attaque(1,Type.ACCIDENT);
		zDeJeu.deposer(new Botte(1,Type.ACCIDENT)); //la botte empêche le dépôt de l'accident
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Botte(1,Type.ACCIDENT);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Attaque(1,Type.ESSENCE);
		zDeJeu.deposer(new Botte(1,Type.ESSENCE));
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Parade(1,Type.ESSENCE);
		zDeJeu.deposer(new Attaque(1,Type.FEU));
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Parade(1,Type.FEU);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Borne(1,100);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new DebutLimite(1);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Borne(1,100);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Borne(1,25);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new FinLimite(1);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	
		carte = new Borne(1,100);
		depotOK = zDeJeu.deposer(carte);
		System.out.println("déposer "+carte+" : dépôt ok ? "+depotOK+", bloqué ? "+zDeJeu.estBloque());
	}
}
