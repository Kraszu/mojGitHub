package view;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.HotelController;

public class HotelMenu implements Serializable {

	private HotelController Hotel;

	static Scanner in = new Scanner(System.in); // scanner instantiate

	public HotelMenu() {
		this.Hotel = new HotelController(); // controller instantiate
	}

	public void startApp() {
		// start of the hotel app. Loading previous state of reservations and
		// direct to main menu
		this.Hotel.loadFromHotelData();
		menuSwitch();
	}

	public int mainMenu() {// main menu gives 6 initial options
		int option = 0;

		System.out.println("----------------------------------------");
		System.out.println(" Welcome To CIT Hotel Management System	");
		System.out.println("****************************************");
		System.out.println("1) Display Available Rooms");
		System.out.println("2) Display Guest");
		System.out.println("3) Process Reservation");
		System.out.println("4) Process Payment");
		System.out.println("5) Check Out");
		System.out.println("6) Exit");
		boolean selected = false;

		while (selected == false) {// while loop until user select correct
									// number 1-6

			try {
				option = in.nextInt();
				in.nextLine();
				if ((option == 1) || (option == 2) || (option == 3) || (option == 4) || (option == 5))
					selected = true;
				else if ((option == 6)) { // option 6 terminate app and saves
											// current status to file
					this.Hotel.saveHotelData();
					System.out.println("Goodbye!");
					System.exit(0);
				}

				else
					System.out.println("Sorry but you have to choose an option between 1 and 6");

			} catch (InputMismatchException e) { // exception handling incorrect
													// input problem (i.e.letter
													// instead of num)
				System.out.println("Sorry you did not enter a valid option, please try again");

				in.next();
			}

		}

		return option;

	}

	public void menuSwitch() { // switch method deal with cases from main menu

		boolean finish = false;
		if (finish == false) {
			int option = mainMenu();

			switch (option) {

			case 1:

				this.Hotel.getFreeRooms();// by this.Hotel system connects with
											// controller's method

				System.out.println(this.Hotel.getFreeRooms());
				break;
			case 2:
				this.Hotel.guestDisplay(0);

				break;
			case 3:

				reservationSwitch();
				break;
			case 4:
				paymentSwitch();

				break;
			case 5:
				this.Hotel.saveToBank(0);

				break;

			default:
				finish = true;
				break;
			}
			menuSwitch();
		}
	}

	public HotelController getHotel() {
		return Hotel;
	}

	public void setHotel(HotelController hotel) {
		Hotel = hotel;
	}

	public int reservationMenu() {
		int selection = 0;

		System.out.println("----------------------------------------");
		System.out.println("    	Welcome To Reservation	        ");
		System.out.println("****************************************");
		System.out.println("1) New Reservation");
		System.out.println("2) View Reservation");
		System.out.println("3) Cancell Reservation");
		System.out.println("4) Return");

		boolean selected = false;

		while (selected == false) {

			try {
				selection = in.nextInt();
				in.nextLine();
				if ((selection == 1) || (selection == 2) || (selection == 3) || (selection == 4))
					selected = true;

				else
					System.out.println("Sorry but you have to choose an option between 1 and 4");

			} catch (InputMismatchException e) {
				System.out.println("Sorry you did not enter a valid option, please try again");

				in.next();
			}

		}

		return selection;

	}

	public void reservationSwitch() {
		boolean finish = false;
		if (finish == false) {
			int selection = reservationMenu();

			switch (selection) {

			case 1:
				this.Hotel.reservation();

				break;
			case 2:
				this.Hotel.selectResNo(0);
				break;
			case 3:
				this.Hotel.cancelReservation();
				break;
			case 4:
				menuSwitch();

			default:
				finish = true;
				break;
			}
			reservationSwitch();
		}
	}

	public int paymentMenu() {
		int selection = 0;

		System.out.println("----------------------------------------");
		System.out.println("    	Please  select option  	        ");
		System.out.println("****************************************");
		System.out.println("1) Outstanding Payments");
		System.out.println("2) Payment History ");
		System.out.println("3) Return");

		boolean selected = false;

		while (selected == false) {

			try {
				selection = in.nextInt();
				in.nextLine();
				if ((selection == 1) || (selection == 2) || (selection == 3))
					selected = true;

				else
					System.out.println("Sorry but you have to choose an option between 1 and 3");

			} catch (InputMismatchException e) {
				System.out.println("Sorry you did not enter a valid option, please try again");

				in.next();
			}

		}

		return selection;

	}

	public void paymentSwitch() {
		boolean finish = false;
		if (finish == false) {
			int selection = paymentMenu();

			switch (selection) {

			case 1:
				this.Hotel.pendingPayment(0);

				break;
			case 2:
				this.Hotel.loadFromBankFile();
				break;

			case 3:
				menuSwitch();

			default:
				finish = true;
				break;
			}
			paymentSwitch();
		}
	}

}
