package paquet;

public abstract class Limite extends Carte {

	protected Limite(int n) {
		super(n);
	}
	
	@Override
	public boolean equals(Object objet) {
		return objet.getClass() == this.getClass();
	}
}
