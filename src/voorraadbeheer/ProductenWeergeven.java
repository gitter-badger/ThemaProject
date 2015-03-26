package voorraadbeheer;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ProductenWeergeven extends Stage {
	private Voorraad vrAad = new Voorraad();
	private Button but1 = new Button("Bestel bij");
	private TextField bestel= new TextField(); 
	private TextField aantal= new TextField();
	private Label label = new Label("Kies product");
	private ListView<Product> listView = new ListView<>();

	public ProductenWeergeven(Stage owner, Voorraad voor) {
		initOwner(owner);
		this.vrAad = voor;
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 350, 500);

		List<Product> myList;
		myList = new ArrayList<>();

		for (Product x : vrAad.geefAlleProducten()) {
			myList.add(x);
		}

		ListView<String> list = new ListView<String>();
		ObservableList<Product> items = FXCollections.observableArrayList(myList);
		listView.setItems(items);

		but1.setOnAction(e -> {
			Product x = getSelected();
			x.setVoorraad(x.getVoorraad() + Integer.valueOf(bestel.getText()));

			// TODO: Dit is lelijk
			myList.remove(x);
			myList.clear();
			for (Product x1 : vrAad.geefAlleProducten()) {
				myList.add(x1);
			}
			listView.setItems(null);
			ObservableList<Product> itemsDup = FXCollections.observableArrayList(myList);
			listView.setItems(itemsDup);

			label.setText("Bestelling gelukt");

		});

		listView.setOnMouseClicked(e -> {
			Product x = getSelected();
			aantal.setText(String.valueOf(x.getVoorraad()));
			bestel.setText("");
		});

		root.setPadding(new Insets(10, 30, 30, 30));
		root.getChildren().addAll(listView, label, aantal, bestel, but1);
		scene.setRoot(root);
		this.setScene(scene);
		show();
	}

	public Product getSelected() {
		for (Product x : vrAad.geefAlleProducten()) {
			if (x.equals(listView.getSelectionModel().getSelectedItem())) {
				return x;
			}
		}
		return null;
	}
}
