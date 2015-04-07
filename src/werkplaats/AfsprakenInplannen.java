package werkplaats;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

import java.util.Calendar;

public class AfsprakenInplannen extends Stage implements EventHandler<ActionEvent> {
	private Label jaar = new Label("Jaar: ");
	private Label maand = new Label("Maand: ");
	private Label dag = new Label("Dag: ");
	private Label beginTijd = new Label("Begintijd: ");
	private Label eindTijd = new Label("Eindtijd: ");
	private Label typeAfspraak = new Label("Type afspraak: ");
	private Label beschikbareMonteurs = new Label("Beschikbare monteurs:");
	private ComboBox jaarCombo = new ComboBox();
	private ComboBox maandCombo = new ComboBox();
	private ComboBox dagCombo = new ComboBox();
	private TextField beginUrenField = new TextField();
	private TextField beginMinutenField = new TextField();
	private TextField eindUrenField = new TextField();
	private TextField eindMinutenField = new TextField();
	private TextField typeAfspraakField = new TextField();
	private ListView<Monteur> monteurs = new ListView();
	private Button annuleren = new Button("Annuleren");
	private Button beschikbaarheid = new Button("Controleren");
	private Button inplannen = new Button("Inplannen");
	private AfsprakenBestand atd = new AfsprakenBestand();
	private Monteur m = new Monteur(null, null, 0);
	Afspraak a1 = new Afspraak(null, null, null, null, null, m);

	public AfsprakenInplannen(Stage owner) {
		initOwner(owner);
		
		BorderPane borderPane = new BorderPane();
		annuleren.setOnAction(this);
		inplannen.setOnAction(this);
		beschikbaarheid.setOnAction(this);
		inplannen.setDisable(true);

		monteurs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Monteur>() {
			@Override
			public void changed(ObservableValue<? extends Monteur> observable, Monteur oldValue, Monteur newValue) {
				m = newValue;
				inplannen.setDisable(false);
			}

		});

		jaar.setPrefWidth(90);
		maand.setPrefWidth(90);
		dag.setPrefWidth(100);
		beginTijd.setPrefWidth(100);
		eindTijd.setPrefWidth(100);
		typeAfspraak.setPrefWidth(100);

		jaarCombo.setPrefWidth(100);
		maandCombo.setPrefWidth(120);
		dagCombo.setPrefWidth(100);
		beginUrenField.setPrefWidth(50);
		beginMinutenField.setPrefWidth(50);
		eindUrenField.setPrefWidth(50);
		eindMinutenField.setPrefWidth(50);
		beschikbaarheid.setPrefWidth(100);
		typeAfspraakField.setPrefWidth(100);

		jaarCombo.getItems().addAll("2015", "2016", "2017");

		HBox jaarInvoer = new HBox(10);
		jaarInvoer.getChildren().addAll(jaar, jaarCombo);
		jaarInvoer.setPadding(new Insets(10, 0, 10, 0));

		maandCombo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");

		HBox maandInvoer = new HBox(10);
		maandInvoer.getChildren().addAll(maand, maandCombo);
		maandInvoer.setPadding(new Insets(10, 10, 10, 0));

		dagCombo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");

		HBox dagInvoer = new HBox();
		dagInvoer.getChildren().addAll(dag, dagCombo);
		dagInvoer.setPadding(new Insets(10, 10, 10, 0));

		HBox beginTijdInvoer = new HBox();
		beginTijdInvoer.getChildren().addAll(beginTijd, beginUrenField, beginMinutenField);
		beginTijdInvoer.setPadding(new Insets(10, 10, 10, 0));

		HBox eindTijdInvoer = new HBox();
		eindTijdInvoer.getChildren().addAll(eindTijd, eindUrenField, eindMinutenField);
		eindTijdInvoer.setPadding(new Insets(10, 10, 10, 0));

		HBox afspraakInvoer = new HBox();
		eindTijdInvoer.getChildren().addAll(typeAfspraak, typeAfspraakField);
		eindTijdInvoer.setPadding(new Insets(10, 10, 10, 0));

		VBox afspraak = new VBox();
		afspraak.getChildren().addAll(jaarInvoer, maandInvoer, dagInvoer, beginTijdInvoer, eindTijdInvoer,
				beschikbaarheid);
		afspraak.setPadding(new Insets(10, 10, 10, 10));

		VBox monteursv = new VBox();
		monteursv.getChildren().addAll(beschikbareMonteurs, monteurs);
		monteursv.setPadding(new Insets(10, 10, 10, 10));

		HBox buttons = new HBox(420);
		buttons.getChildren().addAll(annuleren, inplannen);
		buttons.setPadding(new Insets(10, 0, 10, 10));

		borderPane.setLeft(afspraak);
		borderPane.setRight(monteursv);
		borderPane.setBottom(buttons);

		Scene scene = new Scene(borderPane, 700, 500);

		setTitle("Afspraken Inplannen");
		setScene(scene);
		show();
	}

	public void handle(ActionEvent event) {

		if (event.getSource() == beschikbaarheid) {

			if (jaarCombo.getValue() != null && maandCombo.getValue() != null && dagCombo.getValue() != null
					&& beginUrenField.getText() != null && beginMinutenField.getText() != null
					&& eindUrenField.getText() != null && eindMinutenField.getText() != null
					&& typeAfspraakField != null) {
				int jaar = Integer.valueOf((String) jaarCombo.getValue());
				int maand = Integer.valueOf((String) maandCombo.getValue());
				int dag = Integer.valueOf((String) dagCombo.getValue());
				int beginUren = Integer.valueOf(beginUrenField.getText());
				int beginMinuten = Integer.valueOf(beginMinutenField.getText());
				int eindUren = Integer.valueOf(eindUrenField.getText());
				int eindMinuten = Integer.valueOf(eindMinutenField.getText());
				String afspraakType = typeAfspraakField.getText();

				Calendar afspraakBegin = Calendar.getInstance();
				Calendar afspraakEind = Calendar.getInstance();

				afspraakBegin.set(jaar, maand, dag, beginUren, beginMinuten);
				afspraakEind.set(jaar, maand, dag, eindUren, eindMinuten);
				
				Afspraak a = new Afspraak(Type.ONDERHOUD, afspraakBegin, afspraakBegin, afspraakEind, new Klant("Jan", "Petersen", "Moeflonstraat", 22, "4355AB", "Amsterdam", new Auto("12-DG-GH", "Opel","Astra")), new Monteur("Martijn", "Boontjesland 82", 18.50));
				a1 = a;

				ObservableList<Monteur> monteursList = FXCollections.observableArrayList();

				for (Monteur m : atd.geefAlleMonteurs()) {
					if (m.isTijdBeschikbaar(a)) {
						monteursList.add(m);
					}
				}
				monteurs.setItems(monteursList);
			}
		}

		if (event.getSource() == inplannen) {
			atd.voegAfspraakToe(a1);
			System.out.println(m.afspraken());
		}
	}
}