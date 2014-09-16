package parent;

public class FireCell extends Cell {
	private static final int EMPTY = 0;
	private static final int TREE = 1;
	private static final int BURNING = 2;
	private double myThresholdValue;

	public FireCell(int xCoord, int yCoord, boolean update, int state,
			double thresholdValue) {
		myCoordinates = new int[2];
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
		myThresholdValue = thresholdValue;
	}

	@Override
	public void update() {
		if (myState == TREE && myUpdate == false) {
			myPreviousState = myState;
			myUpdate = true;
			if (anyNeighborIsBurning()) {
				double probabilityValue = Math.random();
				if (probabilityValue < myThresholdValue) {
					myState = BURNING;
				}

			}
		}
	}

	public void update2() {
		// TODO Auto-generated method stub
		if (myState == BURNING && myUpdate == false) {
			myPreviousState = myState;
			myUpdate = true;
			myState = EMPTY;
		}
	}

	private boolean anyNeighborIsBurning() {
		for (int i = 0; i < myNeighbor.size(); i++) {
			if (myNeighbor.get(i).myState == BURNING
					&& myNeighbor.get(i).myUpdate == false) {
				return true;
			}
			if (myNeighbor.get(i).myPreviousState == BURNING
					&& myNeighbor.get(i).myUpdate == true) {
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
		}
		if (state.equals("BURNING")) {
			myState = BURNING;
		}
	}

}
