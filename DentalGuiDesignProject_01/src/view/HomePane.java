package view;
//Maciej Kubiniec R00144142

import controller.InvoiceProcedure;
import controller.PatientManagement;
import controller.PaymentManagement;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class HomePane extends BorderPane {

	public HomePane() {

		BackgroundImage myBI = new BackgroundImage(new Image("gabinet-stomatologiczny.jpg", 702, 500, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

		this.setBackground(new Background(myBI));
		// creating the tab pane
		TabPane tabs = new TabPane();

		Tab tabPatient = new Tab();
		tabPatient.setText("Patient Management Section");
		tabPatient.setContent(new PatientManagement());
		tabPatient.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;"
				+ "-fx-font-weight: bold;");

		Tab tabInvoice = new Tab();
		tabInvoice.setText("Invoice/Procedure Management Section");
		tabInvoice.setContent(new InvoiceProcedure());
		tabInvoice.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;"
				+ "-fx-font-weight: bold;");

		Tab tabPay = new Tab();
		tabPay.setText("Payment Management Section");
		tabPay.setContent(new PaymentManagement());
		tabPay.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;"
				+ "-fx-font-weight: bold;");
		// add tabs to tabpane
		tabs.getTabs().addAll(tabPatient, tabInvoice, tabPay);
		// tabs shown at the top
		tabs.setSide(Side.TOP);
		this.setCenter(tabs);

	}
}
