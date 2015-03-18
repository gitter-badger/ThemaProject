package voorraadbeheer;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ProductenWeergeven extends Stage {
	private Voorraad vrAad = new Voorraad();

	public ProductenWeergeven(Stage owner, Voorraad voor) {
		initOwner(owner);
		this.vrAad = voor;
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 350, 500);
		
		List<Product> myList;
		myList = new ArrayList<>();
		ListView<Product> listView = new ListView<>();
		for (Product x : vrAad.geefAlleProducten()){
			myList.add(x);
		}
		ListView<String> list = new ListView<String>();
		ObservableList<Product> items = FXCollections.observableArrayList(myList);
		listView.setItems(items);
		root.setPadding(new Insets(10, 30, 30, 30));
		root.getChildren().addAll(listView);
		scene.setRoot(root);
		this.setScene(scene);
		show();
	}
}
