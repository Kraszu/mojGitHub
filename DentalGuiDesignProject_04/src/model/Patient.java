package model;
//Maciej Kubiniec R00144142

import java.util.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient extends Person {

	String fName;
	String lName;
	String address;
	String phoneNum;
	int id;

	ArrayList<Invoice> p_invoiceList;

	public Patient(int id, String fName, String lName, String address, String phoneNum) {
		super(id, fName, lName, address, phoneNum);

		setId(id);

		p_invoiceList = new ArrayList<Invoice>();

		setfNm(fName);
		setlNm(lName);
		setAd(address);
		setPN(phoneNum);
	}

	// properties for data base TableView

	private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");

	public IntegerProperty idProperty() {
		return ID;
	}

	public final int getID() {
		return idProperty().get();
	}

	public final void setId(int ID) {
		idProperty().set(ID);
	}

	private final StringProperty fnm = new SimpleStringProperty(this, "fnm");

	public StringProperty fnmProperty() {
		return fnm;
	}

	public final String getfNm() {
		return fnmProperty().get();
	}

	public final void setfNm(String fName) {
		fnmProperty().set(fName);
	}

	private final StringProperty lnm = new SimpleStringProperty(this, "lnm");

	public StringProperty lnmProperty() {
		return lnm;
	}

	public final String getlNm() {
		return lnmProperty().get();
	}

	public final void setlNm(String lName) {
		lnmProperty().set(lName);
	}

	private final StringProperty ad = new SimpleStringProperty(this, "ad");

	public StringProperty adProperty() {
		return ad;
	}

	public final String getAd() {
		return adProperty().get();
	}

	public final void setAd(String address) {
		adProperty().set(address);
	}

	private final StringProperty pn = new SimpleStringProperty(this, "pn");

	public StringProperty pnProperty() {
		return pn;
	}

	public final String getPN() {
		return pnProperty().get();
	}

	public final void setPN(String phoneNum) {
		pnProperty().set(phoneNum);
	}

	public ArrayList<Invoice> getP_invoiceList() {
		return this.p_invoiceList;
	}

	public void addInvoice(Invoice inv) {
		this.p_invoiceList.add(inv);
	}

	public Invoice getInvoice(int index) {
		return this.p_invoiceList.get(index);
	}

	public double getInvoiceTotal() {
		double total = 0;
		for (int i = 0; i < getP_invoiceList().size(); i++)
			total += getP_invoiceList().get(i).getInvoiceAmount();
		return total;
	}

	// this formats the double output to 2 decimal digits
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long temp = Math.round(value);
		return (double) temp / factor;
	}

}