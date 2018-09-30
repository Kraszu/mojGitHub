package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable {
	private static int RESERVATION = 10;
	private int resNumber;
	private double price;
	private double totalPrice;
	private final double LECTURE_DISCOUNT = .10;
	private ArrayList<Guest> guests;

	public Room(double price) {
		resNumber = RESERVATION;
		RESERVATION++; // every next reservation number is incremented by one
		this.price = price;
		guests = new ArrayList<Guest>(); // guest ArrayList instantiate
		totalPrice = 0;
	}

	public int getResNumber() {
		return resNumber;
	}

	public ArrayList<Guest> getGuest() {
		return this.guests;
	}

	public abstract int getRoomNo();// abstract method allows to retrieve data
									// from all room child (each child have
									// different values)

	public abstract int getMaxRoom();

	public void addGuest(Guest guest) {
		// add guest method adding lecturers and students to guest Array
		// and calculate the payment value (lecturer have 10% discount)

		this.guests.add(guest);
		if (guest instanceof Lecturer) {
			totalPrice += price - (price * LECTURE_DISCOUNT);

		} else if (guest instanceof Student) {
			totalPrice += price;
			;
		}
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {

		this.totalPrice = totalPrice;
	}

}
