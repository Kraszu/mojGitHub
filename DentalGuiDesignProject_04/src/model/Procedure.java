package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Procedure implements Serializable {
	private static final long serialVersionUID = 1L;
	int procNo;
	String procName;
	String procDesc;
	double procCost;
	static int count = 0;

	public Procedure(int procedureNo, String procName, String procDesc, double procCost) {

		count++;
		int i = count;
		setPrn(i);

		setPrn(procedureNo);
		setProcName(procName);
		setProcDescription(procDesc);
		setProcCost(procCost);
	}

	public Procedure(String procName, String procDesc, double procCos, int textToInt) {

	}

	public String toString() {
		String val = "";
		val += "\nProcedure name:\t" + getProcName() + "\nDescription:\t\t" + getProcDescription()
				+ "\nCost:\t\t\t\t\u20ac" + getProcCost();

		return val;
	}

	public void print() {
		System.out.println(toString());
	}

	private final IntegerProperty pn = new SimpleIntegerProperty(this, "pn");

	public IntegerProperty pnProperty() {
		return pn;
	}

	public final int getPrn() {
		return pnProperty().get();
	}

	public final void setPrn(int pn) {
		pnProperty().set(pn);
	}

	private final StringProperty nm = new SimpleStringProperty(this, "nm");

	public StringProperty nmProperty() {
		return nm;
	}

	public final String getProcName() {
		return nmProperty().get();
	}

	public final void setProcName(String procName) {
		nmProperty().set(procName);
	}

	private final StringProperty ds = new SimpleStringProperty(this, "ds");

	public StringProperty dsProperty() {
		return ds;
	}

	public final String getProcDescription() {
		return dsProperty().get();
	}

	public final void setProcDescription(String procDesc) {
		dsProperty().set(procDesc);
	}

	private final DoubleProperty cost = new SimpleDoubleProperty(this, "cost");

	public DoubleProperty costProperty() {
		return cost;
	}

	public final Double getProcCost() {
		return costProperty().get();
	}

	public final void setProcCost(Double procCost) {
		costProperty().set(procCost);
	}
}