package controller;
//Maciej Kubiniec R00144142

import database.InvoiceDB;
import database.PatientDB;
import database.ProcedureDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Reports {
	TableView<?> displayReport;

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
		reportsComboBox.getItems().addAll("Patients", "Invoices", "Procedures"

		);
		reportsComboBox.setPromptText("Select report:");

		VBox vbButtons = new VBox();
		Button toConsole = new Button("Display");
		toConsole.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		toConsole.setOnAction(e -> {

			Stage w = new Stage();
			// Block events to other windows
			w.initModality(Modality.APPLICATION_MODAL);
			w.setMinWidth(700);
			w.setMinHeight(400);
			BorderPane bd = new BorderPane();
			bd.setPadding(new Insets(20, 0, 20, 20));
			bd.setStyle("-fx-font-size: 12pt;");
			// get patient list object from controller

			try {
				// report by invoice
				if (reportsComboBox.getValue().equals("Invoices")) {
					w.setTitle("Invoices Report");
					// looking through list of patients
					displayReport = InvoiceDB.getInstance().displayTableInvoice();
				}
				if (reportsComboBox.getValue().equals("Procedures")) {
					w.setTitle("Procedures Report");
					displayReport = ProcedureDB.getInstance().displayTableProcedure();
					// report by patients
				}
				if (reportsComboBox.getValue().equals("Patients")) {
					w.setTitle("Patients Report SORTED BY NAME");
					displayReport = PatientDB.getInstance().displayTablePatient();

				}
			}

			catch (Exception exc) {
				w.setTitle("ERROR");

			}

			bd.setCenter(displayReport);
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
