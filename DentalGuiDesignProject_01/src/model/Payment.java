package model;
//Maciej Kubiniec R00144142
import java.io.Serializable;
import java.util.*;

public class Payment implements Serializable {

	int paymentN;
	double paymentAmount;
	Date paymentDate;

	public Payment(int paymentN, double paymentAmount, Date paymentDate) {
		setPaymentN(paymentN);
		setPaymentAmount(paymentAmount);
		setPaymentDate(paymentDate);
	}

	// setters
	public void setPaymentN(int paymentN) {
		this.paymentN = paymentN;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	// getters
	public int getPaymentN() {
		return this.paymentN;
	}

	public double getPaymentAmount() {
		return this.paymentAmount;
	}

	public Date getDate() {
		return this.paymentDate;
	}

	public String toString() {
		String out = "";
		out = "\t\nPayment Details:" 
		+ "\nNumber (as invoice number):\t" 
		+ this.paymentN + "\nAmount:\t\u20ac"
		+ this.paymentAmount + "\nDate:\t" 
		+ this.paymentDate + "\n";
		return out;
	}

}
