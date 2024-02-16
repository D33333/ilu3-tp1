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
			nom = "FeuRouge";
		} else if (type == Type.ESSENCE) {
			nom = "PanneEssence";
		} else if (type == Type.CREVAISON) {
			nom = "Crevaison";
		} else {
			nom = "Accident";
		}
		return nom;
	}
}
