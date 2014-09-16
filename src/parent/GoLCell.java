package parent;

public class GoLCell extends Cell {
	private static final int DEAD = 1;
	private static final int ALIVE = 2;

	public GoLCell(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates = new int[2];
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (myUpdated == false) {
			if (myState == ALIVE && getAliveCount() < 2) {
				myPreviousState = myState;
				myState = DEAD;
				myUpdated = true;
				return;
			}
			if (myState == ALIVE
					&& (getAliveCount() == 2 || getAliveCount() == 3)) {
				myPreviousState = myState;
				myUpdated = true;
				return;
			}
			if (myState == ALIVE && getAliveCount() > 3) {
				myPreviousState = myState;
				myState = DEAD;
				myUpdated = true;
				return;
			}
			if (myState == DEAD && getAliveCount() == 3) {
				myPreviousState = myState;
				myState = ALIVE;
				myUpdated = true;
				return;
			}

		}
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		if (state.equals("DEAD")) {
			myState = DEAD;
		}
		if (state.equals("ALIVE")) {
			myState = ALIVE;
		}

	}

	public int getAliveCount() {
		int count = 0;
		for (int i = 0; i < myNeighbors.size(); i++) {
			if (myNeighbors.get(i).myState == ALIVE
					&& myNeighbors.get(i).myUpdated == false)
				count++;
			if (myNeighbors.get(i).myPreviousState == ALIVE
					&& myNeighbors.get(i).myUpdated == true)
				count++;
		}
		return count;
	}

}
