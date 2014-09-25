package backend.cells;


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
		myPossibleStates = 3;
	}

	@Override
	/**
	 * Updates the trees. 
	 */
	public void update() {
		if (myState == TREE && myUpdated == false) {
			myUpdated = true;
			if (anyNeighborIsBurning()) {
				double probabilityValue = Math.random();
				if (probabilityValue < myThresholdValue) {
					myState = BURNING;
				}
			}
		}
	}

	/**
	 * Updates the fires. Called first in updateGrid() because fire has priority over trees in turns of order.
	 */
	public void updateFire() {
		if (myState == BURNING && myUpdated == false) {
			myUpdated = true;
			myState = EMPTY;
		}
	}

	/**
	 * Method that checks if neighbors are in the state of burning.
	 * 
	 * @return returns whether any neighbors are burning.
	 */
	private boolean anyNeighborIsBurning() {
		for (Cell neighbor : myNeighbors)
		{
			if(neighbor.myState == BURNING) {
				return true;
			}
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
