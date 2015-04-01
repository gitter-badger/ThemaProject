package werkplaats;

import java.util.Calendar;

public class Afspraak
{
    private String type;
    private Calendar datum;

    public Afspraak(String type, Calendar datum)
    {
        this.type = type;
        this.datum = datum;
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
        int dag = datum.get(Calendar.DAY_OF_MONTH);
        return dag;
    }

    public String getType()
    {
        return type;
    }
}
