package controller;
//Maciej Kubiniec R00144142

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import model.Invoice;
import model.PatientList;
import model.Procedure;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvoiceProcedure extends BorderPane {

	ComboBox<String> initialProc;
	ArrayList<Procedure> procList = new ArrayList<Procedure>();
	Invoice inv = new Invoice(new Date());
	String toDisplay;
	Procedure procToAdd;
	int textToInt = 0;

	public InvoiceProcedure() {
		this.setPadding(new Insets(20, 20, 20, 20));
		// populate items in observable list
		initialProc = populateList();
		

		initialProc.setOnAction(e -> {

			BorderPane bPane = new BorderPane();
			Text tb = new Text();

			try {
				procList = Controller.getInstance().loadProcedureList();

				for (Iterator<Procedure> iter = procList.iterator(); iter.hasNext();) {
					Procedure p = iter.next();
					String s = p.getProcName();
					if (initialProc.getValue().equals(s)) {
						tb.setText(p.toString());
					}
				}

			} catch (Exception exc) {
				tb.setText("issue in PRINTING DATA to screen");
			}

			bPane.setTop(tb);
			this.setCenter(bPane);
			this.setStyle("-fx-font-size: 11pt;");
		});

		VBox vbButtons = new VBox();
		Button create = new Button("CREATE/\nDELETE PROCEDURE");
		create.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button add = new Button("ADD PROCEDURE \nTO INVOICE");
		add.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button remove = new Button("REMOVE PROCEDURE \nFROM INVOICE");
		remove.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button invoice = new Button("SELECT PATIENT \nFOR INVOICE");
		invoice.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		Button reset = new Button("CLEAR INVOICE");
		reset.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");

		create.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		remove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		invoice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		reset.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20));
		vbButtons.getChildren().addAll(create, add, remove, invoice, reset);

		create.setOnAction(e -> {
			new ProcedureMaintenance();
		});

		reset.setOnAction(e -> {
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			toDisplay = "";
			inv.clearProcList();
			listView.getItems().addAll("add procedure");
			bPane.setTop(listView);
			this.setCenter(bPane);
		});

		add.setOnAction(e -> {
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			procToAdd = getProcedureFromList();
			try {

				inv.addProcedure(procToAdd);
				inv.setInvoiceAmount(inv.getInvoiceAmount() + procToAdd.getProcCost());
				toDisplay = "";
				for (int i = 0; i < inv.getInv_procList().size(); i++) {
					toDisplay += "----------\n" + inv.getInv_procList().get(i).toString();
				}

				listView.getItems().add(toDisplay);
			} catch (Exception exc) {
				listView.getItems().add("<---\t\t\t\t\t\t or create procedure --->"
						+ "\nselect a procedure from \ndropdown menue first..");
				reset();
			}

			bPane.setTop(listView);
			this.setCenter(bPane);
		});

		remove.setOnAction(e -> {
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			try {
				Procedure procToRemove = getProcedureFromList();
				inv.removeProcedure(procToRemove);
				inv.setInvoiceAmount(inv.getInvoiceAmount() - procToRemove.getProcCost());

				toDisplay = "";
				for (int i = 0; i < inv.getInv_procList().size(); i++) {
					toDisplay += "----------\n" + inv.getInv_procList().get(i).toString();
				}

			} catch (Exception exc) {
				toDisplay = "No procedures to remove from invoice";
				reset();
			}

			listView.getItems().add(toDisplay);

			bPane.setTop(listView);
			this.setCenter(bPane);
		});

		invoice.setOnAction(e -> {
			BorderPane bPane = new BorderPane();
			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			try {
				if (!toDisplay.isEmpty()) {
					inv.setInvoice(Controller.getInstance().getInvoiceNumberCount() + 1);
					listView.getItems().addAll("-------------INVOICE " + inv.getInvoice() + "-------------");
					for (int i = 0; i < inv.getInv_procList().size(); i++) {
						listView.getItems().add(inv.getInv_procList().get(i).toString() + "\n");

					}
					listView.getItems().add("\n-----------TOTAL=  " + inv.getInvoiceAmount() + "-----------");
					addInvoice(inv);
				} else {
					listView.getItems().add("no procedures added yet");
					reset();
				}

			} catch (Exception exc) {
				listView.getItems().add("something wrong in attempting to create the invoice");
				reset();
			}
			bPane.setTop(listView);
			this.setCenter(bPane);

		});
		this.setRight(vbButtons);

	}

	public void reset() {
		BorderPane bPane = new BorderPane();
		ListView<String> listView = new ListView<String>();
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		toDisplay = "";
		inv.clearProcList();
		listView.getItems().addAll("start again by addign \n procedures to invoice ");
		bPane.setTop(listView);
		this.setCenter(bPane);
	}

	public ComboBox<String> populateList() {
		ComboBox<String> procedures = new ComboBox<String>();
		try {
			ArrayList<Procedure> procList = Controller.getInstance().loadProcedureList();

			// initialProc=procedures;
			for (Iterator<Procedure> iter = procList.iterator(); iter.hasNext();) {
				Procedure p = iter.next();
				String s = p.getProcName();
				procedures.getItems().add(s);
			}
			procedures.setPromptText("Procedures");
			procedures.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;"
					+ "-fx-font-weight: bold;");
			this.setLeft(procedures);
		} catch (Exception e) {
			
		}

		return procedures;

	}

	// populate items in observable list
	public Procedure getProcedureFromList() {
		Procedure proc = null;
		try {
			// create an arraylist of procedure
			ArrayList<Procedure> procList = Controller.getInstance().loadProcedureList();
			// use iterator to go through the list
			for (Iterator<Procedure> iter = procList.iterator(); iter.hasNext();) {
				Procedure p = iter.next();
				String s = p.getProcName();
				// if on the list is clicked the same name as procedure name in
				// file
				if (initialProc.getValue().equals(s)) {
					// return the same
					return p;
				}
			}

		} catch (Exception e) {
			
		}
		return proc;
	}

	// add invoice to file, this method actually generates another window pop up
	public void addInvoice(Invoice invoice) {

		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Invoice to Patient");
		window.setMinWidth(350);
		window.setMinHeight(400);
		// ask info
		Label label = new Label();
		label.setText("PLEASE ENTER PATIENT DETAILS");

		Label labelLNAME = new Label("Surname:");
		TextField textName = new TextField();
		textName.setMaxWidth(100);

		Label labelId = new Label("ID:");
		TextField textId = new TextField();
		textId.setMaxWidth(100);

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #e7d1d1;");

		Button submit = new Button("Submit");
		submit.setStyle("-fx-font-size: 10pt;");
		// if button clicked
		submit.setOnAction(e -> {

			try {
				// ID max length 6 digits
				if (textId.getText().matches("(([0-9]){1,6})"))
					textToInt = Integer.parseInt(textId.getText());

			} catch (NumberFormatException nfe) {
				System.out.println("error with parsing");
			}
			// create a list of patient
			PatientList patlist = Controller.getInstance().loadPatientList();
			// go through list
			for (int i = 0; i < patlist.getLength(); i++) {
				// if name is the same
				if (patlist.getPatient(i).getLname().equalsIgnoreCase(textName.getText().toString()) &&
				// and id is the same
				patlist.getPatient(i).getId() == textToInt) {
					// set date
					invoice.setDate(new Date());
					// set unpaid balance
					invoice.setUnpaid(inv.getInvoiceAmount());
					patlist.getPatient(i).addInvoice(invoice);

				}
			}
			// save to file
			Controller.getInstance().saveToSerialFile(patlist, "patientlist.ser");
			// update current instance of the system
			Controller.getInstance().refresh();

			window.close();
		});
		// add all buttons and textfields
		layout.getChildren().addAll(label, labelLNAME, textName, labelId, textId, submit);

		Scene scene = new Scene(layout, 300, 275);
		window.setScene(scene);
		window.show();

	}
}
