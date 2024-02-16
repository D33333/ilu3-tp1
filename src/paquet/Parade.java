package paquet;

import paquet.Probleme.Type;

public class Parade extends Bataille {

	public Parade(int n, Type t) {
		super(n, t);
	}

	@Override
	public String toString() {
		String nom;
		Type type = getType();
		if (type==Type.FEU) {
			nom = "Feu Vert";
		} else if (type == Type.ESSENCE) {
			nom = "Essence";
		} else if (type == Type.CREVAISON) {
			nom = "Roue De Secours";
		} else {
			nom = "Réparation";
		}
		return nom;
	}
}
