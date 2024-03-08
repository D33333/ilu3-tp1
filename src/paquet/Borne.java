package paquet;

public class Borne extends Carte {
	private int km;
	
	public Borne(int n, int km) {
		super(n);
		this.km = km;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km+" km/h";
	}
	
	@Override
	public boolean equals(Object objet) {
		if (objet.getClass() == this.getClass()) {
			Borne b = (Borne) objet;
			return this.km == b.getKm();
		}
		return false;
	}
	
}
