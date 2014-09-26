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
	public boolean myUpdated;
	public int myState;
	protected int myPreviousState;
	protected double myThresholdValue;
	protected int myPossibleStates;
	public Cell myCell;
	protected boolean isEmpty;

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
		List<Cell> cellNeighborsList =  new ArrayList<Cell>();
		for (Patch p: myNeighbors) {
			cellNeighborsList.add(p.myCell);
		}
		return cellNeighborsList;

	}
	
	public List<Patch> getPatchNeighbors() {
		return myNeighbors;
	}

	public void addCell(Cell cell) {
		myCell = cell;
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

	public boolean isEmpty() {
		return myCell == null;
	}
	
	public abstract void setState(String state);


	/**
	 * Changes the state of the cell to the next state allowing for dynamic user
	 * interaction
	 * 
	 */
	public int changedState() {
		return (myCell.myState = (myCell.myState + 1) % myPossibleStates);
	}

	/**
	 * Abstract method that defines how each cell changes their state according
	 * to their neighbor each update cycle (turn/frame).
	 */
	public abstract void update();

}