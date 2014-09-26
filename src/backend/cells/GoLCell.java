package backend.cells;

import backend.patches.GoLPatch;
import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * GoLCell is the Cell for the Game of Life simulation.
 * GoLCell assumes two states - dead or alive.
 */
public class GoLCell extends Cell {
	public static int DEAD = 0;
	public static int ALIVE = 1;

	/**
	 * Constructor for GoLCell
	 * @param state
	 * @param patch
	 */
	public GoLCell(int state, Patch patch) {
		myState = state;
		myPatch = patch;
		myPreviousState = state;
	}

	/**
	 * Updating GoLCell only affects this cell,
	 * does not have an impact on neighbors or other cells.
	 */
	@Override
	public void update() {
		if (myPatch.getUpdated() == false) {
			if (myState == ALIVE && ((GoLPatch)myPatch).getAliveCount() < 2) {
				myPreviousState = myState;
				myState = DEAD;
				myPatch.setUpdated(true);
				return;
			}
			if (myState == ALIVE
					&& (((GoLPatch)myPatch).getAliveCount() == 2 || ((GoLPatch)myPatch).getAliveCount() == 3)) {
				myPreviousState = myState;
				myPatch.setUpdated(true);
				return;
			}
			if (myState == ALIVE && ((GoLPatch)myPatch).getAliveCount() > 3) {
				myPreviousState = myState;
				myState = DEAD;
				myPatch.setUpdated(true);
				return;
			}
			if (myState == DEAD && ((GoLPatch)myPatch).getAliveCount() == 3) {
				myPreviousState = myState;
				myState = ALIVE;
				myPatch.setUpdated(true);
				return;
			}
		}
	}
}
