package paquet;

import java.util.HashMap;
import java.util.Map;

public class Attaque extends Bataille {
	
	private static Map<Type,String> nomType = new HashMap<>();
	static {
		nomType.put(Type.FEU,"Feu Rouge");
		nomType.put(Type.ESSENCE,"Panne d'Essence");
		nomType.put(Type.CREVAISON,"Crevaison");
		nomType.put(Type.ACCIDENT,"Accident");
	};
	
	public Attaque(int n, Type t) {
		super(n, t);
	}
	
	@Override
	public String toString() {
		return nomType.get(getType());
	}
}
