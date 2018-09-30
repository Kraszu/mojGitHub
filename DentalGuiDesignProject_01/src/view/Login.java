package view;
//Maciej Kubiniec R00144142

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Login extends GridPane {
	public Login() {
		// Override default
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(12);
		

		HBox hbButton = new HBox();
		hbButton.setSpacing(10.0);
		// Aligns HBox and controls in HBox
		hbButton.setAlignment(Pos.CENTER);

		Button login = new Button("Login");
		login.setStyle("-fx-font-size: 12pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		

		Label labelName = new Label("Doctor Name:");
		labelName.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		TextField TextName = new TextField();
		TextName.setStyle("-fx-font-size: 11pt;");

		Label LabelPassword = new Label("Password:");
		LabelPassword.setStyle("-fx-font-size: 11pt;-fx-font-weight: bold;");
		PasswordField TextPassword = new PasswordField();
		TextPassword.setStyle("-fx-font-size: 11pt;");
		// handle login with user name and password input
		login.setOnAction(e -> {
			Controller.getInstance().handleLogin(TextName, TextPassword);
		});

		System.out.println(TextPassword.getText());

		hbButton.getChildren().addAll(login);
		this.add(labelName, 0, 0);
		this.add(LabelPassword, 0, 1);
		this.add(TextName, 1, 0);
		this.add(TextPassword, 1, 1);
		this.add(hbButton, 0, 2, 2, 1);
		
	}
	

}
