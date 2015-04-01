package werkplaats;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import klantenbinding.Klant;
import klantenbinding.KlantenBestand;

public class WerkPlaats extends Application {
	private VBox maandag, dinsdag, woensdag, donderdag, vrijdag;
	private AfsprakenBestand bestand = new AfsprakenBestand();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		// UI Stuff
		GridPane root = new GridPane();
		root.setHgap(20);
		Scene scene = new Scene(root, 1275, 500);

		// VBox verdeling per dag
		maandag = new VBox();
		vulDag(maandag, "Maandag", 1);

		dinsdag = new VBox();
		vulDag(dinsdag, "Dinsdag", 2);

		woensdag = new VBox();
		vulDag(woensdag, "Woensdag", 3);

		donderdag = new VBox();
		vulDag(donderdag, "Donderdag", 4);

		vrijdag = new VBox();
		vulDag(vrijdag, "Vrijdag", 5);

		GridPane.setConstraints(maandag, 1, 1);
		GridPane.setConstraints(dinsdag, 2, 1);
		GridPane.setConstraints(woensdag, 3, 1);
		GridPane.setConstraints(donderdag, 4, 1);
		GridPane.setConstraints(vrijdag, 5, 1);
		root.getChildren().addAll(maandag, dinsdag, woensdag, donderdag, vrijdag);

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

	public boolean vulDag(VBox vb, String dag, int id) {
		Label l = new Label(dag);
		ListView<Afspraak> x = new ListView<Afspraak>();
		x.getItems().addAll(bestand.geefAlleAfspraken(id));
		vb.getChildren().addAll(l, x);
		return true;
	}
}
