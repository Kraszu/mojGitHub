package application;
//Maciej Kubiniec R00144142	

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setResizable(false);
		primaryStage.setTitle("Intruder Alarm System");
		// create new login
		LogPage log = new LogPage();
		Scene scene = new Scene(log, 300, 200);

		primaryStage.setScene(scene);
		primaryStage.show();
		Controller.getInstance().setStage(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}