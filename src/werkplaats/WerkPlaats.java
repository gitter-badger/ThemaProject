package werkplaats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import voorraadbeheer.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import klantenbinding.Klant;
import klantenbinding.KlantenBestand;

public class WerkPlaats extends Application {
	private VBox maandag, dinsdag, woensdag, donderdag, vrijdag;
	private ListView<Afspraak> maandagL, dinsdagL, woensdagL, donderdagL, vrijdagL;
	private HBox knoppen, weken;
	private AfsprakenBestand bestand = new AfsprakenBestand();
	private Button maak, verwijder, vorige, volgende;
	private Label week;
	private int curWeek = 1;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// UI Stuff
		GridPane root = new GridPane();
		root.setHgap(20);
		root.setVgap(10);
		Scene scene = new Scene(root, 1275, 500);

		weken = new HBox(5);
		week = new Label("Week: " + curWeek);
		vorige = new Button("<<");
		volgende = new Button(">>");
		weken.getChildren().addAll(week, vorige, volgende);

		volgende.setOnAction(e -> {
			curWeek++;
			week.setText("Week: " + curWeek);
			updateDagen(maandagL, 1);
			updateDagen(dinsdagL, 2);
			updateDagen(woensdagL, 3);
			updateDagen(donderdagL, 4);
			updateDagen(vrijdagL, 5);
		});
		vorige.setOnAction(e -> {
			if (curWeek != 1) {
				curWeek--;
			}
			week.setText("Week: " + curWeek);
			updateDagen(maandagL, 1);
			updateDagen(dinsdagL, 2);
			updateDagen(woensdagL, 3);
			updateDagen(donderdagL, 4);
			updateDagen(vrijdagL, 5);
		});

		knoppen = new HBox(5);
		maak = new Button("Maak afspraak");
		verwijder = new Button("Verwijder afspraak");
		knoppen.getChildren().addAll(maak, verwijder);

		// VBox verdeling per dag
		maandag = new VBox();
		maandagL = vulDag(maandag, "Maandag", 1);

		dinsdag = new VBox();
		dinsdagL = vulDag(dinsdag, "Dinsdag", 2);

		woensdag = new VBox();
		woensdagL = vulDag(woensdag, "Woensdag", 3);

		donderdag = new VBox();
		donderdagL = vulDag(donderdag, "Donderdag", 4);

		vrijdag = new VBox();
		vrijdagL = vulDag(vrijdag, "Vrijdag", 5);

		GridPane.setConstraints(maandag, 1, 1);
		GridPane.setConstraints(dinsdag, 2, 1);
		GridPane.setConstraints(woensdag, 3, 1);
		GridPane.setConstraints(donderdag, 4, 1);
		GridPane.setConstraints(vrijdag, 5, 1);
		GridPane.setConstraints(knoppen, 5, 2);
		GridPane.setConstraints(weken, 1, 2);
		root.getChildren().addAll(maandag, dinsdag, woensdag, donderdag, vrijdag, knoppen, weken);

		root.setPadding(new Insets(10, 25, 25, 10));
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Werkplaats");
		primaryStage.show();

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					bestand.schrijfWeg();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
	}

	public void updateDagen(ListView<Afspraak> listF, int id) {
		listF.setItems(null);
		ObservableList<Afspraak> itemsDup = FXCollections.observableArrayList(bestand.geefAlleAfspraken(id, curWeek));
		listF.setItems(itemsDup);
	}

	public ListView<Afspraak> vulDag(VBox vb, String dag, int id) {
		ListView<Afspraak> x = new ListView<Afspraak>();
		x.getItems().addAll(bestand.geefAlleAfspraken(id, curWeek));
		Label l;
		//if (!bestand.geefAlleAfspraken(id, curWeek).get(0).getLabelPrint().equals("")) {
		//	l = new Label(dag + " " + bestand.geefAlleAfspraken(id, curWeek).get(0).getLabelPrint());
		//}
		//else {
			l = new Label(dag);
		//}
		vb.getChildren().addAll(l, x);
		return x;
	}
}
