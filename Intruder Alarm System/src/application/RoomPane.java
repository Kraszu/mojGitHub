package application;

//Maciej Kubiniec R00144142
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RoomPane extends Pane {

	RadioButton off = new RadioButton("Off");
	RadioButton on = new RadioButton("On");
	Button intruder = new Button("Intruder");
	BorderPane bp = new BorderPane();

	// constructor takes room name and border color
	public RoomPane(String roomName, Color color) {
		getInstance(roomName, color);
	}

	public void getInstance(String roomName, Color color) {
		// create radioButtons group
		ToggleGroup group = new ToggleGroup();

		off.setUserData("RadioButton1");
		off.setToggleGroup(group);
		off.setSelected(true);

		on.setUserData("RadioButton2");
		on.setToggleGroup(group);

		VBox layout = new VBox(on, off);
		layout.setPadding(new Insets(10));
		bp.setTop(new Label(roomName));
		bp.setLeft(layout);

		off.setOnAction(w -> {
			bp.setRight(null);

		});
		// show intruder button
		on.setOnAction(e -> {

			bp.setRight(intruder);

			intruder.setOnAction(q -> {
				intruder();// flashing red animation
			});

		});
		// set size of a "room" pane
		bp.setMinSize(200, 150);
		bp.setMaxSize(200, 150);
		bp.setBorder(
				new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		getChildren().add(bp);
		setMinSize(200, 150);
		setMaxSize(200, 150);
	}

	private void intruder() {

		// **************************
		// this animation changes the background color
		// of the VBox from red with opacity=1
		// to red with opacity=0
		// **************************
		final Animation animation = new Transition() {

			{
				setCycleDuration(Duration.millis(1000));
				setCycleCount(5);
				setInterpolator(Interpolator.EASE_OUT);
			}

			@Override
			protected void interpolate(double frac) {
				Color vColor = new Color(1, 0, 0, 1 - frac);
				bp.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
			}
		};
		animation.play();

	}

	// select ON radioButton
	public void setToOn() {
		on.setSelected(true);
		bp.setRight(intruder);
		intruder.setOnAction(q -> {
			intruder();
		});
	}

	// select OFF radioButton
	public void setToOff() {
		off.setSelected(true);
		bp.setRight(null);

	}

}