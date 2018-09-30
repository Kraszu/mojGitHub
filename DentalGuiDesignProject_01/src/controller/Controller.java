package controller;
//Maciej Kubiniec R00144142

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

import model.Dentist;
import model.Invoice;
import model.Patient;
import model.PatientList;
import model.Payment;
import model.Procedure;
import view.AlertBox;
import view.HomePane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {

	private static Controller instance;
	private Stage myStage;
	PatientList patList;
	Patient patient;
	ArrayList<Procedure> procedure;

	public Controller() {
		instance = this;
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void setStage(Stage s) {
		this.myStage = s;
	}

	public Stage getStage() {
		return this.myStage;
	}

	// payments
	public void addPayment(Double amount, int paymentN) {
		PatientList list = Controller.getInstance().loadPatientList();
		Invoice inv = null;
		boolean shown = false;
		Payment pmt = new Payment(paymentN, amount, new Date());

		for (int i = 0; i < list.getLength(); i++) {// looking into patient list
			// and into invoice list for each patient
			for (int k = 0; k < list.getPatient(i).getP_invoiceList().size(); k++) {

				inv = list.getPatient(i).getP_invoiceList().get(k);
				// if invoice number is the same as payment number entered
				if (inv.getInvoice() == paymentN) {
					// if amount outstanding is greater than amount we want to
					// pay
					if (inv.getUnpaid() > amount) {
						inv.setUnpaid(inv.getUnpaid() - amount);
						inv.addPayment(pmt);
						// if amount outstanding is equals to amount we want to
						// pay
					} else if (inv.getUnpaid() == amount) {
						inv.setUnpaid(inv.getUnpaid() - amount);
						inv.addPayment(pmt);
						inv.setIsPaid(true);
						AlertBox warning = new AlertBox();
						warning.display("COMPLETE!", "This invoice is fully paid.");
					} else if (inv.getUnpaid() < amount) {

						AlertBox warning = new AlertBox();
						warning.display("!WARNING!", " Entered amount is too high");
						shown = true;

					} else {
						if (!shown) {
							AlertBox warning = new AlertBox();
							warning.display("!WARNING!", "Entered amount is too high");
							shown = true;
						}
					}
				}
			}
		}
		Controller.getInstance().saveToSerialFile(list, "patientlist.ser");
		Controller.getInstance().refresh();
	}
	// Loading saved procedures

	@SuppressWarnings("unchecked")
	public ArrayList<Procedure> loadProcedureList() {
		try {
			procedure = (ArrayList<Procedure>) readFromSerialFile("procedurelist.ser");
			if (procedure == null) {
				procedure = new ArrayList<Procedure>();
				saveToSerialFile(procedure, "procedurelist.ser");
				System.out.println("There is no record");
			}

		} catch (Exception e) {
			System.out.println("ERROR");
		}
		return procedure;
	}

	// Remove procedures from file
	public void removeProcedureFromFile(Procedure p) {
		if (procedure == null) {
			procedure = new ArrayList<Procedure>();

			System.out.println("no such a procedure");
			saveToSerialFile(procedure, "procedurelist.ser");
		} else {
			for (Iterator<Procedure> proc = procedure.iterator(); proc.hasNext();) {
				Procedure pr = proc.next();
				// checks if the name ONLY matches the name on the file
				if (pr.getProcName().equals(p.getProcName())) {
					proc.remove();
				}
			}
			saveToSerialFile(procedure, "procedurelist.ser");
		}
	}

	// save procedures
	public void procedureToFile(Object o) {

		if (procedure == null) {
			procedure = new ArrayList<Procedure>();
			procedure.add((Procedure) o);
			saveToSerialFile(procedure, "procedurelist.ser");

		} else {
			procedure.add((Procedure) o);
			saveToSerialFile(procedure, "procedurelist.ser");
		}
	}

	// load saved patient
	public Patient loadPatient(int index) {
		Patient pat = null;
		try {

			patList = (PatientList) readFromSerialFile("patientlist.ser");
			pat = patList.getPatient(index);

			if (pat == null)
				System.out.println("**no record");
			else
				System.out.println("Patient loaded from file!\nName=" + pat.Name());

		} catch (Exception e) {
			System.out.println("**error**");
		}
		return pat;// .getName();
	}

	// Upload patient details
	public PatientList loadPatientList() {

		try {
			patList = (PatientList) readFromSerialFile("patientlist.ser");

			if (patList == null)
				System.out.println("**no record");

		} catch (Exception e) {
			System.out.println("**error**");
			System.out.println("**no record");

		}
		return patList;
	}

	// remove patient
	public void removePatientFromFile(Patient p) {
		if (patList == null) {
			patList = new PatientList();
			System.out.println("no such a patient");
			saveToSerialFile(patList, "patientlist.ser");
		} else {
			patList.removePatient(p);
			saveToSerialFile(patList, "patientlist.ser");
		}
	}

	// Save patient to file
	public void patientToFile(Object o) {

		if (patList == null) {
			patList = new PatientList();
			patList.addPatient((Patient) o);
			saveToSerialFile(patList, "patientlist.ser");

		} else {
			patList.addPatient((Patient) o);
			saveToSerialFile(patList, "patientlist.ser");
		}
	}

	// Retrieve object from file
	public Object readFromSerialFile(String filename) {
		Object o = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.out.println("!WARNING!" + filename + " does not exist!!");
		} catch (IOException i) {
			System.out.println("!WARNING! " + filename + " cannot be read!!");
		} catch (ClassNotFoundException c) {
			System.out.println("class not found");
			c.printStackTrace();
		}
		return o;
	}

	// Save an Object to the file
	public void saveToSerialFile(Object o, String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.println("Serialized data  saved in " + filename);
		} catch (FileNotFoundException e) {
			System.out.println("!WARNING!" + filename + " does not exist!!");
		} catch (IOException i) {
			System.out.println("!WARNING!" + filename + " cannot be read!!");
		}
	}

	// invoice number consistency method
	public int getInvoiceNumberCount() {
		PatientList list = Controller.getInstance().loadPatientList();
		int temp = 0;
		for (int i = 0; i < list.getLength(); i++) {
			for (int k = 0; k < list.getPatient(i).getP_invoiceList().size(); k++) {
				if (list.getPatient(i).getInvoice(k).getInvoice() >= temp)
					temp = list.getPatient(i).getInvoice(k).getInvoice();

			}
		}
		return temp;
	}

	public void handleLogin(TextField nameInput, PasswordField passInput) {

		String username = nameInput.getText().toString();
		boolean success = true;
		Dentist dentist = new Dentist(nameInput.toString(), passInput.toString());

		if (!username.toUpperCase().equals(dentist.getName().toUpperCase())) {
			success = false;
		}

		String pass = passInput.getText().toString();
		// if above check has passed do this check
		if ((!pass.toUpperCase().equals(dentist.getPassword().toUpperCase())) && success) {
			success = false;
		}

		if (!success) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("FAILURE");
			alert.setHeaderText("Wrong Login or Password");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		} else {
			HomePane pane = new HomePane();
			Scene scene = new Scene(pane, 702, 500);
			getStage().setScene(scene);
		}
	}

	// refresh method allow us to update system with any changes and prompt the
	// system to homeBP
	public void refresh() {
		HomePane pane = new HomePane();
		Scene scene = new Scene(pane, 702, 500);
		Controller.getInstance().getStage().setScene(scene);

	}

}