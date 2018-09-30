package model;
//Maciej Kubiniec R00144142

public class Person {

	String fName;
	String lName;
	String address;
	String phoneNumber;
	int id;

	public Person(String fName, String lName, String address) {
		setFname(fName);
		setLname(lName);
		setAddress(address);
	}

	// method overloading
	public Person(int id, String fName, String lName, String address, String pNumber) {
		setID(id);
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

	public void setID(int id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPNumber(String pNumber) {
		this.phoneNumber = pNumber;
	}

	// getters
	public int getId() {
		return this.id;
	}

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
		return this.phoneNumber;
	}

	public String Name() {
		return this.fName + " " + this.lName;
	}

}
