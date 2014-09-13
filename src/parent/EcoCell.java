package parent;

import java.util.ArrayList;

public class EcoCell extends Cell {
	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private int myState;
	private int myTurnsAlive;

	public EcoCell(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
		myTurnsAlive = 0;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
