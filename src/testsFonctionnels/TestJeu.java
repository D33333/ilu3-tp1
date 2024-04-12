package testsFonctionnels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jeu.Jeu;
import jeu.Joueur;
import jeu.MainAsList;
import jeu.ZoneDeJeu;
import paquet.Attaque;
import paquet.Borne;
import paquet.Botte;
import paquet.DebutLimite;
import paquet.JeuDeCartes;
import paquet.Parade;
import paquet.Probleme.Type;

class TestJeu {

	@Test
	void lancer_testsJeu() {
		//Cartes
		/*Borne b100 = new Borne(2,100);
		Parade reparation = new Parade(2,Type.ACCIDENT);
		Borne b50 = new Borne(1,50);
		Botte asDuV = new Botte(1,Type.ACCIDENT);
		
		DebutLimite lim = new DebutLimite(1);
		Borne b200 = new Borne(1,200);
		Attaque feuR = new Attaque(1,Type.FEU);//feu rouge
		Parade feuV = new Parade(2,Type.FEU);
		Botte prio = new Botte(1,Type.FEU);*/
		//Jeu
		ZoneDeJeu zDeJeuJ = new ZoneDeJeu();
		MainAsList mainJ = new MainAsList();
		/*mainJ.prendre(b100);
		mainJ.prendre(reparation);
		mainJ.prendre(b50);
		mainJ.prendre(asDuV);*/
		Joueur j1 = new Joueur("Jack",zDeJeuJ,mainJ);
		ZoneDeJeu zDeJeuB = new ZoneDeJeu();
		MainAsList mainB = new MainAsList();
		/*mainB.prendre(lim);
		mainB.prendre(b200);
		mainB.prendre(feuR);
		mainB.prendre(feuV);
		mainB.prendre(prio);*/
		Joueur j2 = new Joueur("Bill",zDeJeuB,mainB);
		Set<Joueur> joueurs = new HashSet();
		joueurs.add(j1);
		joueurs.add(j2);
		Jeu jeu = new Jeu(joueurs);
		JeuDeCartes jeucartes = new JeuDeCartes();
		jeu.remplirSabot(jeucartes);
		jeu.distribuerCartes();
		jeu.jouerTour();
	}

}
