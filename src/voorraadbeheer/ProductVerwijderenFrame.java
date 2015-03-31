package voorraadbeheer; 

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ProductVerwijderenFrame extends Stage {

	//private Voorraad deVoorraad;
	private Voorraad voorraad = new Voorraad();

	public ProductVerwijderenFrame(Stage primaryStage, Voorraad voor) {
		this.voorraad = voor;
		Label l1 = new Label("Verwijder de producten uit voorraad");
		Label l3 = new Label("alle producten:");
		
		ComboBox cb1 = new ComboBox();
		Label empty = new Label("");
		cb1.getItems().addAll(voorraad.geefAlleProducten().toArray(new Product[0]));
		

		Button b1 = new Button();
		b1.setText("Annulleer");
		Button b2 = new Button();
		b2.setText("verwijder");
		FlowPane root = new FlowPane();
		root.setVgap(8);
		root.setHgap(4);
		l1.setPrefWidth(350);
		l3.setPrefWidth(100);
		cb1.setPrefWidth(200);
		empty.setPrefWidth(150);

		root.setPadding(new Insets(5, 5, 5, 5));
		root.getChildren().addAll(l1,l3,cb1,empty,b1,b2);
		
		Scene scene = new Scene(root, 350, 275);
		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {				
				close();
			}
		});

		b2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (cb1.getValue() != null) {
					Product p = (Product) cb1.getValue();
					voorraad.verwijderProduct(p);
					System.out.println("Het verwijderen is gelukt");
					cb1.getSelectionModel().clearSelection();

				}
			}
		});

		setTitle("Verwijder Product");
		setResizable(false);
		sizeToScene();
		setScene(scene);
		show();
	}
}
