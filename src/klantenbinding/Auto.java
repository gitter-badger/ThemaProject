package klantenbinding;

public class Auto
{
    private String kenteken;
    private String merk;
    private String type;

    public Auto(String kenteken, String merk, String type)
    {
        this.kenteken = kenteken;
        this.merk = merk;
        this.type = type;
    }

    public String toString()
    {
        return "auto: " + merk + " " + type + " met kenteken: " + kenteken;
    }
}
