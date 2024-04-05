package paquet;

import paquet.Probleme.Type;

public interface Cartes {
	Object PRIORITAIRE = new Botte(1,Type.FEU);
	Object FEU_ROUGE = new Attaque(5,Type.FEU);
	Object FEU_VERT = new Parade(14,Type.FEU);
}