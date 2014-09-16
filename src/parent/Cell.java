package parent;

import java.util.ArrayList;

public abstract class Cell {
	protected ArrayList<Cell> myNeighbors = new ArrayList<Cell>();
	protected int[] myCoordinates;
	protected boolean myUpdated;
	protected int myState;
	protected int myPreviousState;
	protected double myThresholdValue;

	public void addNeighbor(Cell neighbor) {
		myNeighbors.add(neighbor);
	}

	public int getState() {
		return myState;
	}

	// resets the myUpdate of the cell for use in next frame
	public void reset() {
		if (myState != 0) {
			myUpdated = false;
		}
	}

	public abstract void update();
	public abstract void setState(String state);

}