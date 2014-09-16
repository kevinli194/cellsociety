package parent;

import java.util.ArrayList;

public abstract class Cell {
	protected ArrayList<Cell> myNeighbor = new ArrayList<Cell>();
	protected int[] myCoordinates;
	protected boolean myUpdate;
	protected int myState;
	protected int myPreviousState;

	public void addNeighbor(Cell neighbor) {
		myNeighbor.add(neighbor);
	}

	public int getState() {
		return myState;
	}

	// resets the myUpdate of the cell for use in next frame
	public void reset() {
		if (myState != 0) {
			myUpdate = false;
		}
	}

	public abstract void update();
	public abstract void setState(String state);

}