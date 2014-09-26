package backend.patches;

import java.util.Collections;

import backend.cells.Cell;
import backend.cells.EmptyCell;
import backend.cells.FishCell;
import backend.cells.SharkCell;
import javafx.scene.paint.Color;

/**
 * 
 * @author CS308 Team16
 * EcoPatch is the Patch for the Wa-Tor World Simulation.
 * EcoPatch stores EmptyCell, SharkCell, or FishCell, and its primarily role
 * is to switch between the different cell states and instantiate new cells where appropriate.
 */

public class EcoPatch extends Patch {
	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;

	/**
	 * Constructor for EcoPatch
	 * @param xCoord
	 * @param yCoord
	 * @param thresholdValue
	 * 		Breeding time that will be assigned to the Cells that occupy this Patch.
	 */
	public EcoPatch(int xCoord, int yCoord, double thresholdValue) {
		super(xCoord, yCoord, thresholdValue);
		myUpdated = false;
		myCell = new EmptyCell(this);
		myPossibleStates = 3;
	}

	/**
	 * Returns the color for the cell's state.
	 */
	@Override
	public Color getColor() {
		if (myCell.getState() == FISH)
			return Color.YELLOW;
		if (myCell.getState() == SHARK)
			return Color.GRAY;
		else{
			return Color.BLUE;
		}

	}

	/**
	 * Sets the initial state of the cell stored at this patch.
	 */
	@Override
	public void setInitialState(String state) {
		if (state.equals("EMPTY")) {
			myCell = new EmptyCell(this);
		}
		if (state.equals("FISH")) {
			myCell = new FishCell(this, myThresholdValue);

		}
		if (state.equals("SHARK")) {
			myCell = new SharkCell(this, myThresholdValue);
		}

	}

	/**
	 * Allows for easily switching the cell stored at this patch.
	 */
	public void setState(int type) {
		if (type == EMPTY) {
			myCell = new EmptyCell(this);
		}
		if (type == FISH) {
			myCell = new FishCell(this, myThresholdValue);

		}
		if (type == SHARK) {
			myCell = new SharkCell(this, myThresholdValue);
		}

	}

	@Override
	public void update() {	}
	
	/**
	 * Updating for sharks is called first.
	 */
	public void updateShark(){
		if (myCell.getState() ==SHARK)
		myCell.update();
	}
	
	/**
	 * Updating for fish is called second.
	 */
	public void updateFish(){
		if (myCell.getState() == FISH)
			myCell.update();		
	}

	/**
	 * Searches through the neighbors for a patch holding a particular cell.
	 * @param stateID
	 * @return
	 * 		Patch neighbor containing the cell of input state.
	 */
	public Patch findNeighborOfType(int stateID) {
		Collections.shuffle(myNeighbors);
		for (Patch neighbor : myNeighbors) {
			if (neighbor.myCell.getState() == stateID) {
				return neighbor;
			}
		}
		return null;
	}

	public void swapCells(Patch patch) {
		Cell tempCell = patch.myCell;
		patch.myCell = myCell;
		myCell.setPatch(patch);
		myCell = tempCell;
	}

	/**
	 * Set the patch's cell to an EmptyCell.
	 */
	public void clearCell() {
		myCell = new EmptyCell(this);
		myUpdated = true;
	}

}
