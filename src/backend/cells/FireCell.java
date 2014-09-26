package backend.cells;

import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * FireCell is the Cell for the Spreading of Fire Simulation.
 * Each FireCell assumes two states - burning or not burning (empty).
 */
public class FireCell extends Cell {
	public static int NOT_BURNING = 0;
	public static int BURNING = 1;

	/**
	 * Constructor for FireCell
	 * @param state
	 * @param patch
	 */
	public FireCell(int state, Patch patch) {
		myState = state;
		myPatch = patch;
	}

	/**
	 * Updates the fires. Called first in updateGrid() because fire has priority
	 * over trees in turns of order.
	 */
	@Override
	public void update() {
		if (myPatch.myUpdated == false) {
			if (myState == BURNING)
				myState = NOT_BURNING;
		}
		myPatch.myUpdated = true;
	}

}
