package parent;

import java.util.ArrayList;

public class SegCell extends Cell {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int Y = 2;
	private int myState;

	public SegCell(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
