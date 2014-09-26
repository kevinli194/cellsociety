package backend.cells;

import java.util.List;

import backend.patches.Patch;
import backend.patches.SugarPatch;

public class SugarAgentCell extends Cell {

	private static final int DEAD = 0;
	private static final int ALIVE = 1;
	private int mySugarAmount;
	private int mySugarMetabolism;

	public SugarAgentCell(SugarPatch patch) {
		myPatch = patch;
		mySugarMetabolism = 3;
	}

	@Override
	public void update() {
		if (myPatch.myUpdated == false && myState == ALIVE) {
			List<Patch> myPatchNeighbors = myPatch.getPatchNeighbors();
			int maximumSugarAmongNeighbors = Integer.MIN_VALUE;
			SugarPatch maximumNeighbor = null;

			for (Patch neighbor : myPatchNeighbors) {
				int neighborSugar = ((SugarPatch) neighbor).myState;
				if (neighborSugar > maximumSugarAmongNeighbors) {
					maximumSugarAmongNeighbors = neighborSugar;
					maximumNeighbor = (SugarPatch) neighbor;
				}
			}

			maximumNeighbor.swapCells(myPatch);
			mySugarAmount += myPatch.myState;
			mySugarAmount -= mySugarMetabolism;

			if (mySugarAmount < 0) {
				myState = DEAD;
			}
		}
	}
}