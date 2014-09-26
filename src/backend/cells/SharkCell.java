package backend.cells;

import backend.patches.EcoPatch;
import backend.patches.Patch;


public class SharkCell extends EcoCell {

	protected int myTurnsStarved;
	private double myStarveTime;

	public SharkCell(Patch patch, double thresholdValue) {
		myPatch = patch;
		myState = SHARK;
		myBreedTime = thresholdValue;
		myStarveTime = thresholdValue;
		myTurnsAlive = 0;
		myTurnsStarved = 0;
	}

	@Override
	public void update() {
		if (myPatch.getUpdated() == false) {
			myPatch.setUpdated(true);
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

	private void eatFish(Patch fish) {
		myTurnsStarved = 0;
		((EcoPatch) fish).setState(EMPTY);
	}

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
