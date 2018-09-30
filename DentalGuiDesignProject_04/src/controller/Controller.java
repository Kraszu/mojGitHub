package controller;
//Maciej Kubiniec R00144142

import model.Dentist;

import view.HomePane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {

	private static Controller instance;
	private Stage myStage;

	public Controller() {
		instance = this;
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void setStage(Stage s) {
		this.myStage = s;
	}

	public Stage getStage() {
		return this.myStage;
	}

	public void handleLogin(TextField nameInput, PasswordField passInput) {

		String username = nameInput.getText().toString();

		Dentist dentist = new Dentist(nameInput.toString(), passInput.toString());
		boolean success = true;

		if (!username.toUpperCase().equals(dentist.getName().toUpperCase())) {
			success = false;
		}

		String pass = passInput.getText().toString();
		// if above check has passed do this check
		if ((!pass.toUpperCase().equals(dentist.getPassword().toUpperCase())) && success) {
			success = false;
		}

		if (!success) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("FAILURE");
			alert.setHeaderText("Wrong Login or Password");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		} else {
			HomePane pane = new HomePane();
			Scene scene = new Scene(pane, 702, 500);
			getStage().setScene(scene);
		}
	}

	public void refresh() {
		HomePane pane = new HomePane();
		Scene scene = new Scene(pane, 702, 500);
		Controller.getInstance().getStage().setScene(scene);

	}

	public void showAlert(String title, String intro, String context) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(intro);
		alert.setContentText(context);
		alert.showAndWait();
	}

}