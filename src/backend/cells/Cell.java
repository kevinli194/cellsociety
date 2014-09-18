package backend.cells;

import java.util.ArrayList;

/*
 * The cell class is an abstract class that defines the shared characteristics of a cell. 
 * All cells have defined neighbors, coordinates on the grid, a state. It is sometimes important
 * to keep track of whether the cell has been updated that "turn", its previous state, and a threshold value
 * at which states change.
 */

public abstract class Cell {
	protected ArrayList<Cell> myNeighbors = new ArrayList<Cell>();
	protected int[] myCoordinates = new int[2];
	protected boolean myUpdated;
	protected int myState;
	protected int myPreviousState;
	protected double myThresholdValue;

	/*
	 * Method that adds a cell to the arrayList myNeighbors if it is found to be
	 * a neighbor in the simulation.
	 */
	public void addNeighbor(Cell neighbor) {
		myNeighbors.add(neighbor);
	}

	/*
	 * Method that returns the state of an object. Used when getting to state to
	 * display in CellViewer.
	 */

	public int getState() {
		return myState;
	}

	/*
	 * Method that sets the state of an object. Used when setting the initial
	 * state of the cells.
	 */
	public abstract void setState(String state);

	/*
	 * Resets the myUpdate of the cell for use in next frame. All empty cells
	 * (0) should remain as updated to skip for performance.
	 */
	public void reset() {
		if (myState != 0) {
			myUpdated = false;
		}
	}

	/*
	 * Abstract method that defines how each cell changes their state according
	 * to their neighbor each update cycle (turn/frame).
	 */
	public abstract void update();

}