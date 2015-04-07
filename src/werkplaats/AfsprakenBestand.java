package werkplaats;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import voorraadbeheer.Voorraad;

public class AfsprakenBestand implements Serializable {
	private ArrayList<Afspraak> alleAfspraken = new ArrayList<Afspraak>();
	private Locale usersLocale = Locale.getDefault();
	private ArrayList<Monteur> alleMonteurs = new ArrayList<Monteur>();
	private ArrayList<Klant> alleKlanten = new ArrayList<Klant>();
	// Kopeling met Voorraadbeheer
	private Voorraad voorraad = new Voorraad();
	private ArrayList<voorraadbeheer.Product> alleProducten = voorraad.geefAlleProducten();

	public AfsprakenBestand() {
		//lees();
		
		voorraad.lees();
		// Voorbeelden voor nu TODO: Standaard save maken
		Monteur mon1 = new Monteur("Martijn", "Boontjesland 82", 18.50);
		Monteur mon2 = new Monteur("Harry", "Bariumlaan 91", 20.50);
		Monteur mon3 = new Monteur("Cara", "Flapstraat 132", 15.50);
		alleMonteurs.add(mon1);
		alleMonteurs.add(mon2);
		alleMonteurs.add(mon3);

		Auto a1 = new Auto("12-DG-GH", "Opel", "Astra");
		Auto a2 = new Auto("53-LL-PO", "Fiat", "Panda");
		Auto a3 = new Auto("87-LK-LK", "Nissan", "Primera");
		Auto a4 = new Auto("33-DF-GH", "Nissan", "Micra");

		Klant k1 = new Klant("Jan", "Petersen", "Moeflonstraat", 22, "4355AB", "Amsterdam", a1);
		Klant k2 = new Klant("Klaas", "Jansen", "Oosterweg", 56, "7567LK", "Leusden", a2);
		Klant k3 = new Klant("Greet", "van den Berg", "Klaverpad", 67, "5545DD", "Almere", a3);
		Klant k4 = new Klant("Maureen", "van der Ploeg", "Koolzaadlaan", 324, "6456CV", "Den Dolder", a4);
		alleKlanten.add(k1);
		alleKlanten.add(k2);
		alleKlanten.add(k3);
		alleKlanten.add(k4);

		Calendar c1 = Calendar.getInstance(usersLocale);
		c1.set(2014, 1, 1);
		Calendar c2 = Calendar.getInstance(usersLocale);
		c2.set(2014, 1, 1);
		Calendar c3 = Calendar.getInstance(usersLocale);
		c3.set(2014, 1, 2);
		Calendar c4 = Calendar.getInstance(usersLocale);
		c4.set(2014, 1, 3);
		Calendar c5 = Calendar.getInstance(usersLocale);
		c5.set(2014, 1, 3);
		Calendar c6 = Calendar.getInstance(usersLocale);
		c6.set(2014, 1, 4);
		Calendar c7 = Calendar.getInstance(usersLocale);
		c7.set(2014, 1, 4);
		Calendar c8 = Calendar.getInstance(usersLocale);
		c8.set(2014, 1, 5);
		Calendar c9 = Calendar.getInstance(usersLocale);
		c9.set(2015, 1, 6);
		Afspraak as1 = new Afspraak(Type.ONDERHOUD, c1, null, null, k1, mon1);
		Afspraak as2 = new Afspraak(Type.ONDERHOUD, c2, null, null, k2, mon1);
		Afspraak as3 = new Afspraak(Type.ONDERHOUD, c3, null, null, k3, mon2);
		Afspraak as4 = new Afspraak(Type.REPERATIE, c4, null, null, k4, mon1);
		Afspraak as5 = new Afspraak(Type.ONDERHOUD, c5, null, null, k2, mon3);
		Afspraak as6 = new Afspraak(Type.REPERATIE, c6, null, null, k1, mon2);
		Afspraak as7 = new Afspraak(Type.REPERATIE, c7, null, null, k4, mon1);
		Afspraak as8 = new Afspraak(Type.ONDERHOUD, c8, null, null, k4, mon2);
		Afspraak as9 = new Afspraak(Type.REPERATIE, c9, null, null, k2, mon3);
		alleAfspraken.add(as1);
		alleAfspraken.add(as2);
		alleAfspraken.add(as3);
		alleAfspraken.add(as4);
		alleAfspraken.add(as5);
		alleAfspraken.add(as6);
		alleAfspraken.add(as7);
		alleAfspraken.add(as8);
		alleAfspraken.add(as9);

	}

	public List<Afspraak> geefAlleAfspraken(int i, int curWeek) {
		ArrayList<Afspraak> x = new ArrayList<Afspraak>();
		for (Afspraak af : alleAfspraken) {
			if (af.getDag() == i && af.getWeek() == curWeek) {
				x.add(af);
			}
		}
		return x;
	}

	public ArrayList<Afspraak> geefAlleAfsprakenn() {
		return alleAfspraken;
	}

	public ArrayList<Monteur> geefAlleMonteurs() {
		return alleMonteurs;
	}

	public ArrayList<Klant> geefAlleKlanten() {
		return alleKlanten;
	}

	public ArrayList<voorraadbeheer.Product> geefAlleProducten() {
		return alleProducten;
	}

	public String toString() {
		String s = "";

		for (Afspraak k : alleAfspraken) {
			s += k + "\n";
		}

		return s;
	}

	public void voegAfspraakToe(Afspraak af) {
		alleAfspraken.add(af);
	}

	public void verwijderAfspraak(Afspraak af) {
		alleAfspraken.remove(af);
	}

	/**
	 * Schrijft alleAfspraken object weg
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void schrijfWeg() throws FileNotFoundException, IOException {
		try (OutputStream file = new FileOutputStream(new File("afspraken.obj"));
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(alleAfspraken);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * Haalt alleAfspraken object op en zet deze in local alleAfspraken
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void lees() {
		try (InputStream file = new FileInputStream("afspraken.obj");
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			alleAfspraken = (ArrayList<Afspraak>) input.readObject();
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
