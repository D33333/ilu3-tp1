package paquet;

import java.util.HashMap;
import java.util.Map;

import paquet.Probleme.Type;

public abstract class Probleme extends Carte {
	
	public enum Type{
		FEU("feu"), ESSENCE("essence"), CREVAISON("crevaison"),ACCIDENT("accident");
		
		private String nom;
		
		private Type(String nom) {
			this.nom = nom;
		}
		
		@Override
		public String toString() {
			return nom;
		}
	}
	
	private Type type;
	
	public Probleme(int n, Type t) {
		super(n);
		this.type = t;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object objet) {
		if (objet.getClass() == this.getClass()) {
			Probleme pb = (Probleme) objet;
			return this.type == pb.getType();
		}
		return false;
	}
}
