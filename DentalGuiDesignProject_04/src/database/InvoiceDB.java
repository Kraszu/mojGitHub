package database;
//Maciej Kubiniec R00144142

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Invoice;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InvoiceDB extends Database{
	
	private Connection connection = null; // manages connection
	
	private PreparedStatement insertNewInvoice = null;
	private PreparedStatement updateInvoice = null;
	private PreparedStatement selectInvoice = null;
	private PreparedStatement deleteInvoice = null;
	
	private static InvoiceDB instance;


	public static InvoiceDB getInstance() {
		if (instance == null) {
			instance = new InvoiceDB();
		}
		return instance;
	}

	public InvoiceDB() {
		instance = this;
		try{
			connection = new Database().DatabaseConnection();

			insertNewInvoice = connection.prepareStatement("INSERT INTO `Invoice` "
					+ "(InvoiceNo, PatientID, OriginalAmount, RemainingBalance ) " + "VALUES ( ?, ?, ?, ?)");

			// update prepared statement
			deleteInvoice = connection.prepareStatement(
					"delete from `Invoice` where InvoiceNo = ?");

			// update prepared statement
			updateInvoice = connection.prepareStatement(
					"UPDATE `Invoice` set PatientId = ?, OriginalAmount = ?, RemainingBalance = ? where InvoiceNo = ?");

			// select everyone prepared statement
			selectInvoice = connection.prepareStatement("SELECT * from `Invoice`");
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	}
	// add an entry
	public int addInvoice(int invoiceNumber, int PatientId, double OriginalAmount, double RemainingBalance) {
		int result = 0;

		
		try {
			insertNewInvoice.setInt(1, invoiceNumber);
			insertNewInvoice.setInt(2, PatientId);
			insertNewInvoice.setDouble(3, OriginalAmount);
			insertNewInvoice.setDouble(4, RemainingBalance);

			// insert the new entry; returns # of rows updated
			result = insertNewInvoice.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int deleteInvoice(String InvoiceNo) {
		int result = 0;

		
		try {
			deleteInvoice.setString(1, InvoiceNo);

			// delete entry; returns # of rows updated
			result = deleteInvoice.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int updateInvoice(int invoiceNumber,int PatientId, double OriginalAmount, double RemainingBalance) {
		int result = 0;

		
		try {
			updateInvoice.setInt(1, invoiceNumber);
			updateInvoice.setInt(2, PatientId);
			updateInvoice.setDouble(3,  OriginalAmount);
			updateInvoice.setDouble(4,  RemainingBalance);
			

			

			// insert the new entry; returns # of rows updated
			result = updateInvoice.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} 

	// add an entry
	public ArrayList<Invoice> selectInvoices() {
		ArrayList<Invoice> Invoices = new ArrayList<Invoice>();
		
		try {
			ResultSet rs = selectInvoice.executeQuery();
			
			while (rs.next()) {			
				Invoice p = new Invoice(
						rs.getInt(1),//"invoiceNo"), 
						rs.getInt(2),//"patientID"));
						rs.getDouble(3),
						rs.getDouble(4)
						);
				Invoices.add(p);
			}

		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return Invoices;
	}
	
	public ArrayList<Invoice> selectInvoices(int patId) {
		ArrayList<Invoice> Invoices = new ArrayList<Invoice>();
		
		try {
			ResultSet rs = selectInvoice.executeQuery();			
			while (rs.next()) {			
				Invoice p = new Invoice(
						rs.getInt(1),//"invoiceNo"), 
						rs.getInt(2),//"patientID"));
						rs.getDouble(3),
						rs.getDouble(4)
						);
					if(patId==rs.getInt(2)){
						Invoices.add(p);					
					}				
			}
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return Invoices;
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
	public TableView<Invoice> displayTableInvoice(){
		
		
        TableView<Invoice> InvoiceTable = new TableView<>();
        
        TableColumn<Invoice, String> invNoCol = new TableColumn<>("InvoiceNo");
        invNoCol.setCellValueFactory(new PropertyValueFactory<>("invNo") );
        
        TableColumn<Invoice, String> patID = new TableColumn<>("PatientID");
        patID.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        
        TableColumn<Invoice, String> amCol = new TableColumn<>("OriginalAmount");
        amCol.setCellValueFactory(new PropertyValueFactory<>("am"));
        
        TableColumn<Invoice, String> rmCol = new TableColumn<>("RemainingBalance");
        rmCol.setCellValueFactory(new PropertyValueFactory<>("rm"));
        
        InvoiceTable.getColumns().addAll(invNoCol,patID,amCol, rmCol);
        
        InvoiceTable.getItems().addAll(this.selectInvoices());

        return InvoiceTable;

    
}
	
	
	@SuppressWarnings("unchecked")
	public TableView<Invoice> displayTableInvoice(int patId){
				
				
		        TableView<Invoice> InvoiceTable = new TableView<>();
		        
		        TableColumn<Invoice, String> invNo = new TableColumn<>("InvoiceNo");
		        invNo.setCellValueFactory(new PropertyValueFactory<>("ino"));
		        
		        TableColumn<Invoice, String> patID = new TableColumn<>("PatientID");
		        patID.setCellValueFactory(new PropertyValueFactory<>("PatientID"));
		        
		        TableColumn<Invoice, String> amCol = new TableColumn<>("OriginalAmount");
		        amCol.setCellValueFactory(new PropertyValueFactory<>("am"));
		        
		        TableColumn<Invoice, String> rmCol = new TableColumn<>("RemainingBalance");
		        rmCol.setCellValueFactory(new PropertyValueFactory<>("rm"));
		        
		        InvoiceTable.getColumns().addAll(invNo,amCol, rmCol);
		        
		        InvoiceTable.getItems().addAll(this.selectInvoices(patId));

		        return InvoiceTable;

		    
	}

}
