package klantenbinding;

public class HerinneringsBrief extends Brief
{
    public HerinneringsBrief(Klant deKlant)
    {
        super(deKlant);
    }

    public String toString()
    {
        String brief = "";
        String adres = deKlant.getAdres();
        String achternaam = deKlant.getAchternaam();

        brief = adres + "\n\n\nGeachte heer/mevrouw " + achternaam + ",\n\nWij van Auto Totaal Diensten vinden het jammer dat u de afgelopen tijd niet zo vaak meer bij ons langskomt als in het verleden.\nGraag zouden wij u weer terug willen zien bij onze garage, en om u opnieuw welkom te heten, willen wij u een korting aanbieden van 20% op de eerstvolgende reparatie die u door ons laat uitvoeren.\n\nWij hopen u snel weer terug te zien!\n\nMet vriendelijke groet,\n\nHenk Paladijn\n\nBedrijfleider Auto Totaal Diensten";

        return brief;

    }
}
