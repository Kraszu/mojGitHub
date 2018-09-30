package application;
//Maciej Kubiniec R00144142

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.util.Duration;


public class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;
	Pane clock = new Pane(); // Create a clock
	

	
	//Construct a default clock with the current time 
	public ClockPane() {
		EventHandler<ActionEvent> eventHandler = e -> {
			 setCurrentTime(); // Set a new clock time
			};

			  // Create an animation for a running clock
			  Timeline animation = new Timeline(
			  new KeyFrame(Duration.millis(1000), eventHandler));
			  animation.setCycleCount(Timeline.INDEFINITE);
			  animation.play(); // Start animation
	}

	// Construct a clock with specified hour, minute, and second 
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	// Return hour 
	public int getHour() {
		return hour;
	}

	//Set a new hour
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	// Return minute
	public int getMinute() {
		return minute;
	}

	// Set a new minute 
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}

	// Return second 
	public int getSecond() {
		return second;
	}

	//Set a new second
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}

	// Set the current time for the clock 
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);

		paintClock(); // Repaint the clock
	}

	//Paint the clock 
	private void paintClock() {

		// Initialize clock parameters
		double clockRadius = Math.min(getWidth(), getHeight()) * 0.61 * 0.82;
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		clockRadius = (120);
		centerX = (148);
		centerY = (155);

		// Draw circle
		Circle circle = new Circle(centerX, centerY, clockRadius);

		circle.setStyle(" -fx-fill: radial-gradient(radius 245%, black, derive(GREEN, -30%)); "
				+ "-fx-stroke: radial-gradient(radius 180%, darkgreen, derive(#50f441, -40%),derive(#50f441, -30%));-fx-stroke-width:10;");

		Group numbers = new Group();
		numbers.setLayoutX(142);
		numbers.setLayoutY(142);

		for (int i = 0; i < 12; i++) {
			Label label = new Label(String.valueOf(i == 0 ? 12 : i));
			label.setStyle("-fx-font-size: 13pt;-fx-text-fill:#50f441;-fx-font-weight: bold; ");
			Circle c = new Circle();

			c.getTransforms().add(new Rotate((i) * (360d / 12d)));
			c.getTransforms().add(new Translate(0, -100d));
			label.setTranslateX(c.localToParent(0, 0).getX());
			label.setTranslateY(c.localToParent(0, 0).getY());

			StackPane sp = new StackPane(c, label);
			numbers.getChildren().add(sp);
		}

		// Draw second hand
		double sLength = clockRadius * 0.65;
		double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
		double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStyle(" -fx-stroke: #50f441;-fx-stroke-width: 2;-fx-stroke-line-cap: round;");

		// Draw minute hand
		double mLength = clockRadius * 0.55;
		double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
		double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, xMinute, minuteY);
		mLine.setStyle(" -fx-stroke: derive(#50f441, -5%);-fx-stroke-width: 4;-fx-stroke-line-cap: round;");

		// Draw hour hand
		double hLength = clockRadius * 0.35;
		double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);

		hLine.setStyle(" -fx-stroke: derive(#50f441, -30%);-fx-stroke-width: 6;-fx-stroke-line-cap: round;");

		getChildren().clear();
		clock.getChildren().addAll(circle, sLine, mLine, hLine, numbers);
	}
    public Pane getPane()
    {
    	return clock;
    }

}  