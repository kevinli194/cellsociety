package backend.cells;

import java.util.Collections;

public class EcoCell extends Cell {

	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private int myTurnsAlive;
	private int myStarveTime;

	public EcoCell(int xCoord, int yCoord, boolean update, int state,
			int breedingTime) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		myThresholdValue = breedingTime;
		myStarveTime = 2 * breedingTime - 1;
		myTurnsAlive = 0;
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
				updateNeighbor(fishNeighbor, EMPTY);
			} else {
				if(deathOfShark())
					return;
			}
			if (emptyNeighbor != null) {
				updateNeighbor(emptyNeighbor, SHARK);
				breedorMove();
				myTurnsAlive = 0;
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
				updateNeighbor(emptyNeighbor, FISH);
				breedorMove();
				myTurnsAlive = 0;
			}
		}
	}

	/*
	 * Method for determining whether shark will die from starvation
	 */
	private boolean deathOfShark() {
		if (myTurnsAlive >= myStarveTime)
		{
			myState = EMPTY;
			myTurnsAlive = 0;
			return true;
		}
		return false;
	}

	/*
	 * Method for determining whether fish/shark can breed and reproduce
	 */

	private void breedorMove() {
		if (myTurnsAlive < myThresholdValue) {
			myState = EMPTY;
		}
	}

	private void updateNeighbor(Cell neighbor, int state) {
		neighbor.myState = state;
		neighbor.myUpdated = true;
		if (state != EMPTY) {
			((EcoCell) neighbor).myTurnsAlive = myTurnsAlive;
		} else {
			myTurnsAlive = 0;
		}
	}

	private Cell findNeighborOfType(int stateID) {
		for (Cell neighbor : myNeighbors) {
			if (neighbor.myState == stateID) {
				return neighbor;
			}
		}
		Collections.shuffle(myNeighbors);
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