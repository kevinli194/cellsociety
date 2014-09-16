package parent;

public class GoLCell extends Cell {
	private static final int DEAD = 1;
	private static final int ALIVE = 2;

	public GoLCell(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates = new int[2];
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (myUpdate == false) {
			if (myState == ALIVE && getAliveCount() < 2) {
				myPreviousState = myState;
				myState = DEAD;
				myUpdate = true;
				return;
			}
			if (myState == ALIVE
					&& (getAliveCount() == 2 || getAliveCount() == 3)) {
				myPreviousState = myState;
				myUpdate = true;
				return;
			}
			if (myState == ALIVE && getAliveCount() > 3) {
				myPreviousState = myState;
				myState = DEAD;
				myUpdate = true;
				return;
			}
			if (myState == DEAD && getAliveCount() == 3) {
				myPreviousState = myState;
				myState = ALIVE;
				myUpdate = true;
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
		for (int i = 0; i < myNeighbor.size(); i++) {
			if (myNeighbor.get(i).myState == ALIVE
					&& myNeighbor.get(i).myUpdate == false)
				count++;
			if (myNeighbor.get(i).myPreviousState == ALIVE
					&& myNeighbor.get(i).myUpdate == true)
				count++;
		}
		return count;
	}

}
