package backend.cells;

import java.util.Collections;

public class EcoCell extends Cell {

	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private int myTurnsAlive;
	private int myStarveTime;
	private int myTurnsStarved;

	public EcoCell(int xCoord, int yCoord, boolean update, int state,
			int breedingTime) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		myThresholdValue = breedingTime;
		myStarveTime = breedingTime - 1;
		myTurnsAlive = 0;
		myTurnsStarved = 0;
	}

	@Override
	/**
	 * Method containing the update logic if the state is a shark. Sharks hold
	 * priority over fish when updating.
	 */
	public void update() {
		if (myState == SHARK && myUpdated == false) {
			myUpdated = true;
			myTurnsAlive++;
			Cell fishNeighbor = findNeighborOfType(FISH);
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (fishNeighbor != null) {
				eatFish(fishNeighbor);
				checkandBreed(SHARK);
			} else {
				boolean sharkDead = checkSharkDeath();
				if (emptyNeighbor != null && !sharkDead) {
					movetoNeighbor(emptyNeighbor);
					((EcoCell) emptyNeighbor).checkandBreed(SHARK);
				} else {
					if (!sharkDead) {
						checkandBreed(SHARK);
					}
				}
			}
		}
	}

	/**
	 * Method containing the update logic for fish. Fish have lower priority
	 * than shark.
	 */
	public void updateFish() {
		if (myState == FISH && myUpdated == false) {
			myUpdated = true;
			myTurnsAlive++;
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				movetoNeighbor(emptyNeighbor);
				((EcoCell) emptyNeighbor).checkandBreed(FISH);
			}
		}
	}

	/**
	 * Method for clearing the contents of a cell to empty. Useful method call
	 * when calling methods that require the cell to be emptied, such as
	 * eatFish.
	 */
	private void clearCell() {
		myState = EMPTY;
		myUpdated = true;
		myTurnsStarved = 0;
		myTurnsAlive = 0;
	}

	/**
	 * Method for eating a fish. Takes in a cell to clear. Also sets turns
	 * starved to 0, because the shark is now full.
	 * 
	 * @param fish Cell to be eaten.
	 */
	private void eatFish(Cell fish) {
		myTurnsStarved = 0;
		((EcoCell) fish).clearCell();
	}

	/**
	 * Checks to see if the shark has passed dued to starvation. Called when it
	 * has no access to food. Kills off the shark if its past the starve time
	 * and returns true to indicate that the shark is dead. Increments the turns
	 * it is not eaten if it has not starved to death yet and returns false.
	 * 
	 * @return Returns whether the shark is dead (true) or alive (false) as a
	 * boolean.
	 */

	private boolean checkSharkDeath() {
		if (myTurnsStarved >= myStarveTime) {
			clearCell();
			return true;

		} else {
			myTurnsStarved++;
			return false;
		}

	}

	/**
	 * Method for checking whether a fish or shark can breed and then proceeding
	 * to breed.
	 * 
	 * @param type which type of animal you are breeding given as a state
	 * (int).
	 */

	private void checkandBreed(int type) {
		if (myTurnsAlive >= myThresholdValue) {
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				emptyNeighbor.myState = type;
				emptyNeighbor.myUpdated = true;
				((EcoCell) emptyNeighbor).myTurnsAlive = 0;
				((EcoCell) emptyNeighbor).myTurnsStarved = 0;
			}
		}
	}

	/**
	 * moves the contents in the current cell to a neighboring cell and clears
	 * the contents of the current cell.
	 * 
	 * @param neighbor The cell to move to.
	 */

	private void movetoNeighbor(Cell neighbor) {
		neighbor.myState = myState;
		neighbor.myUpdated = true;
		((EcoCell) neighbor).myTurnsAlive = myTurnsAlive;
		((EcoCell) neighbor).myTurnsStarved = myTurnsStarved;
		clearCell();
	}

	/**
	 * Finds the a neighboring cell that has the given state randomly.
	 * 
	 * @param stateID The state (type) of cell we are looking for.
	 * 
	 * @return Cell returns the neighboring cell that contains the state we are
	 * looking for.
	 */

	private Cell findNeighborOfType(int stateID) {
		Collections.shuffle(myNeighbors);
		for (Cell neighbor : myNeighbors) {
			if (neighbor.myState == stateID) {
				return neighbor;
			}
		}
		return null;
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
}