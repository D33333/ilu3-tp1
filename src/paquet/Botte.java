package paquet;

import java.util.HashMap;
import java.util.Map;

public class Botte extends Probleme {
	
	private static Map<Type,String> nomType = new HashMap<>();
	static {
		nomType.put(Type.FEU,"Véhicule Prioritaire");
		nomType.put(Type.ESSENCE,"Citerne d'Essence");
		nomType.put(Type.CREVAISON,"Increvable");
		nomType.put(Type.ACCIDENT,"As Du Volant");
	};
	
	public Botte(int n, Type t) {
		super(n, t);
	}

	@Override
	public String toString() {
		return nomType.get(getType());
	}
}
