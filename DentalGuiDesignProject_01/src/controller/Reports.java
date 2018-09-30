package controller;
//Maciej Kubiniec R00144142

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import model.PatientList;
import model.Invoice;
import model.Patient;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Reports {
	public Reports() {

		Stage window = new Stage();
		// Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Reports");
		window.setMinWidth(400);
		window.setMinHeight(200);
		

		BorderPane border = new BorderPane();
		border.setPadding(new Insets(20, 0, 20, 20));
		

		// display in combobox
		ComboBox<String> reportsComboBox = new ComboBox<String>();
		reportsComboBox.getItems().addAll("Patients", "Invoices"

		);
		reportsComboBox.setPromptText("Select report:");

		VBox vbButtons = new VBox();
		Button toConsole = new Button("Display");
		toConsole.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		toConsole.setOnAction(e -> {
			String displayReport = "";

			Stage w = new Stage();
			// Block events to other windows
			w.initModality(Modality.APPLICATION_MODAL);
			w.setMinWidth(700);
			w.setMinHeight(400);
			BorderPane bd = new BorderPane();
			bd.setPadding(new Insets(20, 0, 20, 20));
			bd.setStyle("-fx-font-size: 12pt;");
			// get patient list object from controller
			PatientList pl = Controller.getInstance().loadPatientList();

			ArrayList<Patient> pls = new ArrayList<Patient>();
			// assign patient list content to newly created arraylist pls
			pls = pl.getPlist();
			// use comparator to sort list
			Collections.sort(pls, Comparator.comparing(Patient::getLname));

			int size = Controller.getInstance().loadPatientList().getLength();
			try {
				// report by invoice
				if (reportsComboBox.getValue().equals("Invoices")) {
					w.setTitle("Invoices Report");
					// looking through list of patients
					for (int i = 0; i < size; i++)
						// looking through list of invoices
						for (int f = 0; f < pl.getPatient(i).getP_invoiceList().size(); f++)
							// invoice details
							displayReport += pl.getPatient(i).getP_invoiceList().get(f).toString() + "\n";
				}
				// report by patients
				if (reportsComboBox.getValue().equals("Patients")) {
					w.setTitle("Patients Report SORTED BY NAME");
					// looking through list of procedures
					for (int i = 0; i < pls.size(); i++) {
						displayReport += pls.get(i).toString();

					}
				}

			} catch (Exception exc) {
				w.setTitle("ERROR");
				displayReport += "please do select an option";
			}

			ListView<String> listView = new ListView<String>();
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			listView.getItems().add(displayReport);
			bd.setCenter(listView);
			Scene scene = new Scene(bd, 600, 400);
			w.setScene(scene);
			w.show();
		});

		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20));
		vbButtons.getChildren().addAll(toConsole);

		border.setLeft(reportsComboBox);
		border.setRight(vbButtons);
		border.setStyle("-fx-background-color: #e7d1d1;");

		Scene scene = new Scene(border, 300, 275);
		window.setScene(scene);
		window.show();
	}

}
