package controller;
//Maciej Kubiniec R00144142

import database.InvoiceDB;
import database.PatientDB;
import model.Patient;
import view.ConfirmBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class PatientManagement extends BorderPane {
	
	ListView<String> lvList = new ListView<String>();
	ObservableList<String> items = FXCollections.observableArrayList();
	int textToInt = 0;
	int currentID;
	Patient p, pat, selected;
	String id, fName, lName, address, phNumber;
	PatientDB pDB = new PatientDB();
	TableView<?> patInv;
	TableView<Patient> tb = PatientDB.getInstance().displayTablePatient();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PatientManagement() {

		this.setPadding(new Insets(20, 20, 20, 20));

		VBox vbox = new VBox();
		Button add = new Button("Add");
		add.setStyle(
				"-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button remove = new Button("Remove");
		remove.setStyle(
				"-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button report = new Button("Reports");
		report.setStyle(
				"-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button quit = new Button("Quit");
		quit.setStyle(
				"-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");

		// se5t button's size
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		remove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		report.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		quit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		Clipboard clipboard = Clipboard.getSystemClipboard();
		// add listener
		tb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				Patient selectedUser = (Patient) newValue;
				ClipboardContent content = new ClipboardContent();

				content.putString("" + selectedUser.getID());
				clipboard.setContent(content);
				currentID = Integer.parseInt(clipboard.getString());

				patInv = InvoiceDB.getInstance().displayTableInvoice(currentID);

				id = "" + selectedUser.getID();
				fName = selectedUser.getfNm();
				lName = selectedUser.getlNm();
				address = selectedUser.getAd();
				phNumber = selectedUser.getPN();

				patDetails(id, fName, lName, address, phNumber, patInv);
			}
		});

		add.setOnAction(e -> {
			new AddPatient();
		});
		populateItems();

		remove.setOnAction(e -> {

			if (clipboard.equals(null))
				System.out.println("null object");
			else {

				new ConfirmBox();
				ConfirmBox.display("Remove Patient", "Remove patient " + clipboard.getString() + " ?",
						clipboard.getString());

				Controller.getInstance().refresh();

			}
		});
		report.setOnAction(e -> {
			new Reports();
		});

		quit.setOnAction(e -> System.exit(0));

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20, 5, 10, 10));

		vbox.getChildren().addAll(add, remove, report, quit);
		this.setStyle("-fx-font-size: 10pt;");
		this.setLeft(vbox);

	}

	public void patDetails(String id, String fName, String lName, String address, String phNumber, TableView<?> pdtl) {

		Label label = new Label();
		label.setText("PATIENT DETAILS ");
		label.setStyle("-fx-font-weight: bold");
		
		TextField tfID = new TextField();
		tfID.setText(id);
		tfID.setMaxWidth(100);

		TextField firstName = new TextField();
		firstName.setPromptText("First name");
		firstName.setMaxWidth(100);

		TextField lastName = new TextField();
		lastName.setPromptText("Last name");
		lastName.setMaxWidth(100);

		TextField Address = new TextField();
		Address.setPromptText("address");
		Address.setMaxWidth(100);

		TextField Phone = new TextField();
		Phone.setPromptText("phone number");
		Phone.setMaxWidth(100);

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setStyle("-fx-background-color:  TRANSPARENT;");

		Button Update = new Button("Update");
		Update.setMaxWidth(100);
		Update.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;");
		// add all buttons & textfields
		layout.getChildren().addAll(label, tfID, firstName, lastName, Address, Phone, Update, pdtl);

		this.setCenter(layout);

		try {
			// ID max length 6 digits
			if (tfID.getText().matches("(([0-9]){1,6})"))
				// assign id value to variable textToInt
				textToInt = Integer.parseInt(tfID.getText());

		} catch (NumberFormatException nfe) {// catch exception in formatting
			System.out.println("error with parsing");
		}
		Update.setOnAction(e -> {
			pDB.updatePatient(textToInt, firstName.getText().toString(), lastName.getText().toString(),
					Address.getText().toString(), Phone.getText().toString());
			// update current instance with new entry
			Controller.getInstance().refresh();
		});

	}

	public void displayPatient(String selectedUser) {
		BorderPane bpane = new BorderPane();
		ListView<String> listView = new ListView<String>();
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// add all patient list short details to it
		listView.getItems().add(selectedUser);
		
		// add to outer layout
		this.setRight(bpane);
	}
	
	public void populateItems() {

		this.setRight(tb);
		tb.setMaxWidth(325);

	}

}
