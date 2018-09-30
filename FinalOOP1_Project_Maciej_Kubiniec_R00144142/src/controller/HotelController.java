package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Double;
import model.Guest;
import model.HotelModel;
import model.Lecturer;
import model.Room;
import model.Single;
import model.Student;
import model.Suite;
import storage.Storage;

public class HotelController implements Serializable {

	private HotelModel hotelM;
	Storage ST = new Storage();
	Scanner in = new Scanner(System.in);

	public HotelController() {

		this.hotelM = new HotelModel();// hotelModel instantiate
		this.ST = new Storage(); // storage package instantiate
	}

	public void reservation() {// this method should get user through the
								// reservation process
		String roomChoice = "";
		String Name = "";
		String Name1 = "";
		String Name2 = "";

		String slChoice = "";
		System.out.println("Please slect room type that guest wishes to stay ");
		// here customer have to chose between room types
		System.out.println("suites >s<, doubles >d<, singles >a< ");
		roomChoice = in.next();
		in.nextLine();
		System.out.println("Please enter main guest name");
		Name = in.nextLine();

		// choose between lecturer and student
		System.out.println("Plase select >L< for Lecturer or >S< for Student ");
		slChoice = in.next();

		if (roomChoice.equalsIgnoreCase("s")) {
			Suite s = new Suite(); // the chosen room have to be instantiate
			if (slChoice.equalsIgnoreCase("s")) {
				Guest gs = new Student(Name); // same with
												// student/lecturer
				s.addGuest(gs); // student has been added to guest array list
								// that is located in room for which the room
								// types are extends
			}
			if (slChoice.equalsIgnoreCase("l")) {
				Guest gl = new Lecturer(Name);
				s.addGuest(gl);

			}
			this.hotelM.addRoom(s);// finally guest are added to room array list
									// which belong to hotel model class

			// in room for 3 or 2 people user can choose if add another guest or
			// not
			// if yes - have to choose between student and lecturer.

			System.out.println("do you want to add another guest to this room? y/n");
			String addChoice = "";
			addChoice = in.next();
			if (addChoice.equalsIgnoreCase("y")) {
				in.nextLine();
				System.out.println("Please enter guest name");
				Name1 = in.nextLine();

				System.out.println("Plase select >L< for Lecturer or >S< for Student ");
				slChoice = in.next();

				if (slChoice.equalsIgnoreCase("s")) {
					Guest gs = new Student(Name1);
					s.addGuest(gs);
				}
				if (slChoice.equalsIgnoreCase("l")) {
					Guest gl = new Lecturer(Name1);
					s.addGuest(gl);

				}
				System.out.println("do you want to add another guest to this room? y/n");

				addChoice = in.next();
				if (addChoice.equalsIgnoreCase("y")) {
					in.nextLine();
					System.out.println("Please enter guest name");
					Name2 = in.nextLine();

					System.out.println("Plase select >L< for Lecturer or >S< for Student ");
					slChoice = in.next();

					if (slChoice.equalsIgnoreCase("s")) {
						Guest gs = new Student(Name2);
						s.addGuest(gs);
					}
					if (slChoice.equalsIgnoreCase("l")) {
						Guest gl = new Lecturer(Name2);
						s.addGuest(gl);

					}
				}
			}
		}
		if (roomChoice.equalsIgnoreCase("d")) {
			Double d = new Double();
			if (slChoice.equalsIgnoreCase("s")) {
				Guest gs = new Student(Name);
				d.addGuest(gs);
			}
			if (slChoice.equalsIgnoreCase("l")) {
				Guest gl = new Lecturer(Name);
				d.addGuest(gl);
			}
			this.hotelM.addRoom(d);

			System.out.println("do you want to add another guest to this room? y/n");
			String addChoice = "";
			addChoice = in.next();
			if (addChoice.equalsIgnoreCase("y")) {
				in.nextLine();
				System.out.println("Please enter guest name");
				Name1 = in.nextLine();

				System.out.println("Plase select >L< for Lecturer or >S< for Student ");
				slChoice = in.next();

				if (slChoice.equalsIgnoreCase("s")) {
					Guest gs = new Student(Name1);
					d.addGuest(gs);
				}
				if (slChoice.equalsIgnoreCase("l")) {
					Guest gl = new Lecturer(Name1);
					d.addGuest(gl);

				}
			}
		}
		if (roomChoice.equalsIgnoreCase("a")) {
			Single a = new Single();
			if (slChoice.equalsIgnoreCase("s")) {
				Guest gs = new Student(Name);
				// a.getGuest().add(gs);
				a.addGuest(gs);

			}

			if (slChoice.equalsIgnoreCase("l")) {
				Guest gl = new Lecturer(Name);
				a.addGuest(gl);

			}

			this.hotelM.addRoom(a);

		}

	}

	public String getFreeRooms() { // this method calculates how many free rooms
									// we have in the system
		int suite = 3, doubles = 6, single = 6;

		for (Room room : this.hotelM.getRooms()) {

			if (room instanceof Suite) {// here i check if chosen room is a room
										// from this type
				if (this.hotelM.getRooms().size() <= room.getMaxRoom())
					suite--; // Initially I assign to the rooms max of rooms and
								// every time the room is added as occupy it
								// deduct one from that number
			}

			if (room instanceof Double) {
				if (this.hotelM.getRooms().size() <= room.getMaxRoom())
					doubles--;
			}

			if (room instanceof Single && this.hotelM.getRooms().size() <= room.getMaxRoom()) {
				single--;
			}

		}

		return " Free Rooms: " + "\n\t" + suite + " Suits; " + "\n\t" + doubles + " Doubles; " + "\n\t" + single
				+ " Singles; " + "\n\t";
	}

	// this method gives full informations about
	// reservation searched by reservation number
	public void selectResNo(int index) {
		try {
			int rID = -1;
			System.out.println("Please enter reservation number ");
			rID = in.nextInt();
			boolean found = false;
			try {
				for (Room r : this.hotelM.getRooms()) {
					// if reservation number is equal to one in the system ,
					// we would get following informations to the screen

					if (r.getResNumber() == rID) {
						// reservation number
						System.out.println("\nReservation no " + r.getResNumber()
								+ " details:\n====================================================== ");
						// room number
						System.out.println("\n\tRoom no is " + r.getRoomNo());
						// guest name
						System.out.println("\tMain Guest Name: " + r.getGuest().get(index).toString());
						if (r instanceof Single) {
							// information about chosen room type
							System.out.println("\tChosen room is single");
						}
						if (r instanceof Double) {
							System.out.println("\tChosen room is double");
						}
						if (r instanceof Suite) {
							System.out.println("\tChosen room is a suite");
						}

						// for how many guests the reservation has been made
						System.out.println("\tNumber of guests booked to that room is: " + r.getGuest().size());
						// total amount due for this room p/n
						System.out.println("\tTotal payment for night is: " + r.getTotalPrice());
						System.out.println("");
						System.out.println("======================================================");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						found = true;
					}

				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(
						"Reservation form was fill incorectly please cancel this reservation and try to do it again");
			}

			if (!found) {
				System.out.println("there is no reservation with that number in the system. \nPlease try again");
			}
		} catch (InputMismatchException e) {
			System.out.println("Sorry - invalid input");
			in.next();
		}
	}

	public void guestDisplay(int index) {
		System.out.println("List of guest that currently stays in the hotel: ");
		for (Room r : this.hotelM.getRooms()) {

			System.out.println("\nRoom no: " + r.getRoomNo());

			for (int j = 0; j < r.getGuest().size(); j++) {
				System.out.println("Guest Name: " + r.getGuest().get(j).toString());
				Guest g = r.getGuest().get(j);
				if (g instanceof Lecturer) {
					System.out.println("Lecturer");
				} else {
					System.out.println("Student");
				}
			}

		}
	}

	public void cancelReservation() {// method for reservation cancel without
										// payment

		try {
			int rIDtoCancelation = -1;
			System.out.println("Please enter reservation number ");
			rIDtoCancelation = in.nextInt();
			try {
				for (Room r : this.hotelM.getRooms()) { // scan room array list

					if (r.getResNumber() == rIDtoCancelation) { // if searching
																// reservation
																// number is in
																// the
																// system ...
						rIDtoCancelation = this.hotelM.getRooms().indexOf(r);
						System.out.println("Reservation has been cancelled");
					}

				}
				if (rIDtoCancelation > -1) {
					this.hotelM.getRooms().remove(rIDtoCancelation);// ...then
																	// it is
																	// removed
																	// from
																	// the
																	// system
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Reservation do not exist");

			}
		} catch (InputMismatchException e) {
			System.out.println("Sorry - invalid input");
			in.next();
		}
	}

	public void pendingPayment(int index) { // this method goes through room
											// array list and prints out all
											// existing customers and their
											// payment p/n
		for (Room r : this.hotelM.getRooms())
			System.out.println("Guest : " + r.getGuest().get(index).toString() + "\nPayment due: " + r.getTotalPrice());
	}

	public void saveToBank(int index) {
		try {
			File resultFile = new File("Bank.txt");

			if (!resultFile.exists()) {
				resultFile.createNewFile();
			}

			FileWriter resultWriter = new FileWriter(resultFile, true);
			try {
				int rIDtoCheckOut = -1;
				System.out.println("Please enter reservation number ");
				rIDtoCheckOut = in.nextInt();

				for (Room r : this.hotelM.getRooms()) {
					{
						if (r.getResNumber() == rIDtoCheckOut) {

							resultWriter.write("\nGuest : " + r.getGuest().get(index).toString() + " has paid "
									+ r.getTotalPrice() + "\n");
						}

					}

				}
				resultWriter.close();
				try {
					for (Room r : this.hotelM.getRooms())
						if (r.getResNumber() == rIDtoCheckOut) {
							rIDtoCheckOut = this.hotelM.getRooms().indexOf(r);
							System.out.println("Customer Check Out is ended successfully");
						}

					if (rIDtoCheckOut > -1) {
						this.hotelM.getRooms().remove(rIDtoCheckOut);
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Reservation do not exist");
				}
			} catch (InputMismatchException e) {
				System.out.println("Sorry - invalid input");
				in.next();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void loadFromBankFile() {// this method call back saved text file to
									// the screen
		try {
			File resultFile = new File("Bank.txt");
			if (!resultFile.exists()) {
				resultFile.createNewFile();
			}
			FileReader resultReader = new FileReader(resultFile);
			BufferedReader buffReader = new BufferedReader(resultReader);

			String lineFromFile = buffReader.readLine();

			while (lineFromFile != null) {

				System.out.println(lineFromFile);

				lineFromFile = buffReader.readLine();
			}
			buffReader.close();
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// this method save whole hotel data to Hotel.ser file every time that
	// system exit is chosen
	public void saveHotelData() {

		try {
			Storage.storeObject(hotelM, "Hotel.ser");
		} catch (Exception e) {
			Storage.storeObject(e, "StorageWriteException.ser");
		}
	}

	// and this one is loading that data at the start of app.
	public void loadFromHotelData() {
		try {
			HotelModel hm = (HotelModel) Storage.readObject("Hotel.ser");
			if (hm != null) {
				hotelM = hm;
			}
		} catch (Exception e) {
			Storage.storeObject(e, "StorageReadException.ser");
		}
	}

}
