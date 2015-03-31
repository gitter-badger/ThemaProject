package klantenbinding;

public class OnderhoudsBrief extends Brief
{
    public OnderhoudsBrief(Klant deKlant)
    {
        super(deKlant);
    }

    public String toString()
    {
        String brief = "";
        String adres = deKlant.getAdres();
        String achternaam = deKlant.getAchternaam();
        String auto = deKlant.getAuto();

        brief = adres + "\n\n\nGeachte heer/mevrouw " + achternaam + ",\n\n Uit uw gegevens in ons klantenbestand is gebleken dat uw " + auto + " voor het laatst op [datum] in onze garage is geweest voor onderhoud. \nDit betekent dat het onderhand weer tijd is voor het halfjaarlijkse onderhoud van uw auto.\n Bij deze willen wij u graag uitnodigen om een afspraak te maken bij Auto Totaal Diensten, zodat wij dit onderhoud voor u uit kunnen voeren.\n\nAls u binnen een week reageert krijgt u op vertoon van deze brief 10% korting op de totale prijs.\n\nWij hopen u snel weer terug te zien!\n\nMet vriendelijke groet,\n\nHenk Paladijn\n\nBedrijfsleider Auto Totaal Diensten\n";

        return brief;
    }
}
