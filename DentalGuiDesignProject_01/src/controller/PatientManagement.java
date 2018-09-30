package controller;
//Maciej Kubiniec R00144142

import java.util.Iterator;

import model.Patient;
import model.PatientList;
import view.ConfirmBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PatientManagement extends BorderPane {
	ListView<String> lvList = new ListView<String>();
	ObservableList<String> items = FXCollections.observableArrayList();

	public PatientManagement() {

		this.setPadding(new Insets(20, 20, 20, 20));

		VBox vbox = new VBox();
		Button add = new Button("Add");
		add.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button remove = new Button("Remove");
		remove.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button display = new Button("Display All");
		display.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button report = new Button("Reports");
		report.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button save = new Button("Exit");
		save.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");

		// se5t button's size
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		remove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		display.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		save.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		report.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		add.setOnAction(e -> {
			new AddPatient();
		});

		remove.setOnAction(e -> {

			// create an observable patient list with the purpose to extrapolate
			// the selected item
			ObservableList<String> patients;
			patients = lvList.getSelectionModel().getSelectedItems();
			// assign length as length of patientlist on serial file
			int size = Controller.getInstance().loadPatientList().getLength();
			for (int i = 0; i < size; i++) {
				Patient p = Controller.getInstance().loadPatient(i);

				for (String m : patients) {
					if (p.Name().equals(m)) {
						new ConfirmBox();
						ConfirmBox.display("Remove Patient", "Do you want to remove " + m + " ?", p);

					}
				}
			}

			// populate items on center of the page for outer layout
			populateItems();
		});

		display.setOnAction(e -> {
			// create a layout for the center of the page
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			// add all patient list short details to it
			listView.getItems().add(Controller.getInstance().loadPatientList().toString());
			// add to inner layout
			bPane.setTop(listView);
			// add to outer layout
			this.setCenter(bPane);

		});

		save.setOnAction(e -> {
			Controller.getInstance().refresh();
			System.exit(0);
		});

		report.setOnAction(e -> {
			new Reports();
		});
		// enable selection of observable list for display purpose
		lvList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// populate items on center of the page for outer layout
		populateItems();
		// add listener to check what patient is selected
		lvList.getSelectionModel().selectedItemProperty().addListener(ov -> {
			// on selection create an inner layout
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			PatientList list = Controller.getInstance().loadPatientList();

			try {
				// go through the patient list
				for (Integer i : lvList.getSelectionModel().getSelectedIndices()) {
					listView.getItems().add(list.getPatient(i).toString());
				}

			} catch (Exception exc) {
				listView.getItems().add("no patients yet");
			}

			bPane.setTop(listView);
			this.setCenter(bPane);

		});
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20, 20, 10, 20));

		vbox.getChildren().addAll(add, remove, display, report, save);
		this.setStyle("-fx-font-size: 10pt;");
		this.setLeft(vbox);

	}

	public void populateItems() {
		// get patient list from serial file through the controller
		PatientList list = Controller.getInstance().loadPatientList();
		ObservableList<String> items = FXCollections.observableArrayList();
		try {
			// iterate through the list
			for (Iterator<Patient> pat = list.getPlist().iterator(); pat.hasNext();) {
				Patient p = pat.next();// get patient
				// add to observable list patient name
				items.add(p.getFname() + " " + p.getLname());
				
				
				

			}
		} catch (Exception exc) {
			items.add("no patients yet");
		}

		// add items to observable list lvListvwhich was declared as a class
		// variable
		lvList.setItems(items);
		lvList.setMaxHeight(Control.USE_PREF_SIZE);
		lvList.setPrefWidth(150.0);
		this.setRight(lvList);// add lvList to the right of my layout
	}
}
