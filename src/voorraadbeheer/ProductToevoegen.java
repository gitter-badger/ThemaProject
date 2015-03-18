package voorraadbeheer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ProductToevoegen extends Stage {
	private TextField artikelNummer, naam, type, minimumVoorraad, voorraad, prijs;
	private Label artikelNummerL, naamL, typeL, minimumVoorraadL, voorraadL, prijsL;
	private Button submit, cancel;
	private Voorraad vrAad = new Voorraad();

	public ProductToevoegen(Stage owner, Voorraad voor) {
		initOwner(owner);
		this.vrAad = voor;
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 225, 330);

		artikelNummerL = new Label("Artikelnummer");
		naamL = new Label("Naam");
		typeL = new Label("Type");
		minimumVoorraadL = new Label("Minimumvoorraad");
		voorraadL = new Label("Voorraad");
		prijsL = new Label("Prijs");

		artikelNummer = new TextField();
		naam = new TextField();
		type = new TextField();
		minimumVoorraad = new TextField();
		voorraad = new TextField();
		prijs = new TextField();

		submit = new Button("Verstuur");
		submit.setOnAction(e -> {
			vrAad.voegProductToe(new Product(Integer.valueOf(artikelNummer.getText()), naam.getText(), type.getText(), Integer.valueOf(minimumVoorraad.getText()), Integer.valueOf(voorraad.getText()), Double.valueOf(prijs.getText())));
			try {
				vrAad.schrijfWeg();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			close();
		});
		
		cancel = new Button("Annuleren");
		cancel.setOnAction(e -> {
			close();
		});

		root.setPadding(new Insets(10, 30, 30, 30));
		root.getChildren().addAll(artikelNummerL, artikelNummer, naamL, naam, typeL, type, minimumVoorraadL, minimumVoorraad, voorraadL, voorraad, prijsL, prijs, submit, cancel);
		scene.setRoot(root);
		this.setScene(scene);
		show();
	}
}
