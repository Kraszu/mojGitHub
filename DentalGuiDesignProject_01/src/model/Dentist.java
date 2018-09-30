package model;
//Maciej Kubiniec R00144142

import java.io.Serializable;

public class Dentist implements Serializable {

	final String name = "1";
	final String password = "1";

	public Dentist(String name, String password) {

	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

}
