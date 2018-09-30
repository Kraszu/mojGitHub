package controller;
//Maciej Kubiniec R00144142

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import database.InvoiceDB;
import database.InvoiceProcDB;
import database.PatientDB;
import database.ProcedureDB;
import model.Invoice;
import model.Patient;
import model.Procedure;
import view.AlertBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvoiceProcedure extends BorderPane {

	ComboBox<String> initialProc;

	Invoice inv = new Invoice(new Date());
	String toDisplay;
	Procedure procToAdd;
	int textToInt = 0;
	ProcedureDB procDB = new ProcedureDB();
	ArrayList<Procedure> procList = procDB.selectProcedures();
	ArrayList<Patient> patList = PatientDB.getInstance().selectPatients();
	ComboBox<String> initialPatient = populateList();
	String selected = "";
	Patient selectedPatient;

	public InvoiceProcedure() {
		this.setPadding(new Insets(20, 20, 20, 20));
		// populate items in observable list
		initialProc = procListPop();

		initialProc.setOnAction(e -> {

			BorderPane bPane = new BorderPane();
			Text tb = new Text();

			try {

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
		create.setStyle(
				"-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button add = new Button("ADD PROCEDURE \nTO INVOICE");
		add.setStyle(
				"-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button remove = new Button("REMOVE PROCEDURE \nFROM INVOICE");
		remove.setStyle(
				"-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button invoice = new Button("SELECT PATIENT \nFOR INVOICE");
		invoice.setStyle(
				"-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");
		Button reset = new Button("CLEAR INVOICE");
		reset.setStyle(
				"-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");

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

			int invoiceProcID = 0;
			int invoiceNumber = 0;

			try {
				if (!toDisplay.isEmpty()) {

					// check if table invoice is empty to set id
					if (!InvoiceDB.getInstance().selectInvoices().isEmpty()) {
						invoiceNumber = InvoiceDB.getInstance().selectInvoices().size() + 1;
					} else
						invoiceNumber = 1;

					// check if table invoiceProcedure is empty to set id
					if (!InvoiceProcDB.getInstance().selectInvoiceProcs().isEmpty()) {
						// lastInvProc = (InvoiceProced)
						// InvoiceProcDB.getInstance().selectInvoiceProcs().get(InvoiceProcDB.getInstance().selectInvoiceProcs().size()-1);
						invoiceProcID = InvoiceProcDB.getInstance().selectInvoiceProcs().size() + 1;
					} else
						invoiceProcID = 1;

					inv.setInvoice(invoiceNumber);
					listView.getItems().addAll("-------------INVOICE " + inv.getInvoice() + "-------------");
					for (int i = 0; i < inv.getInv_procList().size(); i++) {
						listView.getItems().add(inv.getInv_procList().get(i).toString() + "\n");
						// invoi.setInvoiceAmount(invoi.getInv_procList().get(i).getProcCost());

						InvoiceProcDB.getInstance().addInvoiceProc(invoiceProcID + i, inv.getInvoice(),
								inv.getInv_procList().get(i).getPrn());
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

	public ComboBox<String> procListPop() {
		ComboBox<String> procedures = new ComboBox<String>();
		try {

			// initialProc=procedures;
			for (Iterator<Procedure> iter = procList.iterator(); iter.hasNext();) {
				Procedure p = iter.next();
				String s = p.getProcName();
				procedures.getItems().add(s);
			}
			procedures.setPromptText("Procedures");
			procedures.setStyle("-fx-font-size: 11pt;-fx-background-color: #0066cc;" + "-fx-font-weight: bold;");
			this.setLeft(procedures);
		} catch (Exception e) {

		}

		return procedures;

	}

	// populate items in observable list
	public Procedure getProcedureFromList() {
		Procedure proc = null;
		try {

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

	public ComboBox<String> populateList() {
		ComboBox<String> patients = new ComboBox<String>();
		try {
			for (Iterator<Patient> iter = patList.iterator(); iter.hasNext();) {
				Patient i = iter.next();
				String s = i.getLname();
				patients.getItems().add(s);
			}
			patients.setPromptText("Patients");

		} catch (Exception e) {
			System.out.println("issue in uploading data to patients DROPDOWN LIST");
		}

		return patients;

	}

	// add invoice to file, this method actually generates another window pop up
	public void addInvoice(Invoice invoice) {
		initialPatient.setOnAction(exe -> {

			Text tb = new Text();

			try {
				for (Iterator<Patient> iter = patList.iterator(); iter.hasNext();) {
					Patient p = iter.next();
					String s = p.getLname();
					if (initialPatient.getValue().equals(s)) {
						selected = p.getLname();
						selectedPatient = p;
					}
				}

			} catch (Exception exc) {
				tb.setText("issue in RETRIEVING PATIENT");
			}

		});

		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Invoice to Patient");

		Label label = new Label();
		label.setText("PLEASE ENTER PATIENT DETAILS");

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #e7d1d1;");

		Button submit = new Button("Submit");
		submit.setStyle("-fx-font-size: 10pt;");
		submit.setTextFill(Color.GREEN);

		submit.setOnAction(e -> {

			ArrayList<Patient> pl = PatientDB.getInstance().selectPatients();
			int index = -1;
			for (int i = 0; i < pl.size(); i++) {// go through list
				if (pl.get(i).getId() == selectedPatient.getId()) {
					index = i;
				}
			}

			if (index >= 0) {
				invoice.setInvoiceAmount(inv.getInvoiceAmount());
				invoice.setPatientID(selectedPatient.getId());
				invoice.setDate(new Date());// set date
				invoice.setUnpaid(inv.getInvoiceAmount());// set unpaid balance
				pl.get(index).addInvoice(invoice);
				InvoiceDB idb = new InvoiceDB();
				idb.addInvoice(invoice.getInvoice(), invoice.getPatientID(), invoice.getInvoiceAmount(),
						invoice.getUnpaid());
			} else {
				AlertBox ab = new AlertBox();
				ab.display("Error", "no such a patient. invoice not added to DB");
			}
			Controller.getInstance().showAlert("New Invoice", "Invoice created for " + invoice.getPatientID(),
					"Invoice number: " + invoice.getInvoice() + "\nPatientID: " + invoice.getPatientID() + "\nAmount: "
							+ invoice.getInvoiceAmount());
			Controller.getInstance().refresh();
			window.close();
		});
		// add all buttons and textfields
		layout.getChildren().addAll(label, initialPatient, submit);

		Scene scene = new Scene(layout, 300, 275);
		window.setScene(scene);
		window.show();

	}
}
