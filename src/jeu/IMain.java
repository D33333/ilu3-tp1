package jeu;

import paquet.Carte;

public interface IMain extends Iterable<Carte> {
	public void prendre(Carte c);
	public void jouer(Carte c);
}
