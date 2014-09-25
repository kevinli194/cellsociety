package backend.cells;

public class FireCell extends Cell {
	private static final int EMPTY = 0;
	private static final int BURNING = 2;
	
	public FireCell(double thresholdValue) {
		myUpdated = false;
		myState = EMPTY;
		myThresholdValue = thresholdValue;
	}

	@Override
	/**
	 * Updates the fires. Called first in updateGrid() because fire has priority over trees in turns of order.
	 */
	public void updateCell() {
		if (myState == BURNING && myUpdated == false)
		{
			myUpdated = true;
			myState = EMPTY;
		}
	}

	@Override
	public void setState(String state) {
		if (state.equals("EMPTY")) {
			myState = EMPTY;
		}
		if (state.equals("BURNING")) {
			myState = BURNING;
			myUpdated = false;
		}
	}
}
