package jeu;

import paquet.Carte;

public interface Main extends Iterable<Carte> {
	public void prendre(Carte c);
	public void jouer(Carte c);
}
