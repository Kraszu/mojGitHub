package model;

public class Single extends Room {
	private int roomNo;
	private static int NUMBER_Of_THE_ROOM = 1;

	private final int MAX_NUM_ROOM = 6;

	public Single() {
		super(75.00);
		roomNo = NUMBER_Of_THE_ROOM;
		NUMBER_Of_THE_ROOM++;
	}

	public int getRoomNo() {
		return roomNo;

	}

	public int getMaxRoom() {
		return MAX_NUM_ROOM;
	}

}