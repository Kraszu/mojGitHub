package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;
import java.util.*;

import controller.Controller;

public class Patient extends Person implements Serializable {

	String fName;
	String lName;
	String address;
	String phoneNum;
	int ID;
	static int PAT_ID = 1;
	ArrayList<Invoice> p_invoiceList;

	public Patient(String fName, String lName, String address, String phoneNum) {
		super(fName, lName, address, phoneNum);
		p_invoiceList = new ArrayList<Invoice>();
		ID = PAT_ID;
		PAT_ID++;
	}

	public int getId() {
		return ID;

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

	public String toString() {

		String val = "";
		val += "\n\tID:\t\t\t\t\t" + getId() 
		+ "\n\tNAME:\t\t\t\t" + Name() 
		+ "\n\tADDRESS:\t\t\t" + getAddress()
		+ "\n\tPHONE NUMBER:\t\t" + getPNumber() 
		+ "\n\tTOTAL BILLED:\t\t\u20ac " + round(getInvoiceTotal(), 2)
		+ "\n\tINVOICE LIST: \t\n\t---------------------\n";
		for (int i = 0; i < getP_invoiceList().size(); i++)
			val += getP_invoiceList().get(i).toString();
		return val += "\n\t---------------------\n";

	}

	public void print() {
		System.out.println(toString());
	}

}