
package application;
//Maciej Kubiniec R00144142

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class StopwatchPane
{
    Text text;
    
    Button sButton, rButton;
    VBox vBox = new VBox(30);
    HBox hBox;
    Timeline timeline;
    int mins = 0, secs = 0, millis = 0;
    boolean sos = true;
    void change(Text text) {
		if(millis == 1000) {
			secs++;
			millis = 0;
		}
		if(secs == 60) {
			mins++;
			secs = 0;
		}
		text.setText((((mins/10) == 0) ? "0" : "") + mins + ":"
		 + (((secs/10) == 0) ? "0" : "") + secs + ":" 
			+ (((millis/10) == 0) ? "00" : (((millis/100) == 0) ? "0" : "")) + millis++);
    }

    public StopwatchPane()
    {
    	text = new Text("00:00:000");
    	text.setStyle("-fx-font-size: 12pt;-fx-stroke:red;");
		
		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
            	change(text);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		sButton = new Button("Start");
		sButton.setRotate(17);
		sButton.setPrefSize(50, 20);
		sButton.setStyle("-fx-font-size: 9pt;-fx-text-fill:#50f441;-fx-font-weight: bold;"
				+ "-fx-background-color:radial-gradient(radius 245%, black, derive(#50f441, -30%)); ");
		sButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(sos) {
            		timeline.play();
            		sos = false;
            		sButton.setText("Stop");
            	} else {
            		timeline.pause();
            		sos = true;
            		sButton.setText("Start");
            	}
            }
        });
		rButton = new Button("Reset");
		rButton.setRotate(-17);
		rButton.setPrefSize(50, 20);
		
		rButton.setStyle("-fx-font-size: 9pt;-fx-text-fill:#50f441;-fx-font-weight: bold;"
				+ "-fx-background-color:radial-gradient(radius 245%, black, derive(#50f441, -30%)); ");
		rButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	mins = 0;
            	secs = 0;
            	millis = 0;
            	timeline.pause();
            	text.setText("00:00:000");
            	if(!sos) {
            		sos = true;
            		sButton.setText("Start");
            	}
            }
        });
		hBox = new HBox(30);
		hBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(0, 10, 11, 0));
		hBox.setPadding(new Insets(0, 5, 11, 0));
		hBox.getChildren().addAll(sButton, rButton);
		vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text, hBox);
    }

    public VBox getStopWatch()
    {
        return vBox;
        
    }

}