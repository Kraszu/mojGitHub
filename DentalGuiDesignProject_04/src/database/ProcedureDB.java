package database;
//Maciej Kubiniec R00144142

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Procedure;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProcedureDB extends Database {

	private Connection connection = null; // manages connection

	private PreparedStatement insertNewProcedure = null;
	private PreparedStatement updateProcedure = null;
	private PreparedStatement selectProcedure = null;
	private PreparedStatement deleteProcedure = null;

	private static ProcedureDB instance;

	public static ProcedureDB getInstance() {
		if (instance == null) {
			instance = new ProcedureDB();
		}
		return instance;
	}

	public ProcedureDB() {
		instance = this;
		try {
			connection = new Database().DatabaseConnection();

			insertNewProcedure = connection.prepareStatement(
					"INSERT INTO `Procedure` " + "( ProcNo, Name, Description, Cost) " + "VALUES (  ?, ?, ?, ? )");

			// update prepared statement
			deleteProcedure = connection.prepareStatement("delete from `Procedure` where ProcNo = ?");

			// update prepared statement
			updateProcedure = connection
					.prepareStatement("UPDATE `Procedure` set Name = ?, Description = ?, Cost = ? where ProcNo = ?");

			// select everyone prepared statement
			selectProcedure = connection.prepareStatement("SELECT * from `Procedure`");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch
	}

	// add an entry
	public int addProcedure(int procNo, String name, String description, double cost) {
		int result = 0;

		// set parameters, then execute insertNewPatient
		try {
			insertNewProcedure.setInt(1, procNo);
			insertNewProcedure.setString(2, name);
			insertNewProcedure.setString(3, description);
			insertNewProcedure.setDouble(4, cost);

			// insert the new entry; returns # of rows updated
			result = insertNewProcedure.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int deleteProcedure(int procNo) {
		int result = 0;

		//
		try {
			deleteProcedure.setInt(1, procNo);

			// delete entry; returns # of rows updated
			result = deleteProcedure.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	}

	// add an entry
	public int updateProcedure(int procNo, String name, String description, double cost) {
		int result = 0;

		// set parameters, then execute insertNewProcedure
		try {
			updateProcedure.setInt(4, procNo);
			updateProcedure.setString(1, name);
			updateProcedure.setString(2, description);
			updateProcedure.setDouble(3, cost);

			// insert the new entry; returns # of rows updated
			result = updateProcedure.executeUpdate();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return result;
	} // end method updateProcedure

	// add an entry
	public ArrayList<Procedure> selectProcedures() {
		ArrayList<Procedure> procedures = new ArrayList<Procedure>();
		// set parameters, then execute insertNewProcedure
		try {
			ResultSet rs = selectProcedure.executeQuery();

			while (rs.next()) {
				Procedure p = new Procedure(rs.getInt(1), // "ID"),
						rs.getString(2), // "Name"),
						rs.getString(3), // "Description"),
						rs.getDouble(4));// "Cost"),

				procedures.add(p);
			}

		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch

		return procedures;
	} // end method updateProcedure

	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
	} // end method close

	@SuppressWarnings("unchecked")
	public TableView<Procedure> displayTableProcedure() {

		TableView<Procedure> procedureTable = new TableView<>();

		TableColumn<Procedure, String> prCol = new TableColumn<>("ProcNo");
		prCol.setCellValueFactory(new PropertyValueFactory<>("pn"));

		TableColumn<Procedure, String> nmCol = new TableColumn<>("Name");
		nmCol.setCellValueFactory(new PropertyValueFactory<>("nm"));

		TableColumn<Procedure, String> dCol = new TableColumn<>("Description");
		dCol.setCellValueFactory(new PropertyValueFactory<>("ds"));

		TableColumn<Procedure, String> costCol = new TableColumn<>("Cost");
		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

		procedureTable.getColumns().addAll(prCol, nmCol, dCol, costCol);

		procedureTable.getItems().addAll(this.selectProcedures());

		return procedureTable;

	}

}
