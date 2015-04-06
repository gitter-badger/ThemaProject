package werkplaats;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

public class AfsprakenInplannen extends Stage implements EventHandler<ActionEvent>
{
    private Label jaar = new Label("Jaar: ");
    private Label maand = new Label("Maand: ");
    private Label dag = new Label("Dag: ");
    private Label beginTijd = new Label("Begintijd: ");
    private Label eindTijd = new Label("Eindtijd: ");
    private ComboBox jaarCombo = new ComboBox();
    private ComboBox maandCombo = new ComboBox();
    private ComboBox dagCombo = new ComboBox();
    private TextField beginTijdField = new TextField();
    private TextField eindTijdField = new TextField();
    private ListView<Monteur> monteurs = new ListView();
    private Button annuleren = new Button("Annuleren");
    private Button inplannen = new Button("Inplannen");
    private AutoTotaalDienst atd = new AutoTotaalDienst();

    public AfsprakenInplannen(Stage owner)
    {
    	initOwner(owner);
        BorderPane borderPane = new BorderPane();

        annuleren.setOnAction(this);
        inplannen.setOnAction(this);

        HBox jaarInvoer = new HBox();
        jaarInvoer.getChildren().addAll(jaar, jaarCombo);
        jaarInvoer.setPadding(new Insets(10, 10, 10, 10));

        HBox maandInvoer = new HBox();
        maandInvoer.getChildren().addAll(maand, maandCombo);
        maandInvoer.setPadding(new Insets(10, 10, 10, 10));

        HBox dagInvoer = new HBox();
        dagInvoer.getChildren().addAll(dag, dagCombo);
        dagInvoer.setPadding(new Insets(10, 10, 10, 10));

        HBox beginTijdInvoer = new HBox();
        beginTijdInvoer.getChildren().addAll(beginTijd, beginTijdField);
        beginTijdInvoer.setPadding(new Insets(10, 10, 10, 10));

        HBox eindTijdInvoer = new HBox();
        eindTijdInvoer.getChildren().addAll(eindTijd, eindTijdField);
        eindTijdInvoer.setPadding(new Insets(10, 10, 10, 10));

        VBox afspraak = new VBox();
        afspraak.getChildren().addAll(jaarInvoer, maandInvoer, dagInvoer, beginTijdInvoer, eindTijdInvoer);
        afspraak.setPadding(new Insets(10, 10, 10, 10));

        VBox monteursv = new VBox();
        monteursv.getChildren().add(monteurs);
        monteursv.setPadding(new Insets(10, 10, 10, 10));

        HBox buttons = new HBox();
        buttons.getChildren().addAll(annuleren, inplannen);
        buttons.setPadding(new Insets(10, 10, 10, 10));

        borderPane.setLeft(afspraak);
        borderPane.setRight(monteursv);
        borderPane.setBottom(buttons);

        Scene scene = new Scene(borderPane, 600, 600);

        setTitle("Afspraken Inplannen");
        setScene(scene);
        show();
    }

    public void handle(ActionEvent event)
    {

    }
}