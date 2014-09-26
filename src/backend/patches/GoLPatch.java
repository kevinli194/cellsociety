/*package backend.patches;


public class GoLPatch extends Patch {
	private static final int DEAD = 1;
	private static final int ALIVE = 2;

	public GoLPatch(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myPreviousState = state;
		myState = state;
		myPossibleStates = 2;

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
	
	*//**
	 * method that gets a count of how many neighbors are in the ALIVE state.
	 * 
	 * @return returns the number of ALIVE state neighbors as an integer.
	 *//*

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
*/