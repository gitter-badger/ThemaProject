package voorraadbeheer;

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


@SuppressWarnings("serial")
public class Voorraad implements Serializable{
	private ArrayList<Product> alleProducten = new ArrayList<Product>();

	public Voorraad() {
	}

	public void voegProductToe(Product nweP) {
		if (!heeftProduct(nweP.getArtikelNummer())) {
			alleProducten.add(nweP);
		}
		System.out.println("check");
	}

	public ArrayList<Product> geefAlleProducten() {
		return alleProducten;
	}

	public boolean heeftProduct(int artnr) {
		for (Product p : alleProducten) {
			if (p.getArtikelNummer() == artnr) {
				return true;
			}
		}
		return false;
	}
	public boolean verwijderProduct(Product p) {
		if (heeftProduct(p.getArtikelNummer())) {
			{
				alleProducten.remove(p);
				return true;
			}
		} else
			return false;
	}

	public Product zoek(int artnr) {
		for (Product p : alleProducten) {
			if (p.getArtikelNummer() == artnr) {
				return p;
			}
		}
		return null;
	}
	
	public void schrijfWeg() throws FileNotFoundException, IOException {
		try (OutputStream file = new FileOutputStream(new File("producten.obj"));
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(alleProducten);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void lees() {
		try (InputStream file = new FileInputStream("producten.obj");
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			alleProducten = (ArrayList<Product>) input.readObject();
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public String toString() {
		String s = "De voorraad is: \n\n";
		for (Product p : alleProducten) {
			s = s + p + "\n\n";
		}
		return s;
	}
}
