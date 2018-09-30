package application;
//Maciej Kubiniec R00144142

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Clock extends Application
{

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {

        Tie clock = new Tie();
        clock.setStyle("-fx-background-color: DARKKHAKI;");

        Scene scene = new Scene(clock, 300, 310);
        primaryStage.setTitle("Clock"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
       primaryStage.setResizable(false);

    }

    
    public static void main(String[] args)
    {
        launch(args);
    }

}