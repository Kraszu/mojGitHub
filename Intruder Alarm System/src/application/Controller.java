package application;

//Maciej Kubiniec R00144142
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class Controller extends BorderPane {
	private Stage myStage;
	private static Controller instance;

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

	public void handleLogin(PasswordField pinInput) {

		String Pin = pinInput.getText().toString();
		boolean success = true;
		PinNo pinNo = new PinNo(pinInput.toString());

		if (!Pin.toUpperCase().equals(pinNo.getPin().toUpperCase()) && success) {
			success = false;
		}

		if (!success) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("FAILURE");
			alert.setHeaderText("Wrong Pin");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		} else {
			Home pane = new Home();
			Scene scene = new Scene(pane, 600, 500);
			getStage().setScene(scene);

		}
	}

}
