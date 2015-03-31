package klantenbinding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class KlantenBestand
{
    private ArrayList<Klant> alleKlanten;

    public KlantenBestand() //Hierin staan alle gegevens van de klanten
    {
        alleKlanten = new ArrayList<Klant>();
        ObservableList<Klant> myObservableList = FXCollections.observableList(alleKlanten); //Deze is nodig om de Klanten te kunnen inladen in de ListView

        //Er zijn 4 verschillende klanten om goed te kunnen testen of de applicatie werkt. k1 moet een herinneringsbrief krijgen, k2 onderhoud, k3 allebei en k4 geen.

        Auto a1 = new Auto("12-DG-GH", "Opel","Astra");
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

        Calendar c1 = Calendar.getInstance();
        c1.set(2014, 0, 12);
        Calendar c2 = Calendar.getInstance();
        c2.set(2014, 1, 28);
        Calendar c3 = Calendar.getInstance();
        c3.set(2014, 3, 15);
        Calendar c4 = Calendar.getInstance();
        c4.set(2014, 5, 2);
        Calendar c5 = Calendar.getInstance();
        c5.set(2014, 6, 19);
        Calendar c6 = Calendar.getInstance();
        c6.set(2014, 8, 31);
        Calendar c7 = Calendar.getInstance();
        c7.set(2014, 9, 14);
        Calendar c8 = Calendar.getInstance();
        c8.set(2014, 10, 30);
        Calendar c9 = Calendar.getInstance();
        c9.set(2015, 0, 29);

        Afspraak as1 = new Afspraak("reparatie", c1);
        Afspraak as2 = new Afspraak("onderhoud", c2);
        Afspraak as3 = new Afspraak("onderhoud", c3);
        Afspraak as4 = new Afspraak("reparatie", c4);
        Afspraak as5 = new Afspraak("onderhoud", c5);
        Afspraak as6 = new Afspraak("reparatie", c6);
        Afspraak as7 = new Afspraak("reparatie", c7);
        Afspraak as8 = new Afspraak("onderhoud", c8);
        Afspraak as9 = new Afspraak("reparatie", c9);

        k1.voegAfspraakToe(as1);
        k1.voegAfspraakToe(as2);
        k1.voegAfspraakToe(as3);
        k1.voegAfspraakToe(as4);
        k1.voegAfspraakToe(as5);
        k1.voegAfspraakToe(as6);
        k1.voegAfspraakToe(as7);
        k1.voegAfspraakToe(as8);
        k1.voegAfspraakToe(as9);

        Calendar c10 = Calendar.getInstance();
        c10.set(2014, 8, 29);

        Afspraak as10 = new Afspraak("onderhoud", c10);

        k2.voegAfspraakToe(as10);

        Calendar c11 = Calendar.getInstance();
        c11.set(2014, 2, 31);
        Calendar c12 = Calendar.getInstance();
        c12.set(2014, 4, 9);
        Calendar c13 = Calendar.getInstance();
        c13.set(2014, 5, 26);
        Calendar c14 = Calendar.getInstance();
        c14.set(2014, 7, 11);
        Calendar c15 = Calendar.getInstance();
        c15.set(2014, 8, 28);
        Calendar c16 = Calendar.getInstance();
        c16.set(2014, 9, 30);
        Calendar c17 = Calendar.getInstance();
        c17.set(2014, 11, 14);
        Calendar c18 = Calendar.getInstance();
        c18.set(2015, 0, 29);
        Calendar c19 = Calendar.getInstance();
        c19.set(2014, 8, 28);


        Afspraak as11 = new Afspraak("reparatie", c11);
        Afspraak as12 = new Afspraak("reparatie", c12);
        Afspraak as13 = new Afspraak("onderhoud", c13);
        Afspraak as14 = new Afspraak("reparatie", c14);
        Afspraak as15 = new Afspraak("onderhoud", c15);
        Afspraak as16 = new Afspraak("reparatie", c16);
        Afspraak as17 = new Afspraak("reparatie", c17);
        Afspraak as18 = new Afspraak("reparatie", c18);
        Afspraak as19 = new Afspraak("onderhoud", c19);

        k3.voegAfspraakToe(as11);
        k3.voegAfspraakToe(as12);
        k3.voegAfspraakToe(as13);
        k3.voegAfspraakToe(as14);
        k3.voegAfspraakToe(as15);
        k3.voegAfspraakToe(as16);
        k3.voegAfspraakToe(as17);
        k3.voegAfspraakToe(as18);
        k3.voegAfspraakToe(as19);

        Calendar c20 = Calendar.getInstance();
        c20.set(2014, 2, 10);
        Calendar c21 = Calendar.getInstance();
        c21.set(2014, 3, 28);
        Calendar c22 = Calendar.getInstance();
        c22.set(2014, 5, 18);
        Calendar c23 = Calendar.getInstance();
        c23.set(2014, 7, 1);
        Calendar c24 = Calendar.getInstance();
        c24.set(2014, 8, 14);
        Calendar c25 = Calendar.getInstance();
        c25.set(2014, 9, 31);
        Calendar c26 = Calendar.getInstance();
        c26.set(2014, 11, 12);
        Calendar c27 = Calendar.getInstance();
        c27.set(2014, 0, 30);
        Calendar c28 = Calendar.getInstance();
        c28.set(2014, 2, 9);

        Afspraak as20 = new Afspraak("reparatie", c20);
        Afspraak as21 = new Afspraak("onderhoud", c21);
        Afspraak as22 = new Afspraak("reparatie", c22);
        Afspraak as23 = new Afspraak("onderhoud", c23);
        Afspraak as24 = new Afspraak("reparatie", c24);
        Afspraak as25 = new Afspraak("reparatie", c25);
        Afspraak as26 = new Afspraak("onderhoud", c26);
        Afspraak as27 = new Afspraak("reparatie", c27);
        Afspraak as28 = new Afspraak("reparatie", c28);

        k4.voegAfspraakToe(as20);
        k4.voegAfspraakToe(as21);
        k4.voegAfspraakToe(as22);
        k4.voegAfspraakToe(as23);
        k4.voegAfspraakToe(as24);
        k4.voegAfspraakToe(as25);
        k4.voegAfspraakToe(as26);
        k4.voegAfspraakToe(as27);
        k4.voegAfspraakToe(as28);

    }

    public void voegKlantToe(Klant k)
    {
        alleKlanten.add(k);
    }

    public List<Klant> geefAlleKlanten() //Deze methode was nodig om de ListView te kunnen vullen
    {
        return alleKlanten;
    }

    public String toString()
    {
        String s = "";

        for(Klant k : alleKlanten)
        {
            s += k + "\n";
        }

        return s;
    }
}
