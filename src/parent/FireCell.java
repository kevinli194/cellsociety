package parent;

public class FireCell extends Cell {
	private static final int EMPTY = 0;
	private static final int TREE = 1;
	private static final int BURNING = 2;

	public FireCell(int xCoord, int yCoord, boolean update, int state,
			double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		myThresholdValue = thresholdValue;
	}

	@Override
	public void update() {
		if (myState == TREE && myUpdated == false) {
			//myPreviousState = myState;
			myUpdated = true;
			if (anyNeighborIsBurning()) {
				double probabilityValue = Math.random();
				if (probabilityValue < myThresholdValue) {
					myState = BURNING;
				}
			}
		}
	}

	public void update2() {
		if (myState == BURNING && myUpdated == false) {
		//	myPreviousState = myState;
			myUpdated = true;
			myState = EMPTY;
		}
	}

	private boolean anyNeighborIsBurning() {
		for (int i = 0; i < myNeighbors.size(); i++) {
			if (myNeighbors.get(i).myState == BURNING) {
				return true;
			}
		/*	if (myNeighbors.get(i).myPreviousState == BURNING
					&& myNeighbors.get(i).myUpdated == true) {
				return true;
			}*/
		}
		return false;
	}

	@Override
	public void setState(String state) {
		if (state.equals("EMPTY")) {
			myState = EMPTY;
		}
		if (state.equals("TREE")) {
			myState = TREE;
			myUpdated = false;
		}
		if (state.equals("BURNING")) {
			myState = BURNING;
			myUpdated = false;
		}
	}
}
