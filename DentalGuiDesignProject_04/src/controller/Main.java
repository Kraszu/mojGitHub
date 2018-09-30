package controller;
//Maciej Kubiniec R00144142

import view.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setResizable(false);
		primaryStage.setTitle("Dental Surgery");
		// create new login
		Login lg = new Login();
		// create login scene
		Scene scene = new Scene(lg, 702, 500);
		// set scene
		primaryStage.setScene(scene);
		// display to screen
		primaryStage.show();
		// set current stage
		Controller.getInstance().setStage(primaryStage);
		// set up for background image
		BackgroundImage myBI = new BackgroundImage(new Image("Background2.jpg", 715, 510, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		lg.setBackground(new Background(myBI));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
