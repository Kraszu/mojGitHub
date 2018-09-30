package database;
//Maciej Kubiniec R00144142

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InvoiceProced;

public class InvoiceProcDB extends Database {

	private Connection connection = null; // manages connection

	private PreparedStatement insertNewInvoiceProc = null;
	private PreparedStatement updateInvoiceProc = null;
	private PreparedStatement selectInvoiceProc = null;
	private PreparedStatement deleteInvoiceProc = null;

	private static InvoiceProcDB instance;

	public static InvoiceProcDB getInstance() {
		if (instance == null) {
			instance = new InvoiceProcDB();
		}
		return instance;
	}

	public InvoiceProcDB() {
		instance = this;
		try {
			connection = new Database().DatabaseConnection();

			insertNewInvoiceProc = connection.prepareStatement(
					"INSERT INTO `InvoiceProcedure` " + "(ID, InvoiceNo, ProcedureNo) " + "VALUES (?, ?, ?)");

			// update prepared statement
			deleteInvoiceProc = connection.prepareStatement("delete from `InvoiceProcedure` where ID = ?");

			// update prepared statement
			updateInvoiceProc = connection
					.prepareStatement("UPDATE `InvoiceProcedure` set InvoiceNo = ?, ProcedureNo = ? where ID = ?");

			// select everyone prepared statement
			selectInvoiceProc = connection.prepareStatement("SELECT * from `InvoiceProcedure`");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	}

	// add an entry
	public int addInvoiceProc(int id, int invoiceNo, int invoiceProcNo) {
		int result = 0;

	
		try {
			insertNewInvoiceProc.setInt(1, id);
			insertNewInvoiceProc.setInt(2, invoiceNo);
			insertNewInvoiceProc.setInt(3, invoiceProcNo);

			// insert the new entry; returns # of rows updated
			result = insertNewInvoiceProc.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} 

	// add an entry
	public int deleteInvoiceProc(String id) {
		int result = 0;

		// 
		try {
			deleteInvoiceProc.setString(1, id);

			// delete entry; returns # of rows updated
			result = deleteInvoiceProc.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} 

	// add an entry
	public int updateInvoiceProc(int invoiceNo, int procedureNo, int id) {
		int result = 0;

		
		try {
			updateInvoiceProc.setInt(1, invoiceNo);
			updateInvoiceProc.setInt(2, procedureNo);
			updateInvoiceProc.setInt(3, id);

			// insert the new entry; returns # of rows updated
			result = updateInvoiceProc.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} 

	// add an entry
	public ArrayList<InvoiceProced> selectInvoiceProcs() {
		ArrayList<InvoiceProced> InvoiceProcs = new ArrayList<InvoiceProced>();
		
		try {
			ResultSet rs = selectInvoiceProc.executeQuery();

			while (rs.next()) {
				InvoiceProced p = new InvoiceProced(
						rs.getInt(1), // "InvoiceProcNo"),
						rs.getInt(2), // "patientID"));
						rs.getInt(3)// "patientID"));
				);
				InvoiceProcs.add(p);
			}

		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return InvoiceProcs;
	}
	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
	} // end method close

	@SuppressWarnings("unchecked")
	public TableView<InvoiceProced> displayTableInvoiceProc() {

		TableView<InvoiceProced> InvoiceProcTable = new TableView<>();

		TableColumn<InvoiceProced, String> idNo = new TableColumn<>("ID");
		idNo.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<InvoiceProced, String> invNo = new TableColumn<>("InvoiceNo");
		invNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));

		TableColumn<InvoiceProced, String> procNo = new TableColumn<>("ProcedureNo");
		procNo.setCellValueFactory(new PropertyValueFactory<>("procedureNo"));

		InvoiceProcTable.getColumns().addAll(idNo, invNo, procNo);

		InvoiceProcTable.getItems().addAll(this.selectInvoiceProcs());

		return InvoiceProcTable;

	}

}
