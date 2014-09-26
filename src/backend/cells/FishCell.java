package backend.cells;


import backend.patches.EcoPatch;
import backend.patches.Patch;

public class FishCell extends EcoCell {

	public FishCell(Patch patch, double thresholdValue) {
		myPatch = patch;
		myState = FISH;
		myBreedTime = thresholdValue;
		myTurnsAlive = 0;
	}

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
