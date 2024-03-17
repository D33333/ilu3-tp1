package paquet;

import java.util.LinkedList;
import java.util.List;

import paquet.Probleme.Type;

public class JeuDeCartes {
	
	List<Carte> listeCartes = new LinkedList<>();

	Carte[] typesDeCartes = {new Botte(1,Type.CREVAISON), new Botte(1,Type.ESSENCE), new Botte(1,Type.FEU), new Botte(1,Type.ACCIDENT),
			new Attaque(5,Type.FEU), new DebutLimite(4), new Attaque(3,Type.ESSENCE), new Attaque(3,Type.CREVAISON), new Attaque(3,Type.ACCIDENT),
			new Parade(14,Type.FEU), new FinLimite(6), new Parade(6,Type.ESSENCE), new Parade(6,Type.CREVAISON), new Parade(6,Type.ACCIDENT),
			new Borne(10,25), new Borne(10,50), new Borne(10,75), new Borne(12,100), new Borne(4,200)};
	
	public JeuDeCartes() {
		ajouterFamilleCarte(listeCartes, typesDeCartes);
	}
	
	private void ajouterFamilleCarte(List<Carte> liste, Carte carte) {
		int nbCartesFamille = carte.getNombre();
		for (int i = 0; i < nbCartesFamille; i++) {
			liste.add(carte);
		}
	}
	
	private void ajouterFamilleCarte(List<Carte> liste, Carte[] cartes) {
		int i_carte = 0;
		while (i_carte < cartes.length && cartes[i_carte]!=null) {
			ajouterFamilleCarte(liste, cartes[i_carte]);
			i_carte++;
		}
	}
	
	public List<Carte> getListeCartes(){
		return listeCartes;
	}
	
	private int compter_nb_exs(Carte famille, List<Carte> listeCartes) {
		int compteur = 0;
		for (Carte carte : listeCartes) {
			if (carte == famille) {
				compteur++;
			}
		}
		return compteur;
	}
	
	public boolean checkCount() {
		for(Carte famille : typesDeCartes) {
			int nbExemplaires = famille.getNombre();
			if(compter_nb_exs(famille,listeCartes)!=nbExemplaires) {
				System.out.println("Carte "+famille+" : "+compter_nb_exs(famille,listeCartes)+"   "+nbExemplaires);
				return false;
			}
		}
		return true;
	}

}
