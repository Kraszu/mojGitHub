package model;
//Maciej Kubiniec R00144142

import java.util.ArrayList;
import java.util.Iterator;

public class ProcedureList {
	ArrayList<Procedure> procList;

	public ProcedureList() {
		procList = new ArrayList<Procedure>();
	}

	public void addProcedure(Procedure procedure) {
		this.procList.add(procedure);
	}

	public Procedure getProcedure(int index) {
		return this.procList.get(index);
	}

	public int getLength() {
		return this.procList.size();
	}

	public void removeProcedure(Procedure procedure) {
		for (Iterator<Procedure> iter = procList.iterator(); iter.hasNext();) {
			Procedure p = iter.next();
			if (p.getProcName().equals(procedure.getProcName())) {
				iter.remove();
			}
		}
	}

	public ArrayList<Procedure> getPlist() {
		return this.procList;
	}

	public String toString() {
		String val = "";
		for (Procedure pro : procList)
			val += pro.toString();
		return val;
	}

	public void print() {
		System.out.println(toString());
	}

}
