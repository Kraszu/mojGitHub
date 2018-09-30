package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;
import java.util.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	double OriginalAmount;
	Date invoiceDate;
	boolean isPaid = false;
	;
	double RemainingBalance;
	ArrayList<Procedure> inv_procList;
	ArrayList<Payment> inv_paymentList;
	int invoice;
	public Invoice(){};
	public Invoice(Date invoiceDate) {
		setDate(invoiceDate);
		inv_procList = new ArrayList<Procedure>();
		inv_paymentList = new ArrayList<Payment>();
		
	}

	public Invoice(int invoiceNumber, int patientID, double OriginalAmount, double RemainingBalance) {

		setInvoice(invoiceNumber);
		setPatientID(patientID);
		setInvoiceAmount(OriginalAmount);
		setUnpaid(RemainingBalance);
		setIno(invoiceNumber);
	}
	public void setInvoice(int invoiceN){
		this.invoice=invoiceN;
		}
	public int getInvoice(){
		return this.invoice;
		}//this refers to invoice number
	private final IntegerProperty invNo = new SimpleIntegerProperty(this, "invNo");

	public IntegerProperty iNoProperty() {
		return invNo;
	}

	public final int getIno() {
		return iNoProperty().get();
	}

	public final void setIno(int invoiceNumber) {
		iNoProperty().set(invoiceNumber);
	}

	private final IntegerProperty PatientID = new SimpleIntegerProperty(this, "PatientID");

	public IntegerProperty pIdProperty() {
		return PatientID;
	}

	public final int getPatientID() {
		return pIdProperty().get();
	}

	public final void setPatientID(int patientID) {
		pIdProperty().set(patientID);
	}

	private final DoubleProperty am = new SimpleDoubleProperty(this, "am");

	public DoubleProperty amProperty() {
		return am;
	}

	public final Double getInvoiceAmount() {
		return amProperty().get();
	}

	public final void setInvoiceAmount(Double OriginalAmount) {
		amProperty().set(OriginalAmount);
	}

	private final DoubleProperty rm = new SimpleDoubleProperty(this, "rm");

	public DoubleProperty rmProperty() {
		return rm;
	}

	public final Double getUnpaid() {
		return rmProperty().get();
	}

	public final void setUnpaid(Double RemainingBalance) {
		rmProperty().set(RemainingBalance);
	}

	public void addPayment(Payment payment) {
		this.inv_paymentList.add(payment);
	}

	public void addProcedure(Procedure procedure) {
		this.inv_procList.add(procedure);
	}

	public void setDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setIsPaid(boolean b) {
		this.isPaid = b;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public boolean getIsPaid() {
		return this.isPaid;
	}

	public ArrayList<Procedure> getInv_procList() {
		return this.inv_procList;
	}

	public ArrayList<Payment> getInv_paymentList() {
		return this.inv_paymentList;
	}

	// this method leaves 2 decimal digits
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long temp = Math.round(value);
		return (double) temp / factor;
	}

	public void clearProcList() {
		this.inv_procList.clear();
	}

	public void removeProcedure(Procedure procedure) {
		boolean isRemoved = false;
		for (Iterator<Procedure> proc = inv_procList.iterator(); proc.hasNext();) {
			Procedure p = proc.next();
			if (p.getProcName().equalsIgnoreCase(procedure.getProcName()) && !isRemoved) {
				proc.remove();
				isRemoved = true;
			}
		}

	}

}