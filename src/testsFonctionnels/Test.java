package testsFonctionnels;

import java.util.Iterator;

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
		Carte reparation = new Parade(3, Type.ACCIDENT);
		Carte asDuVolant = new Botte(1, Type.ACCIDENT);
		Carte[] famille = {accident, reparation, asDuVolant};
		
		Sabot sabot = new Sabot(110);
		
		//Ajout des cartes dans le sabot
		sabot.ajouterFamilleCarte(famille);
		
		System.out.println("Méthode 1 pour piocher question a");
		while(!sabot.estVide()) {
			System.out.println("je pioche "+sabot.piocher());
		}
		
		
		sabot.ajouterFamilleCarte(famille);
		
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
}
