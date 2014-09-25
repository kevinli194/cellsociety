package backend.cells;

import java.util.Collections;

public abstract class EcoCell extends Cell {
	protected static final int EMPTY = 0;
	protected static final int FISH = 1;
	protected static final int SHARK = 2;
	protected int myTurnsStarved;
	protected int myTurnsAlive;
	
	/**
	 * Method for clearing the contents of a cell to empty. Useful method call
	 * when calling methods that require the cell to be emptied, such as
	 * eatFish.
	 */
	protected void clearCell() {
		myState = EMPTY;
		myUpdated = true;
		myTurnsStarved = 0;
		myTurnsAlive = 0;
	}
	
	public void setState(String state) {
		if (state.equals("EMPTY")) {
			myState = EMPTY;
		}
		if (state.equals("FISH")) {
			myState = FISH;
			myUpdated = false;
		}
		if (state.equals("SHARK")) {
			myState = SHARK;
			myUpdated = false;
		}
	}
	
	/**
	 * Finds the a neighboring cell that has the given state randomly.
	 * 
	 * @param stateID The state (type) of cell we are looking for.
	 * 
	 * @return Cell returns the neighboring cell that contains the state we are
	 * looking for.
	 */

	protected EcoCell findNeighborOfType(int stateID) {
		Collections.shuffle(myNeighbors);
		for (Cell neighbor : myNeighbors) {
			if (neighbor.myState == stateID) {
				return (EcoCell) neighbor;
			}
		}
		return null;
	}
}
