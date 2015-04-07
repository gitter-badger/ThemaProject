package werkplaats;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import voorraadbeheer.Product;

public class FactuurOpstellenFrame extends Stage implements EventHandler<ActionEvent> {
	private Label klussenL = new Label("Selecteer klus:");
	private Label monteursL = new Label("Selecteer monteur:");
	private ComboBox monteurscb = new ComboBox();
	private Label aantaluren = new Label("Aantal uren gewerkt:");
	private TextField aantalurenTF = new TextField();
	private Label soortonderdeelL = new Label("Gebruikte onderdeel:");
	private ComboBox soortonderdeelcb = new ComboBox();
	private Label aantalonderdelenL = new Label("aantal onderdelen:");
	private TextField aantalonderdelenTF = new TextField();
	private Button toevoegenmonteur = new Button("Toevoegen");
	private Button toevoegenonderdeel = new Button("Toevoegen");
	private Button berekenuur = new Button("Bereken");
	private ComboBox klussencb = new ComboBox();
	private Button verwijderen1 = new Button("verwijder");
	private Button verwijderen2 = new Button("verwijder");
	private Button print = new Button("Print   ");
	private Button annuleer = new Button("Annuleer");
	private IntegerProperty index = new SimpleIntegerProperty();
	private Afspraak gekozenAfspraak;
	private double totaalprijs = 0.00;

	private AfsprakenBestand dienst = new AfsprakenBestand();
	private AfsprakenBestand deAfspraak = new AfsprakenBestand();

	private TableView monteurTV = new TableView();
	private TableView onderdeelTV = new TableView();

	private ObservableList<MonteurEntry> monteurdata = FXCollections.observableArrayList();
	private ObservableList<OnderdeelEntry> onderdeeldata = FXCollections.observableArrayList();

	public FactuurOpstellenFrame(Stage owner) {
		initOwner(owner);
		BorderPane borderPane = new BorderPane();

		TableColumn monteurCol = new TableColumn("Monteur");
		TableColumn aantaluurCol = new TableColumn("Uur");
		TableColumn mkostenCol = new TableColumn("Kosten");

		monteurCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, String>("Monteur"));
		aantaluurCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, Double>("Uur"));
		mkostenCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, Double>("Kosten"));
		monteurTV.setItems(monteurdata);

		monteurTV.getColumns().addAll(monteurCol, aantaluurCol, mkostenCol);

		TableColumn onderdeelCol = new TableColumn("Onderdeel");
		TableColumn aantalCol = new TableColumn("Aantal");
		TableColumn okostenCol = new TableColumn("Kosten");

		onderdeelCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, String>("Onderdeel"));
		aantalCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, Integer>("Aantal"));
		okostenCol.setCellValueFactory(new PropertyValueFactory<MonteurEntry, Double>("Kosten"));
		onderdeelTV.setItems(onderdeeldata);

		onderdeelTV.getColumns().addAll(onderdeelCol, aantalCol, okostenCol);

		VBox klussenbox = new VBox();
		klussencb.setPrefWidth(200);
		klussencb.getItems().addAll(deAfspraak.geefAlleAfsprakenn().toArray(new Afspraak[0]));
		monteurscb.setPrefWidth(200);
		monteurscb.getItems().addAll(deAfspraak.geefAlleMonteurs().toArray(new Monteur[0]));

		Label klussenmelding = new Label("");
		klussenmelding.setTextFill(Color.RED);
		klussenbox.getChildren().addAll(klussenL, klussencb, monteursL, monteurscb, aantaluren, aantalurenTF, toevoegenmonteur, klussenmelding,
				monteurTV, verwijderen1);
		klussenbox.setPadding(new Insets(10, 10, 10, 10));

		VBox onderdelenbox = new VBox();
		soortonderdeelcb.setPrefWidth(200);
		soortonderdeelcb.getItems().addAll(dienst.geefAlleProducten().toArray(new Product[0]));
		Label onderdeelmelding = new Label("");
		onderdeelmelding.setTextFill(Color.RED);

		onderdelenbox.getChildren().addAll(soortonderdeelL, soortonderdeelcb, aantalonderdelenL, aantalonderdelenTF, toevoegenonderdeel,
				onderdeelmelding, onderdeelTV, verwijderen2);
		onderdelenbox.setPadding(new Insets(10, 10, 10, 10));

		VBox prijsbox = new VBox();
		Label totaalprijsL = new Label("De Totaalprijs: ");
		HBox prijsHBox = new HBox();

		print.setPadding(new Insets(5, 5, 5, 5));
		prijsHBox.getChildren().addAll(annuleer, print);
		prijsbox.getChildren().addAll(totaalprijsL, prijsHBox);
		prijsbox.setPadding(new Insets(10, 10, 10, 10));

		borderPane.setLeft(klussenbox);
		borderPane.setCenter(onderdelenbox);
		borderPane.setBottom(prijsbox);

		toevoegenmonteur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (aantalurenTF.getText().isEmpty()) {
					klussenmelding.setText("Voer het tekstveld in");
				} else {
					for (Monteur m : deAfspraak.geefAlleMonteurs()) {

						if (m.equals(monteurscb.getValue())) {
							MonteurEntry monteurentry = new MonteurEntry();
							monteurentry.monteur.set(m.getNaam());
							monteurentry.uur.set(Double.parseDouble(aantalurenTF.getText()));
							monteurentry.kosten.set(Double.parseDouble(aantalurenTF.getText()) * m.getUurloon());
							monteurscb.getSelectionModel().clearSelection();
							totaalprijs = totaalprijs + monteurentry.getKosten();
							totaalprijsL.setText("De Totaalprijs: " + totaalprijs);
							monteurdata.add(monteurentry);
						}
					}
				}

			}
		});

		toevoegenonderdeel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (aantalonderdelenTF.getText().isEmpty()) {
					onderdeelmelding.setText("Voer het aantal onderdelen in");
				}
				if (!aantalonderdelenTF.getText().isEmpty()) {
					for (voorraadbeheer.Product p : dienst.geefAlleProducten()) {
						if (p.equals(soortonderdeelcb.getValue())) {
							OnderdeelEntry onderdeelentry = new OnderdeelEntry();
							if (Integer.parseInt(aantalonderdelenTF.getText()) < p.getVoorraad()
									&& Integer.parseInt(aantalonderdelenTF.getText()) >= 0 && aantalonderdelenTF.getText() != null) {
								onderdeelentry.onderdeel.set(p.getNaam());
								onderdeelentry.aantal.set(Integer.parseInt(aantalonderdelenTF.getText()));
								onderdeelentry.kosten.set((double) Integer.parseInt(aantalonderdelenTF.getText()) * p.getPrijs());
								p.setVoorraad(p.getVoorraad() - Integer.valueOf(aantalonderdelenTF.getText()));
								soortonderdeelcb.getSelectionModel().clearSelection();
								totaalprijs = totaalprijs + onderdeelentry.getKosten();
								totaalprijsL.setText("De Totaalprijs: " + totaalprijs);
								onderdeelmelding.setText("");
								onderdeeldata.add(onderdeelentry);
							}
						} else {
							onderdeelmelding.setText("Zoveel onderdelen zitten er niet in de voorraad");
							soortonderdeelcb.getSelectionModel().clearSelection();
						}
					}
				}

			}
		});

		verwijderen1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int x = index.get();
				double data = monteurdata.get(x).getKosten();
				totaalprijs = totaalprijs - data;
				totaalprijsL.setText("De Totaalprijs: " + totaalprijs);

				monteurdata.remove(index.get());

			}
		});

		verwijderen2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int x = index.get();
				double data = onderdeeldata.get(x).getKosten();
				totaalprijs = totaalprijs - data;
				totaalprijsL.setText("De Totaalprijs: " + totaalprijs);

				onderdeeldata.remove(index.get());
			}
		});

		print.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (Afspraak a : deAfspraak.geefAlleAfsprakenn()) {
					if (klussencb.getValue() == a) {
						System.out.println(a.getKlant() + "\n\nBeste meneer/mevrouw " + a.getKlant().getAchternaam()
								+ ",\n\nDit is het factuur van de reparatie,\nGraag binnen 2 weken overmaken naar NL99INGB0001234567\n");

						for (int i = 0; i < monteurdata.size(); i++) {
							System.out.print((monteurdata.get(i)).toString());
						}
						System.out.println("");
						for (int i = 0; i < onderdeeldata.size(); i++) {
							System.out.print((onderdeeldata.get(i)).toString());
						}
						System.out.print("\nTotaalprijs: " + totaalprijs + " euro\n\nMet vriendelijke groet,\n\nHenk Paladijn");

					}
				}
				close();
			}
		});

		annuleer.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				close();
			}
		});

		Scene scene = new Scene(borderPane, 600, 400);

		setTitle("Factuur opstellen");
		setScene(scene);
		show();

	}
	public void setAfspraak(Afspraak af){
		gekozenAfspraak = af;
	}

	public void handle(ActionEvent event) {

	}
}
