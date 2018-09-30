package controller;
//Maciej Kubiniec R00144142

import model.Patient;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPatient {

	

	public AddPatient() {
		Stage addPatient = new Stage();

		// Block events to other windows
		addPatient.initModality(Modality.APPLICATION_MODAL);
		addPatient.setTitle("Add Patient");
		addPatient.setMinWidth(350);
		addPatient.setMinHeight(400);

		Label label = new Label();
		label.setText("PLEASE ENTER PATIENT DETAILS ");

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
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #e7d1d1;");

		Button submit = new Button("Submit");
		submit.setStyle("-fx-font-size: 10pt;");

		submit.setOnAction(e -> {

			Patient pat = new Patient(firstName.getText().toString(), lastName.getText().toString(),
					Address.getText().toString(), Phone.getText().toString());
			// add patient to serilal file
			Controller.getInstance().patientToFile(pat);
			// update current instance with new entry
			Controller.getInstance().refresh();
			addPatient.close();
		});

		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> addPatient.close());
		// add all buttons & textfields
		layout.getChildren().addAll(label, firstName, lastName, Address, Phone, submit, closeButton);

		// Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		addPatient.setScene(scene);
		addPatient.showAndWait();
	}

}
