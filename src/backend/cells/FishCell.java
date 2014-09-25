package backend.cells;

import backend.patch.EcoPatch;

public class FishCell extends AnimalCell {

	public FishCell(EcoPatch patch, int state) {
		myPatch = patch;
		myUpdated = false;
		myState = state;
		myTurnsAlive = 0;
	}

	/**
	 * Method containing the update logic for fish. Fish have lower priority.
	 */
	public void updateCell() {
		if (myUpdated == false) {
			super.updateNeighbors();
			myUpdated = true;
			myTurnsAlive++;
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				movetoNeighbor(emptyNeighbor);
				((FishCell) emptyNeighbor).checkandBreed(FISH);
			}
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
				((FishCell) emptyNeighbor).myTurnsAlive = 0;
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
		((FishCell) neighbor).myTurnsAlive = myTurnsAlive;
		clearCell();
	}
}