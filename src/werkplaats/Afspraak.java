package werkplaats;
import java.io.Serializable;
import java.util.Calendar;

public class Afspraak implements Serializable{
    private Type type;
    private Calendar datum;
    private Klant klant;
    private Monteur mon;

    public Afspraak(Type type, Calendar datum, Klant k1, Monteur mon)
    {
        this.type = type;
        this.datum = datum;
        this.klant = k1;
        this.mon = mon;
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
    
    public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public String getLabelPrint(){
    	return datum.get(Calendar.DAY_OF_MONTH) + "-" + datum.get(Calendar.MONTH);
    }
    public int getWeek(){
    	return datum.get(Calendar.WEEK_OF_MONTH);
    }
    public String toString()
    {
        return "Klant: " + klant.toString() + " \n" + klant.getAuto().getKenteken() + " \nMonteur: " + mon.getNaam() + " \n" + type ;
    }
}
