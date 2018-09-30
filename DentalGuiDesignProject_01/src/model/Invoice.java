package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;
import java.util.*;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
	int invoice;
	double invoiceAmount;
	Date invoiceDate;
	boolean isPaid = false;
	static int count = 0;
	double unpaid;
	ArrayList<Procedure> inv_procList;
	ArrayList<Payment> inv_paymentList;

	public Invoice(Date invoiceDate) {
		setDate(invoiceDate);
		inv_procList = new ArrayList<Procedure>();
		inv_paymentList = new ArrayList<Payment>();

		count++;
		int i = count;
		setInvoice(i);
	}

	public void setInvoice(int invoiceNumber) {
		this.invoice = invoiceNumber;
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

	public void setInvoiceAmount(Double invAmou) {
		this.invoiceAmount = invAmou;
	}

	// this method allow us to update the unpaid balance
	public void setUnpaid(Double amount) {
		this.unpaid = amount;
	}

	public void setIsPaid(boolean b) {
		this.isPaid = b;
	}

	// this refers to invoice number
	public int getInvoice() {
		return this.invoice;
	}

	// original invoice amount
	public double getInvoiceAmount() {
		return round(this.invoiceAmount, 2);
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

	// this methods returns the remaining balance to pay on this invoice
	public double getUnpaid() {
		return round(this.unpaid, 2);
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

	public String toString() {
		String val = "";
		// go through procedure list and concatenate
		for (int i = 0; i < this.inv_procList.size(); i++)
			val += "\n" + this.inv_procList.get(i).toString();
		// go through payment list
		for (int k = 0; k < this.getInv_paymentList().size(); k++) {
			Payment paid = this.getInv_paymentList().get(k);
			// if the payment number is the same as invoice number the payment
			// list is concatenated too
			if (paid.getPaymentN() == this.getInvoice())
				val += "\n" + paid.toString();

		}
		return "\n----INVOICE NUMBER: " + getInvoice() 
		+ "----\nDATE: " + getInvoiceDate() 
		+ "\nBILLED: \u20ac"+ getInvoiceAmount()
		+ "\nUNPAID: \u20ac" + getUnpaid()
		+ "\nPROCEDURES & PAYMENTS:" + val + "\n";
	}

	public void print() {
		System.out.println(toString());
	}
}