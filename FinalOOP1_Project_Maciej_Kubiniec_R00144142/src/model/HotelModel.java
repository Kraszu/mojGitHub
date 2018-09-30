package model;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelModel implements Serializable {
	private ArrayList<Room> rooms;

	public HotelModel() {
		rooms = new ArrayList<Room>(); // instantiate of <Room>

	}

	// this method adding room to Room arrayList
	public void addRoom(Room room) {

		if (room.getMaxRoom() > this.getRooms().size()) {

			this.rooms.add(room);
			System.out.println("\nThe room number is: " + room.getRoomNo());
			System.out.println("\nThe reservation number is: " + room.getResNumber());
			System.out.println("\nGuest need to keep reservation number for any further operations! \n\n\tThank You\n");
		} else
			System.out.println("Sorry but all rooms of that type are booked");

	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

}
