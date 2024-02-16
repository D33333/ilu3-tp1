package paquet;

public class Botte extends Probleme {
	
	public Botte(int n, Type t) {
		super(n, t);
	}

	@Override
	public String toString() {
		String nom;
		Type type = getType();
		if (type==Type.FEU) {
			nom = "Véhicule Prioritaire";
		} else if (type == Type.ESSENCE) {
			nom = "Citerne d'Essence";
		} else if (type == Type.CREVAISON) {
			nom = "Increvable";
		} else {
			nom = "As Du Volant";
		}
		return nom;
	}
}
