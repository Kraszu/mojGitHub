package model;

public class Suite extends Room {
	private int roomNo;
	private static int NUMBER_Of_THE_ROOM = 13;

	private final int MAX_NUM_ROOM = 3;

	public Suite() {
		super(150.00);
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
