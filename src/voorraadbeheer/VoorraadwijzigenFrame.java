package voorraadbeheer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class VoorraadwijzigenFrame extends Stage {

	private TextField minimumVoorraad, voorraad;
	private Label selecteer, minimumVoorraadL, voorraadL;
	private Button submit, cancel;
	private Voorraad voorraadd = new Voorraad();

	@SuppressWarnings("unchecked")
	public VoorraadwijzigenFrame(Stage owner, Voorraad voor) {
		initOwner(owner);
		this.voorraadd = voor;
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 280, 330);

		selecteer = new Label("Selecteer");
		ComboBox cb1 = new ComboBox();
		cb1.getItems().addAll(voorraadd.geefAlleProducten().toArray(new Product[0]));
		cb1.setPrefWidth(200);

		minimumVoorraadL = new Label("Minimumvoorraad");
		voorraadL = new Label("Voorraad");

		minimumVoorraad = new TextField();
		voorraad = new TextField();

		submit = new Button("Verstuur");
		submit.setOnAction(e -> {
			for (Product x : voorraadd.geefAlleProducten()) {
				if (x.equals(cb1.getValue())) {
					x.setVoorraad(Integer.valueOf(voorraad.getText()));
					x.setMinimumVoorraad(Integer.valueOf(minimumVoorraad.getText()));
					close();
				}
			}
		});

		cancel = new Button("Annuleren");
		cancel.setOnAction(e -> {
			close();
		});

		root.setPadding(new Insets(10, 30, 30, 30));
		root.getChildren().addAll(selecteer, cb1, minimumVoorraadL, minimumVoorraad, voorraadL, voorraad, submit, cancel);
		scene.setRoot(root);
		this.setScene(scene);
		show();
	}
}
