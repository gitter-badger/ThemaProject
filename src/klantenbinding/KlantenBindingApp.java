package klantenbinding;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

public class KlantenBindingApp extends Application implements EventHandler<ActionEvent>
{
    private Label klantenBestand = new Label("Klantenbestand:");
    private Label gegevens = new Label("Gegevens:");
    private Label klantGegevens = new Label("");
    private Label typeBrief = new Label("");
    @FXML
    private Klant k = new Klant(null, null, null, 0, null, null, null);
    private ListView<Klant> klanten = new ListView<Klant>();
    private KlantenBestand bestand = new KlantenBestand();
    private Button maakBrief = new Button("Maak Brief");
    private Button annuleren = new Button("Annuleren");

    public void start(Stage primaryStage)
    {
        BorderPane borderPane = new BorderPane();

        maakBrief.setOnAction(this);
        annuleren.setOnAction(this);

        VBox klantBestand = new VBox();
        klantBestand.getChildren().addAll(klantenBestand, klanten, annuleren);
        klantBestand.setPadding(new Insets(10, 10, 10, 10));

        VBox ggevens = new VBox();
        ggevens.getChildren().addAll(gegevens, klantGegevens, typeBrief, maakBrief);
        ggevens.setPadding(new Insets(10, 10, 10, 10));

        borderPane.setLeft(klantBestand);
        borderPane.setCenter(ggevens);

        klanten.getItems().addAll(bestand.geefAlleKlanten());

        klanten.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Klant>() { //een changelistener die kijkt welk item in de ListView is geselecteerd
            @Override
            public void changed(ObservableValue<? extends Klant> observable,
                                Klant oldValue, Klant newValue) {

                k = newValue;

                klantGegevens.setText(newValue.getAdres() + "\n\n");

                if(!newValue.herinnering() && !newValue.onderhoud())
                {
                    typeBrief.setText("Geen bijzonderheden\n\n");
                    maakBrief.setDisable(true); //Brief kan alleen gemaakt worden als dit nodig is, anders niet
                }

                if(newValue.herinnering() && newValue.onderhoud())
                {
                    typeBrief.setText("Klant moet een herinneringsbrief krijgen!\nKlant moet een onderhoudsbrief krijgen!\n\n");
                    maakBrief.setDisable(false);
                }

                else if(newValue.herinnering())
                {
                    typeBrief.setText("Klant moet een herinneringsbrief krijgen!\n\n");
                    maakBrief.setDisable(false);
                }

                else if(newValue.onderhoud())
                {
                    typeBrief.setText("Klant moet een onderhoudsbrief krijgen!\n\n");
                    maakBrief.setDisable(false);
                }


            }
        });

        Scene scene = new Scene(borderPane, 600, 400);

        primaryStage.setTitle("Klantenbinding");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void handle(ActionEvent event)
    {
        if(event.getSource() == maakBrief ) //Brieven worden aangemaakt en uitgeprint
        {

            if(k.onderhoud() && k.herinnering())
            {
                Brief b1 = new OnderhoudsBrief(k);
                Brief b2 = new HerinneringsBrief(k);
                System.out.println(b1);
                System.out.println(b2);
            }

            else if(k.onderhoud())
            {
                Brief b1 = new OnderhoudsBrief(k);
                System.out.println(b1);
            }

            else if(k.herinnering())
            {
                Brief b1 = new HerinneringsBrief(k);
                System.out.println(b1);
            }

            else
            {
                System.out.println("Maak brief werkt niet");
            }
        }
    }
    public static void main(String[] args) {
		Application.launch(args);
	}
}
