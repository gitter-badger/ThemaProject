package werkplaats;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WerkPlaats extends Application {
	private VBox maandag, dinsdag, woensdag, donderdag, vrijdag;
	private Button t1,t2,t3,t4;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
	    GridPane root = new GridPane();
	    root.setHgap(20);
	    Scene scene = new Scene(root, 1000, 800);

		maandag = new VBox();
		t1 = new Button("Test 1");
		maandag.getChildren().add(t1);
		
		dinsdag = new VBox();
		t2 = new Button("Test 2");
		dinsdag.getChildren().add(t2);
		
		woensdag = new VBox();
		donderdag = new VBox();
		vrijdag = new VBox();
		
		GridPane.setConstraints(maandag, 1, 1);
		GridPane.setConstraints(dinsdag, 2, 1);
		root.getChildren().addAll(maandag, dinsdag);
		
		root.setPadding(new Insets(10, 25, 25, 25));
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Werkplaats");
		primaryStage.show();
	}
}
