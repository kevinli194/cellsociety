package backend.cells;

import backend.patch.GoLPatch;

public class GoLCell extends Cell {
	private static final int DEAD = 1;
	private static final int ALIVE = 2;

	public GoLCell(GoLPatch patch) {
		myPatch = patch;
		myUpdated = false;
		myPreviousState = DEAD;
		myState = DEAD;
		myPossibleStates = 2;

	}

	@Override
	public void updateCell() {
		super.updateNeighbors();

		if (myState == ALIVE && getAliveCount() < 2) {
			myPreviousState = myState;
			myState = DEAD;
			return;
		}
		if (myState == ALIVE
				&& (getAliveCount() == 2 || getAliveCount() == 3)) {
			myPreviousState = myState;
			return;
		}
		if (myState == ALIVE && getAliveCount() > 3) {
			myPreviousState = myState;
			myState = DEAD;
			return;
		}
		if (myState == DEAD && getAliveCount() == 3) {
			myPreviousState = myState;
			myState = ALIVE;
			return;

		}
	}

	@Override
	public void setState(String state) {
		if (state.equals("DEAD")) {
			myState = DEAD;
		}
		if (state.equals("ALIVE")) {
			myState = ALIVE;
		}
	}

	/**
	 * method that gets a count of how many neighbors are in the ALIVE state.
	 * 
	 * @return returns the number of ALIVE state neighbors as an integer.
	 */
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
