package application;

//Maciej Kubiniec R00144142
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Home extends BorderPane {
	public Home() {
		Pane p = new Pane();
		Pane p1 = new Pane();
		p.setMinSize(300, 100);
		GridPane gp = new GridPane();

		RoomPane sitting = new RoomPane("Sitting room", Color.YELLOW);
		sitting.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold ");
		RoomPane kitchen = new RoomPane("Kitchen", Color.AQUA);
		kitchen.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold ");
		RoomPane bedroom = new RoomPane("Bedroom", Color.GREEN);
		bedroom.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold ");
		RoomPane living = new RoomPane("Living Room", Color.PINK);
		living.setStyle("-fx-font-size: 13pt;-fx-font-weight: bold ");

		gp.add(sitting, 0, 0);
		gp.add(p, 0, 1);
		gp.add(kitchen, 0, 2);

		Label l = new Label("Hall");

		l.setStyle("-fx-font-size: 15pt;-fx-font-weight: bold ");
		gp.add(l, 0, 1);

		gp.add(bedroom, 4, 0);
		gp.add(p1, 2, 1);
		gp.add(living, 4, 2);

		setCenter(gp);
		// create on/off and exit buttons and add them at the top of window
		BorderPane gp2 = new BorderPane();

		this.setPadding(new Insets(20, 20, 20, 20));
		Button off = new Button("On/Off");
		off.setStyle("-fx-font-size: 12pt;");
		Button on = new Button("On/Off");
		on.setStyle("-fx-font-size: 12pt;");
		Button exit = new Button("Exit");
		exit.setStyle("-fx-font-size: 12pt;");

		exit.setOnAction(t -> {
			

			Button yes = new Button("Yes");
			yes.setStyle("-fx-font-size: 12pt;");
			Button no = new Button("No");
			no.setStyle("-fx-font-size: 12pt;");
			Label lb = new Label("Are You Sure?");
			lb.setStyle("-fx-font-size: 13pt;");
			BorderPane top = new BorderPane();
			top.setLeft(yes);
			top.setCenter(lb);
			top.setRight(no);
			setTop(top);
			yes.setOnAction(y -> {
				Platform.exit();
			});
			no.setOnAction(u -> {
				setTop(gp2);
			});

		});
		
		off.setTextFill(Color.BLACK);
		on.setTextFill(Color.RED);
		gp2.setLeft(off);
		gp2.setRight(exit);
		on.setOnAction(a -> {
			gp2.getChildren().remove(on);
			gp2.setLeft(off);
			bedroom.setToOff();
			living.setToOff();
			sitting.setToOff();
			kitchen.setToOff();
		});
		off.setOnAction(t -> {
			gp2.getChildren().remove(off);
			gp2.setLeft(on);
			bedroom.setToOn();
			living.setToOn();
			sitting.setToOn();
			kitchen.setToOn();
		});

		setTop(gp2);

	}
}
