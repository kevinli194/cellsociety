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
		myStarveTime = breedingTime -1;
		myTurnsAlive = 0;
		myTurnsStarved = 0;
	}

	@Override
	/*
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

	/*
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

	/*
	 * Method for determining whether shark will die from starvation
	 */
	private void eatFish(Cell fish) {
		myTurnsStarved = 0;
		((EcoCell) fish).clearCell();
	}

	private boolean checkSharkDeath() {
		if (myTurnsStarved >= myStarveTime) {
			clearCell();
			return true;

		} else {
			myTurnsStarved++;
			return false;
		}

	}

	private void clearCell() {
		myState = EMPTY;
		myUpdated = true;
		myTurnsStarved = 0;
		myTurnsAlive = 0;
	}

	/*
	 * Method for determining whether fish/shark can breed and reproduce
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

	private void movetoNeighbor(Cell neighbor) {
		neighbor.myState = myState;
		neighbor.myUpdated = true;
		((EcoCell) neighbor).myTurnsAlive = myTurnsAlive;
		((EcoCell) neighbor).myTurnsStarved = myTurnsStarved;
		clearCell();
	}

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