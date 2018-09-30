package view;
//Maciej Kubiniec R00144142

import javafx.stage.*;

import javafx.scene.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import controller.Controller;
import database.PatientDB;
import javafx.geometry.*;

public class ConfirmBox {

	// this variable is used to validate user action (what button is pressed)
	static boolean answer;

	public static boolean display(String title, String message, Object p) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(300);

		Label label = new Label();
		label.setText(message);

		// Create two buttons
		Button yesButton = new Button("Yes");
		yesButton.setTextFill(Color.GREEN);
		Button noButton = new Button("No");
		noButton.setTextFill(Color.RED);
		// Clicking will set answer and close window
		yesButton.setOnAction(e -> {
			answer = true;
			try {
				// if button yes remove patient
				PatientDB.getInstance().deletePatient((String) p);
				// if there is no patient to remove
			} catch (Exception exc) {

				// procedure
			}

			Controller.getInstance().refresh();
			window.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		VBox layout = new VBox(10);
		// Add buttons
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #e7d1d1;");
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		// Make sure to return answer
		return answer;
	}
}