package application;

//Maciej Kubiniec R00144142
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LogPage extends GridPane {
	public LogPage() {
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(12);

		HBox hbButton = new HBox();
		hbButton.setSpacing(10.0);
		// Aligns HBox and controls in HBox
		hbButton.setAlignment(Pos.CENTER);

		Button login = new Button("Enter The House");
		login.setStyle(
				"-fx-font-size: 11pt;-fx-background-color: #382c2c;-fx-text-fill:#ffffff;" + "-fx-font-weight: bold;");

		Label labelPin = new Label("\tEnter PIN:");
		labelPin.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		PasswordField TextPin = new PasswordField();
		TextPin.setStyle("-fx-font-size: 11pt;");
		TextPin.setPromptText("Pin Code");
		login.setOnAction(e -> {
			Controller.getInstance().handleLogin(TextPin);
		});
		System.out.println(TextPin.getText());

		hbButton.getChildren().addAll(login);
		this.add(labelPin, 1, 0);
		this.add(TextPin, 1, 1);
		this.add(hbButton, 0, 2, 2, 1);

	}

}
