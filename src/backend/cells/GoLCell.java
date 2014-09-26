package backend.cells;

import backend.patches.GoLPatch;
import backend.patches.Patch;

public class GoLCell extends Cell {
	public static int DEAD = 0;
	public static int ALIVE = 1;


	public GoLCell(int state, Patch patch) {
		myState = state;
		myPatch = patch;
		myPreviousState = state;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
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
