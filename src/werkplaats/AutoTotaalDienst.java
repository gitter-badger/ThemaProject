package werkplaats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class AutoTotaalDienst implements Serializable{
	
	private ArrayList<Product> alleProducten = new ArrayList<Product>();
	private ArrayList<Monteur> alleMonteurs = new ArrayList<Monteur>();

	public AutoTotaalDienst() {
		Product p1 = new Product(1, "Schroef", "onderdeel", 300, 600, 0.10);
		alleProducten.add(p1);
		Product p2 = new Product(2, "Bout", "onderdeel", 300, 800, 0.25);
		alleProducten.add(p2);
		Monteur m1 = new Monteur("Karel Appel", "Prinsengracht 101, Amsterdam, 9900 AA", 20.50);
		alleMonteurs.add(m1);
	} 
	
	
	public ArrayList<Product> geefAlleProducten() {
		return alleProducten;
	}
	
	public ArrayList<Monteur> geefAlleMonteurs() {
		return alleMonteurs;
	}
	
	public Calendar getCalendar(int day, int month, int year) {
	    Calendar date = Calendar.getInstance();
	    date.set(Calendar.YEAR, year);
	    date.set(Calendar.MONTH, month);
	    date.set(Calendar.DAY_OF_MONTH, day);

	    return date;
	}

}

