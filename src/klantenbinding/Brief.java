package klantenbinding;

public abstract class Brief
{
    protected Klant deKlant;

    public Brief()
    {
        deKlant = null;
    }

    public Brief(Klant deKlant)
    {
        this.deKlant = deKlant;
    }

    public abstract String toString();
}
