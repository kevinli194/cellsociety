package backend.cells;

import backend.patches.EcoPatch;
import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * EcoCell is an abstract extension of Cell for the Wa-Tor World Simulation.
 * EcoCell can be extended to FishCell or SharkCell. 
 */
public abstract class EcoCell extends Cell {
	protected static int EMPTY = 0;
	protected static int FISH = 1;
	protected static int SHARK = 2;
	protected int myTurnsAlive;
	protected double myBreedTime;

	/**
	 * The common feature of Sharks and Fish is that both breed
	 * after a certain number of rounds have completed.
	 * @param type
	 * 		The state or type this particular EcoCell is.
	 */
	protected void checkandBreed(int type) {
		if (myTurnsAlive >= myBreedTime) {
			Patch emptyNeighbor = ((EcoPatch) myPatch).findNeighborOfType(EMPTY);
			if (emptyNeighbor != null) {
				((EcoPatch) emptyNeighbor).setState(type);
			}
		}
	}
}
