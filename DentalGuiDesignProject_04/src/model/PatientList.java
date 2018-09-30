package model;
//Maciej Kubiniec R00144142

import model.Patient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class PatientList implements Serializable {

	private static final long serialVersionUID = 1L;
	ArrayList<Patient> patientList;

	public PatientList() {
		patientList = new ArrayList<Patient>();
	}

	public void addPatient(Patient patient) {
		this.patientList.add(patient);
	}

	public Patient getPatient(int index) {
		return this.patientList.get(index);
	}

	public int getLength() {
		return this.patientList.size();
	}

	public void removePatient(Patient patient) {

		for (Iterator<Patient> pat = patientList.iterator(); pat.hasNext();) {
			Patient p = pat.next();
			if (p.getFname().equals(patient.getFname()) && p.getLname().equals(patient.getLname()))
				pat.remove();

		}

	}

	public ArrayList<Patient> getPlist() {
		return this.patientList;
	}

	public String toString() {
		String val = "";
		for (Patient pt : patientList)
			val += pt.toString();
		return val;
	}

	public void print() {
		System.out.println(toString());
	}

}
