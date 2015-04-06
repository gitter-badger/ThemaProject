package werkplaats;

public class Monteur {
	
	private String naam;
	private String adres;
	private double uurloon;
	
	public Monteur(String naam, String adres, double uurloon) {
		super();
		this.naam = naam;
		this.adres = adres;
		this.uurloon = uurloon;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public double getUurloon() {
		return uurloon;
	}

	public void setUurloon(double uurloon) {
		this.uurloon = uurloon;
	}

	public String toString(){
		return "naam:" + naam + " Uurloon: " + uurloon;
	}

}
