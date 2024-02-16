package testsFonctionnels;

import jeu.Sabot;
import paquet.Attaque;
import paquet.Botte;
import paquet.Carte;
import paquet.Parade;
import paquet.Probleme.Type;

public class Test {
	
	public static void main (String args[]) {
		//Création des cartes et du sabot
		Carte accident = new Attaque(3, Type.ACCIDENT);
		Carte reparation = new Parade(6, Type.ACCIDENT);
		Carte asDuVolant = new Botte(1, Type.ACCIDENT);
		Carte[] famille = {accident, reparation, asDuVolant};
		
		Sabot sabot = new Sabot(110);
		
		//Ajout des cartes dans le sabot
		sabot.ajouterFamilleCarte(famille);
		
		while(!sabot.estVide()) {
			Carte carte = sabot.piocher();
			System.out.println("je pioche "+carte);
		}
	}
}
