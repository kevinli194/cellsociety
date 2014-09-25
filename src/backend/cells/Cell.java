package backend.cells;

import java.util.ArrayList;
import java.util.List;

import backend.patch.Patch;

/**
 * The cell class is an abstract class that defines the shared characteristics
 * of a cell. All cells have defined neighbors, coordinates on the grid, a
 * state. It is sometimes important to keep track of whether the cell has been
 * updated that "turn", its previous state, and a threshold value at which
 * states change.
 */

public abstract class Cell {
	protected Patch myPatch;
	protected List<Cell> myNeighbors = new ArrayList<Cell>();
	protected int[] myCoordinates = new int[2];
	protected boolean myUpdated;
	protected int myState;
	protected int myPreviousState;
	protected double myThresholdValue;
	public int myPossibleStates;

	/**
	 * Method that adds a cell to the arrayList myNeighbors.
	 * 
	 * @param neighbor
	 *            a cell that constitutes as a neighbor as defined by the
	 *            simulation.
	 */
	public void addNeighbor(Cell neighbor) {
		myNeighbors.add(neighbor);
	}
	
	public void updateNeighbors() {
		myNeighbors = myPatch.getCellNeighbors();
	}

	/**
	 * Method that returns the state of an object. Used when getting to state to
	 * display in CellViewer.
	 * 
	 * @return the state of the object (integer)
	 */

	public int getState() {
		return myState;
	}

	/**
	 * Method that sets the state of an object. Used when setting the initial
	 * state of the cells.
	 * 
	 * @param state
	 * the state of the object as defined as a string from the xml
	 */
	public abstract void setState(String state);

	/**
	 * Resets the myUpdate of the cell for use in next frame. All empty cells
	 * (0) should remain as updated to skip for performance.
	 */
	public void reset() {
		if (myState != 0) {
			myUpdated = false;
		}
	}
	
	/**
	 * Changes the state of the cell to the next state allowing for dynamic
	 * user interaction
	 * 
	 */
	public int changedState() {
		return (myState = (myState+1)% myPossibleStates);
	}		
	

	/**
	 * Abstract method that defines how each cell changes their state according
	 * to their neighbor each update cycle (turn/frame).
	 */
	public abstract void updateCell();

}