package database;
//Maciej Kubiniec R00144142

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Payment;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentDB extends Database {

	private Connection connection = null; // manages connection

	private PreparedStatement insertNewPayment = null;
	private PreparedStatement updatePayment = null;
	private PreparedStatement selectPayment = null;
	private PreparedStatement deletePayment = null;

	private static PaymentDB instance;

	public static PaymentDB getInstance() {
		if (instance == null) {
			instance = new PaymentDB();
		}
		return instance;
	}

	public PaymentDB() {
		instance = this;
		try {
			connection = new Database().DatabaseConnection();

			insertNewPayment = connection
					.prepareStatement("INSERT INTO `Payment` " + "(ID, InvoiceNo, AmountPaid) " + "VALUES (?, ?, ?)");

			// update prepared statement
			deletePayment = connection.prepareStatement("delete from `Payment` where ID = ?");

			// update prepared statement
			updatePayment = connection
					.prepareStatement("UPDATE `Payment` set InvoiceNo = ?, AmountPaid = ? where ID = ?");

			// select everyone prepared statement
			selectPayment = connection.prepareStatement("SELECT * from `Payment`");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	}

	// add an entry
	public int addPayment(int id, int invoice, double amount) {
		int result = 0;

		// set parameters, then execute insertNewPyment
		try {
			insertNewPayment.setInt(1, id);
			insertNewPayment.setInt(2, invoice);
			insertNewPayment.setDouble(3, amount);

			// insert the new entry; returns # of rows updated
			result = insertNewPayment.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method addPyment

	// add an entry
	public int deletePayment(int id) {
		int result = 0;

		// set parameters, then execute deleteNewPyment
		try {
			deletePayment.setInt(1, id);

			// delete entry; returns # of rows updated
			result = deletePayment.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method addPyment

	// add an entry
	public int updatePayment(int invoice, double amount, int id) {
		int result = 0;

		// set parameters, then execute insertNewPyment
		try {
			updatePayment.setInt(1, invoice);
			updatePayment.setDouble(2, amount);
			updatePayment.setInt(3, id);

			// insert the new entry; returns # of rows updated
			result = updatePayment.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method updatePyment

	// add an entry
	public ArrayList<Payment> selectPayments() {
		ArrayList<Payment> Payments = new ArrayList<Payment>();
		// set parameters, then execute insertNewPyment
		try {
			ResultSet rs = selectPayment.executeQuery();

			while (rs.next()) {
				Payment p = new Payment(rs.getInt(1), rs.getInt(2), rs.getDouble(3));
				Payments.add(p);

			}

		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return Payments;
	} // end method updatePyment

	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
	} // end method close

	@SuppressWarnings("unchecked")
	public TableView<Payment> displayTablePayment() {

		TableView<Payment> PaymentTable = new TableView<>();

		TableColumn<Payment, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Payment, String> inCol = new TableColumn<>("InvoiceNo");
		inCol.setCellValueFactory(new PropertyValueFactory<>("ino"));

		TableColumn<Payment, String> apCol = new TableColumn<>("AmountPaid");
		apCol.setCellValueFactory(new PropertyValueFactory<>("am"));

		PaymentTable.getColumns().addAll(idCol, inCol, apCol);

		PaymentTable.getItems().addAll(this.selectPayments());

		return PaymentTable;
	}

}
