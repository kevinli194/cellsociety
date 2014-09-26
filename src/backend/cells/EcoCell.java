package backend.cells;

import backend.patches.EcoPatch;
import backend.patches.Patch;

public abstract class EcoCell extends Cell {
	protected static int SHARK = 2;
	protected static int FISH = 1;
	protected static int EMPTY = 0;
	protected int myTurnsAlive;
	protected double myBreedTime;

	protected void checkandBreed(int type) {
		if (myTurnsAlive >= myBreedTime) {
			Patch emptyNeighbor = ((EcoPatch) myPatch).findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				((EcoPatch) emptyNeighbor).setState(type);
			}
		}
	}
}
