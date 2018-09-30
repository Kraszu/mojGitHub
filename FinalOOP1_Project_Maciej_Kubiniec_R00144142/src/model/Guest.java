package model;

import java.io.Serializable;

public abstract class Guest implements Serializable {

	private String Name;
	

	public Guest(String Name) {
		this.Name = Name;
		

	}

	public String toString() {
		return this.Name ;

	}
}