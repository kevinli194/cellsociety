package backend.cells;

import backend.patches.EcoPatch;
import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * FishCell is an extension of EcoCell.
 * FishCells can only move around the grid and reproduce after a certain number of rounds.
 */
public class FishCell extends EcoCell {

	/**
	 * Constructor for FishCell
	 * @param patch
	 * @param thresholdValue
	 * 		This represents the breeding time (number of rounds) for this fish.
	 */
	public FishCell(Patch patch, double thresholdValue) {
		myPatch = patch;
		myState = FISH;
		myBreedTime = thresholdValue;
		myTurnsAlive = 0;
	}

	/**
	 * Update method for fish is called after update for the sharks.
	 */
	@Override
	public void update() {
		if (myPatch.myUpdated == false) {
			myPatch.myUpdated = true;
			myTurnsAlive++;
			Patch emptyNeighbor = ((EcoPatch) myPatch).findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				((EcoPatch) myPatch).swapCells(emptyNeighbor);
				checkandBreed(FISH);
			}
		}
	}

}
