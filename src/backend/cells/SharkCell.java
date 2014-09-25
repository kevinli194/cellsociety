package backend.cells;

import backend.patch.EcoPatch;

public class SharkCell extends EcoCell {
	private int myStarveTime;

	public SharkCell(EcoPatch patch, int state, int breedingTime) {
		myPatch = patch;
		myUpdated = false;
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
	public void updateCell() {
		if (myUpdated == false) {
			super.updateNeighbors();
			myUpdated = true;
			myTurnsAlive++;
			EcoCell fishNeighbor = findNeighborOfType(FISH);
			EcoCell emptyNeighbor = findNeighborOfType(EMPTY);
			if (fishNeighbor != null) {
				eatFish(fishNeighbor);
				checkandBreed(SHARK);
			} else {
				boolean sharkDead = checkSharkDeath();
				if (emptyNeighbor != null && !sharkDead) {
					movetoNeighbor(emptyNeighbor);
					((SharkCell) emptyNeighbor).checkandBreed(SHARK);
				} else {
					if (!sharkDead) {
						checkandBreed(SHARK);
					}
				}
			}
		}
	}

	/**
	 * Method for eating a fish. Takes in a cell to clear. Also sets turns
	 * starved to 0, because the shark is now full.
	 * 
	 * @param fish Cell to be eaten.
	 */
	private void eatFish(Cell fish) {
		myTurnsStarved = 0;
		((SharkCell) fish).clearCell();
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

	protected boolean checkSharkDeath() {
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
			EcoCell emptyNeighbor = findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				emptyNeighbor.myState = type;
				emptyNeighbor.myUpdated = true;
				((SharkCell) emptyNeighbor).myTurnsAlive = 0;
				((SharkCell) emptyNeighbor).myTurnsStarved = 0;
			}
		}
	}

	/**
	 * moves the contents in the current cell to a neighboring cell and clears
	 * the contents of the current cell.
	 * 
	 * @param neighbor The cell to move to.
	 */

	private void movetoNeighbor(EcoCell neighbor) {
		neighbor.myState = myState;
		neighbor.myUpdated = true;
		((SharkCell) neighbor).myTurnsAlive = myTurnsAlive;
		((SharkCell) neighbor).myTurnsStarved = myTurnsStarved;
		clearCell();
	}
}