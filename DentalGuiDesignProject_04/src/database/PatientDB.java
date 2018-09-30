package database;
//Maciej Kubiniec R00144142

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Patient;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PatientDB extends Database {

	private Connection connection = null; // manages connection

	private PreparedStatement insertNewPatient = null;
	private PreparedStatement updatePatient = null;
	private PreparedStatement selectPatients = null;
	private PreparedStatement deletePatient = null;

	private static PatientDB instance;

	public static PatientDB getInstance() {
		if (instance == null) {
			instance = new PatientDB();
		}
		return instance;
	}

	public PatientDB() {
		instance = this;
		try {
			connection = new Database().DatabaseConnection();

			insertNewPatient = connection.prepareStatement("INSERT INTO Patient "
					+ "( ID, fName, lName, Address, PhoneNumber ) " + "VALUES ( ?, ?, ?, ?, ? )");

			// update prepared statement
			deletePatient = connection.prepareStatement("delete from Patient where ID = ?");

			// update prepared statement
			updatePatient = connection.prepareStatement(
					"UPDATE Patient set fName = ?, lName = ?, Address = ?, PhoneNumber= ? where ID = ?");

			// select everyone prepared statement
			selectPatients = connection.prepareStatement("SELECT * from Patient");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	}

	// add an entry
	public int addPatient(int id, String fName, String lName, String address, String num) {
		int result = 0;

		// set parameters, then execute insertNewPatient
		try {
			insertNewPatient.setInt(1, id);
			insertNewPatient.setString(2, fName);
			insertNewPatient.setString(3, lName);
			insertNewPatient.setString(4, address);
			insertNewPatient.setString(5, num);

			// insert the new entry; returns # of rows updated
			result = insertNewPatient.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int deletePatient(String id) {
		int result = 0;

		// set parameters, then execute deleteNewPatient
		try {
			deletePatient.setString(1, id);

			// delete entry; returns # of rows updated
			result = deletePatient.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int updatePatient(int id, String fName, String lName, String address, String num) {
		int result = 0;

		// set parameters, then execute insertNewPerson
		try {
			updatePatient.setInt(5, id);
			updatePatient.setString(1, fName);
			updatePatient.setString(2, lName);
			updatePatient.setString(3, address);
			updatePatient.setString(4, num);

			// insert the new entry; returns # of rows updated
			result = updatePatient.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public ArrayList<Patient> selectPatients() {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		// set parameters, then execute insertNewPerson
		try {
			ResultSet rs = selectPatients.executeQuery();

			while (rs.next()) {
				Patient p = new Patient(rs.getInt(1), // "ID"),
						rs.getString(2), // "fName"),
						rs.getString(3), // lName),
						rs.getString(4), // "Address"),
						rs.getString(5));// "PhoneNumber"));
				patients.add(p);
			}

		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return patients;
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
	public TableView<Patient> displayTablePatient() {

		TableView<Patient> patientTable = new TableView<>();

		TableColumn<Patient, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<Patient, String> fNameCol = new TableColumn<>("fName");
		fNameCol.setCellValueFactory(new PropertyValueFactory<>("fnm"));

		TableColumn<Patient, String> lNameCol = new TableColumn<>("lName");
		lNameCol.setCellValueFactory(new PropertyValueFactory<>("lnm"));

		TableColumn<Patient, String> addressCol = new TableColumn<>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<>("ad"));

		TableColumn<Patient, String> pnCol = new TableColumn<>("PhoneNumber");
		pnCol.setCellValueFactory(new PropertyValueFactory<>("pn"));

		patientTable.getColumns().addAll(idCol, fNameCol, lNameCol, addressCol, pnCol);

		patientTable.getItems().addAll(this.selectPatients());

		return patientTable;

	}

}
