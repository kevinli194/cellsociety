package backend.cells;

public class EcoCell extends Cell {

	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private int myTurnsAlive;

	public EcoCell(int xCoord, int yCoord, boolean update, int state,
			int breedingTime) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		myThresholdValue = breedingTime;
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
			Cell fishNeighbor = findNeighborOfType(FISH);
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (fishNeighbor != null) {
				System.out.print("Found a fish! ");
				updateNeighbor(fishNeighbor, EMPTY);
			}
			if (emptyNeighbor != null) {

				updateNeighbor(emptyNeighbor, SHARK);
				breed();
			}
			myTurnsAlive++;
		} else {
			myTurnsAlive = 0;
		}

		myTurnsAlive++;

	}

	/*
	 * Method containing the update logic for fish. Fish have lower priority
	 * than shark.
	 */
	public void update2() {
		if (myState == FISH && myUpdated == false) {
			myUpdated = true;
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				updateNeighbor(emptyNeighbor, FISH);
				breed();
				myTurnsAlive = 0;
			}

			myTurnsAlive++;
		} else {
			myTurnsAlive = 0;
		}

		myTurnsAlive++;

	}

	/*
	 * Method for determining whether fish/shark can breed and reproduce
	 */

	private void breed() {
		if (myTurnsAlive < myThresholdValue) {
			myState = EMPTY;
		}
	}

	private void updateNeighbor(Cell neighbor, int state) {
		neighbor.myPreviousState = neighbor.myState;
		neighbor.myState = state;
		((EcoCell) neighbor).myTurnsAlive = myTurnsAlive;

		neighbor.myUpdated = true;

	}

	private Cell findNeighborOfType(int stateID) {
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