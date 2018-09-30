package controller;
//Maciej Kubiniec R00144142

import model.PatientList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentManagement extends VBox {
	int textToInt = 0;
	double textToDouble = 20.0;
	double newPayment = 0.0;
	int invoiceNo = 0;
	ListView<String> listView = new ListView<String>();

	public PaymentManagement() {

		Button button = new Button("Add Payment");
		button.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");

		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		button.setOnAction(e -> {
			// create a new stage
			Stage window = new Stage();
			// Block events to other windows
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add Payment");
			window.setMinWidth(350);
			window.setMinHeight(400);

			Label label = new Label();
			label.setText("PLEASE ENTER PAYMENT DETAILS");

			TextField textInvoice = new TextField();
			textInvoice.setPromptText("INVOICE:");
			textInvoice.setMaxWidth(100);

			TextField textAmount = new TextField();
			textAmount.setPromptText("Amount:");
			textAmount.setMaxWidth(100);

			VBox layout = new VBox(10);
			layout.setAlignment(Pos.CENTER);
			layout.setStyle("-fx-background-color: #e7d1d1");

			Button submit = new Button("Submit");
			submit.setStyle("-fx-font-size: 10pt;");
			submit.setOnAction(ex -> {
				try {
					// Validate if a number is 1-9 range and has 1 to 5 digits
					if (textInvoice.getText().matches("(([0-9]){1,5})"))
						// assign invoice no
						textToInt = Integer.parseInt(textInvoice.getText());

					if (textAmount.getText().matches("((([0-9]){1,5})|(([0-9]){1,5}\\.[0-9]{1,2}))"))
						// assign value
						textToDouble = Double.parseDouble(textAmount.getText());

				} catch (NumberFormatException nfe) {
					System.out.println("error with parsing");
				}
				// add payment method
				Controller.getInstance().addPayment(textToDouble, textToInt);

				window.close();

			});
			// add all text files and buttons
			layout.getChildren().addAll(label, textInvoice, textAmount, submit);

			Scene scene = new Scene(layout, 300, 275);
			window.setScene(scene);
			window.show();

		});

		populatepaymentList();

		this.setPadding(new Insets(20, 20, 20, 20));
		this.setStyle("-fx-font-size: 11pt;");

		this.getChildren().addAll(listView, button);
	}

	public void populatepaymentList() {
		// list of patients by instance of Controller
		PatientList list = Controller.getInstance().loadPatientList();
		try {
			// check the patients
			for (int i = 0; i < list.getLength(); i++) {
				// check the invoices
				for (int k = 0; k < list.getPatient(i).getP_invoiceList().size(); k++) {

					listView.getItems()
							.add("Invoice number: " + list.getPatient(i).getInvoice(k).getInvoice()
									+ "\tInvoice Amount= \u20ac " + list.getPatient(i).getInvoice(k).getInvoiceAmount()
									+ "\tAmount left to pay= \u20ac " + list.getPatient(i).getInvoice(k).getUnpaid());

				}
			}
		} catch (Exception e) {

		}
	}

}
