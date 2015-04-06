package werkplaats;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MonteurEntry {
	public SimpleStringProperty monteur = new SimpleStringProperty("<Naam>");
    public SimpleDoubleProperty uur = new SimpleDoubleProperty(); 
    public SimpleDoubleProperty kosten = new SimpleDoubleProperty();
	
    public String getMonteur(){
    	return monteur.get();
    }
    
    public Double getUur(){
    	return uur.get();
    }
    
    public Double getKosten(){
    	return kosten.get();
    }
    
    public String toString(){
    	return "Monteur: " + monteur.get() + ", Aantal uur: " + uur.get() + " = " + kosten.get() + " euro\n";
    }

}
