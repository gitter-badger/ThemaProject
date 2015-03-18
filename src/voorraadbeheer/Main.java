package voorraadbeheer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private Voorraad voorraad = new Voorraad();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		voorraad.lees();
		FlowPane root = new FlowPane();
		VBox paddingBox = new VBox(10);
		Scene scene = new Scene(root, 200, 200);
		Button b = new Button("Product aanmaken");
		Button b2 = new Button("Product verwijderen");
		Button b3 = new Button("Product Wijzigen");
		Button b4 = new Button("Producten weergeven");

		b.setOnAction(e -> {
			ProductToevoegen toev = new ProductToevoegen(primaryStage, voorraad);
		});

		b2.setOnAction(e -> {
			ProductVerwijderenFrame ver = new ProductVerwijderenFrame(primaryStage, voorraad);
		});

		b3.setOnAction(e -> {
			VoorraadwijzigenFrame voor = new VoorraadwijzigenFrame(primaryStage, voorraad);
		});

		b4.setOnAction(e -> {
			ProductenWeergeven voeg = new ProductenWeergeven(primaryStage, voorraad);
		});
		
		paddingBox.getChildren().addAll(b, b2, b3, b4);
		root.setPadding(new Insets(10, 25, 25, 25));
		root.getChildren().addAll(paddingBox);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SUPER APP!");
		primaryStage.show();

	}
}
