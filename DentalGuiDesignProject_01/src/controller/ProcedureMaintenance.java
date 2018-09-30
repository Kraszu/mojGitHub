package controller;
//Maciej Kubiniec R00144142

import model.Procedure;
import view.ConfirmBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProcedureMaintenance {
	double textToDouble = 0.0;
	int textToInt = 0;

	public ProcedureMaintenance() {
		Stage window = new Stage();

		// Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add or Remove Procedure");
		window.setMinWidth(350);
		window.setMinHeight(400);

		Label label = new Label();
		label.setText("PLEASE ENTER PROCEDURE DETAILS ");

		// Details text fields
		TextField textName = new TextField();
		textName.setPromptText("Name");
		textName.setMaxWidth(150);

		TextField textDesc = new TextField();
		textDesc.setPromptText("Description");
		textDesc.setMaxWidth(150);

		TextField textCost = new TextField();
		textCost.setPromptText("Cost");
		textCost.setMaxWidth(150);

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #e7d1d1;");

		Button submit = new Button("Submit");
		submit.setStyle("-fx-font-size: 10pt;");

		CheckBox add = new CheckBox("Add Procedure");
		CheckBox remove = new CheckBox("Remove Procedure");
		add.setSelected(true);

		add.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				remove.setSelected(!newValue);
			}
		});

		remove.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				add.setSelected(!newValue);
			}
		});

		// action after usage of Submit button
		submit.setOnAction(e -> {

			try {
				// cost entry validation
				if (textCost.getText().matches("((([0-9]){1,5})|(([0-9]){1,5}\\.[0-9]{1,2}))"))
					textToDouble = Double.parseDouble(textCost.getText());

			} catch (NumberFormatException nfe) {
				System.out.println("error with parsing");
			}

			Procedure proc = new Procedure(textName.getText().toString(), textDesc.getText().toString(), textToDouble,
					textToInt);// new procedure instance

			if (add.isSelected()) {
				// save to file
				Controller.getInstance().procedureToFile(proc);
			}
			if (remove.isSelected()) {
				new ConfirmBox();
				// confirmBox pop up
				ConfirmBox.display("Remove Procedure", "Remove procedure " + proc.getProcName() + " ?", proc);
			}
			// update
			Controller.getInstance().refresh();
			window.close();

		});

		Button closeButton = new Button("Close this window");
		closeButton.setOnAction(e -> {
			Controller.getInstance().refresh();
			window.close();
		});
		// adding text fields and buttons
		layout.getChildren().addAll(label, textName, textDesc, textCost, add, remove, submit, closeButton);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}
}
