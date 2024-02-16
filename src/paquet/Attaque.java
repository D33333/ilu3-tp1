package paquet;

import paquet.Probleme.Type;

public class Attaque extends Bataille {
	
	public Attaque(int n, Type t) {
		super(n, t);
	}
	
	@Override
	public String toString() {
		String nom;
		Type type = getType();
		if (type==Type.FEU) {
			nom = "Feu Rouge";
		} else if (type == Type.ESSENCE) {
			nom = "Panne d'Essence";
		} else if (type == Type.CREVAISON) {
			nom = "Crevaison";
		} else {
			nom = "Accident";
		}
		return nom;
	}
}
