package model;

public class Double extends Room {
	private int roomNo;
	private static int NUMBER_OF_THE_ROOM = 7;

	private final int MAX_NUM_ROOM = 6;

	public Double() {
		super(100.00);
		roomNo = NUMBER_OF_THE_ROOM;
		NUMBER_OF_THE_ROOM++;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public int getMaxRoom() {
		return MAX_NUM_ROOM;
	}
}
