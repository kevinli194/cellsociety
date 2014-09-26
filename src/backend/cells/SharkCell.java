package backend.cells;

import backend.patches.EcoPatch;
import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * SharkCell is an extension of EcoCell.
 * SharkCells can move around the grid, reproduce after a certain number of rounds,
 * and eat neighboring fish. Sharks will die if they do not eat within a certain 
 * number of rounds.
 *
 */
public class SharkCell extends EcoCell {
	protected int myTurnsStarved;
	private double myStarveTime;

	/**
	 * Constructor for SharkCell
	 * @param patch
	 * @param thresholdValue
	 * 		This represents the breeding time (number of rounds) for this shark.
	 */
	public SharkCell(Patch patch, double thresholdValue) {
		myPatch = patch;
		myState = SHARK;
		myBreedTime = thresholdValue;
		myStarveTime = thresholdValue;
		myTurnsAlive = 0;
		myTurnsStarved = 0;
	}

	/**
	 * Update method for shark is called after update for the fish.
	 */
	@Override
	public void update() {
		if (myPatch.myUpdated == false) {
			myPatch.myUpdated = true;
			myTurnsAlive++;
			Patch fishNeighbor = ((EcoPatch) myPatch).findNeighborOfType(FISH);
			Patch emptyNeighbor = ((EcoPatch) myPatch).findNeighborOfType(EMPTY);
			if (fishNeighbor != null) {
				eatFish(fishNeighbor);
				checkandBreed(SHARK);
			} else {
				boolean sharkDead = checkSharkDeath();
				if (emptyNeighbor != null && !sharkDead) {
					((EcoPatch) myPatch).swapCells(emptyNeighbor);
					checkandBreed(SHARK);
				} else {
					if (!sharkDead) {
						checkandBreed(SHARK);
					}
				}
			}
		}
	}

	/**
	 * Eat a neighboring FishCell by making the cell Empty.
	 * @param fish
	 * 		fish represents the patch containing the neighboring FishCell.
	 */
	private void eatFish(Patch fish) {
		myTurnsStarved = 0;
		((EcoPatch) fish).setState(EMPTY);
	}

	/**
	 * Check to see if the shark has not eaten in time.
	 * @return
	 * 		true for shark dead, false for shark still alive
	 */
	protected boolean checkSharkDeath() {
		if (myTurnsStarved >= myStarveTime) {
			((EcoPatch) myPatch).setState(EMPTY);
			return true;
		} else {
			myTurnsStarved++;
			return false;
		}

	}
}
