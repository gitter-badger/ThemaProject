package werkplaats;

import java.io.Serializable;
import java.util.Calendar;

public class Afspraak implements Serializable{
    private Type type;
    private Calendar datum;
    private Klant klant;

    public Afspraak(Type type, Calendar datum, Klant k1)
    {
        this.type = type;
        this.datum = datum;
        this.klant = k1;
    }

    public Calendar getDatum()
    {
        return datum;
    }

    public int getJaar()
    {
        int jaar = datum.get(Calendar.YEAR);
        return jaar;
    }

    public int getMaand()
    {
        int maand = 1 + datum.get(Calendar.MONTH);
        return maand;
    }

    public int getDag()
    {
        return datum.get(Calendar.DAY_OF_MONTH);
    }

    public Type getType()
    {
        return type;
    }
    public String toString()
    {
        return klant.toString() + " \n" + type + " " + klant.getAuto().getKenteken();
    }
}
