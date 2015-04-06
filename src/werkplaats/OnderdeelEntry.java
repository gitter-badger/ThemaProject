package werkplaats;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OnderdeelEntry {
	
	public SimpleStringProperty onderdeel = new SimpleStringProperty("<Naam>");
    public SimpleIntegerProperty aantal = new SimpleIntegerProperty(); 
    public SimpleDoubleProperty kosten = new SimpleDoubleProperty();
    
    public String getOnderdeel(){
    	return onderdeel.get();
    }
    
    public Integer getAantal(){
    	return aantal.get();
    }
    
    public Double getKosten(){
    	return kosten.get();
    }
    
    public String toString(){
    	return "Onderdeel: " + onderdeel.get() + ", Aantal: " +  aantal.get() + " = " + kosten.get() + " euro\n";
    }
}
