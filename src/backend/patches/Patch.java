package backend.patches;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import backend.cells.Cell;

/**
 * The cell class is an abstract class that defines the shared characteristics
 * of a cell. All cells have defined neighbors, coordinates on the grid, a
 * state. It is sometimes important to keep track of whether the cell has been
 * updated that "turn", its previous state, and a threshold value at which
 * states change.
 */

public abstract class Patch {
	protected List<Patch> myNeighbors = new ArrayList<Patch>();
	protected int[] myCoordinates = new int[2];
	protected boolean myUpdated;
	protected int myState;
	protected int myPreviousState;
	protected double myThresholdValue;
	protected int myPossibleStates;
	protected Cell myCell;
	protected boolean isEmpty;
	
	public Patch(int xCoord, int yCoord, double thresholdValue)
	{
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myThresholdValue = thresholdValue;
		myUpdated = false;
	}
	
	/**
	 * Method that adds a cell to the arrayList myNeighbors.
	 * 
	 * @param neighbor
	 *            a cell that constitutes as a neighbor as defined by the
	 *            simulation.
	 */
	public void addNeighbor(Patch neighbor) {
		myNeighbors.add(neighbor);
	}

	public List<Cell> getCellNeighbors() {
		List<Cell> cellNeighborsList = new ArrayList<Cell>();
		for (Patch p : myNeighbors) {
			cellNeighborsList.add(p.myCell);
		}
		return cellNeighborsList;

	}

	public List<Patch> getPatchNeighbors() {
		return myNeighbors;
	}

	/**
	 * Method that returns the state of an object. Used when getting to state to
	 * display in CellViewer.
	 * 
	 * @return the state of the object (integer)
	 */

	public abstract Color getColor();

	public void updateCell() {
		myCell.update();
	}

	public boolean getUpdated() {
		return myUpdated;
	}

	public void setUpdated(boolean updated) {
		myUpdated = updated;
	}

	public void setCell(Cell cell) {
		myCell = cell;
	}

	/**
	 * Method that sets the state of an object. Used when setting the initial
	 * state of the cells.
	 * 
	 * @param state
	 *            the state of the object as defined as a string from the xml
	 */

	/**
	 * Resets the myUpdate of the cell for use in next frame. All empty cells
	 * (0) should remain as updated to skip for performance.
	 */
	public void reset() {
		myUpdated = false;
	}

	public int getState() {
		return myState;
	}

	public void setState(int state) {
		myState = state;
	}

	public Cell getCell() {
		return myCell;
	}

	public abstract void setInitialState(String state);

	/**
	 * Changes the state of the cell to the next state allowing for dynamic user
	 * interaction
	 * 
	 */
	public int changedState() {
		myCell.setState((myCell.getState() + 1) % myPossibleStates);
		return myCell.getState();
	}

	/**
	 * Abstract method that defines how each cell changes their state according
	 * to their neighbor each update cycle (turn/frame).
	 */
	public abstract void update();
	
	/**
	 * Swap between this patch's and another patch's cells.
	 * @param patch
	 * 		The patch to switch to
	 */
	public void swapCells(Patch patch) {
		Cell tempCell = patch.myCell;
		patch.myCell = myCell;
		myCell.setPatch(patch);
		myCell = tempCell;
	}

}