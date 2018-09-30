package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;

public class Procedure implements Serializable {
	private static final long serialVersionUID = 1L;
	int procN;
	String procName;
	String procDesc;
	double procCost;
	static int count = 0;

	public Procedure(String procName, String procDesc, double procCost, int procDuration) {
		setProcName(procName);
		setProcDescription(procDesc);
		setProcCost(procCost);

		count++;
		int i = count;
		setprocN(i);
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public void setProcDescription(String procDesc) {
		this.procDesc = procDesc;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	public void setprocN(int procN) {
		this.procN = procN;
	}

	public String getProcName() {
		return this.procName;
	}

	public String getProcDesc() {
		return this.procDesc;
	}

	public double getProcCost() {
		return this.procCost;
	}

	public int getProcN() {
		return this.procN;
	}

	public String toString() {
		String val = "";
		val += "\nProcedure name:\t" 
		+ getProcName() + "\nDescription:\t\t" 
		+ getProcDesc() + "\nCost:\t\t\t\t\u20ac"
		+ getProcCost();
		
		return val;
	}

	public void print() {
		System.out.println(toString());
	}
}