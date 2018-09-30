package model;
//Maciej Kubiniec R00144142
import java.io.Serializable;

public class Person implements Serializable {

	String fName;
	String lName;
	String address;
	String password;
	String pNumber;

	public Person(String fName, String lName, String password) {
		setFname(fName);
		setLname(lName);
		setAddress(password);
	}

	// method overloading
	public Person(String fName, String lName, String address, String pNumber) {

		setFname(fName);
		setLname(lName);
		setAddress(address);
		setPNumber(pNumber);
	}
	// setters

	public void setFname(String fName) {
		this.fName = fName;
	}

	public void setLname(String lName) {
		this.lName = lName;
	}

	public void setPassowrd(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	// getters

	public String getFname() {
		return this.fName;
	}

	public String getLname() {
		return this.lName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPNumber() {
		return this.pNumber;
	}

	public String Name() {
		return this.fName + " " + this.lName;
	}

}
