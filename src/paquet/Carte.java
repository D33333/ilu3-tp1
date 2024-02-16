package paquet;

public abstract class Carte {
	private int nombre;
	
	protected Carte(int n) {
		this.nombre = n;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public abstract String toString();
}
