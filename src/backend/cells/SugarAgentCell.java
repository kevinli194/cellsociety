package backend.cells;

import java.util.List;

import backend.patches.Patch;
import backend.patches.SugarPatch;

public class SugarAgentCell extends Cell {

	private static final int DEAD = 0;
	private static final int ALIVE = 1;
	private int mySugarAmount;
	private int mySugarMetabolism;
	private boolean myUpdated;

	public SugarAgentCell(SugarPatch patch) {
		myPatch = patch;
		mySugarMetabolism = (int) Math.floor(Math.random() * 5) + 1;
		mySugarAmount = (int) Math.floor(Math.random() * 4) + 1;
		myUpdated = false;
	}

	@Override
	public void update() {
		if (myUpdated == false && myState == ALIVE) {
			myUpdated = true;
			List<Patch> myPatchNeighbors = myPatch.getPatchNeighbors();
			int maximumSugarAmongNeighbors = Integer.MIN_VALUE;
			SugarPatch maximumNeighbor = null;

			for (Patch neighbor : myPatchNeighbors) {
				int neighborSugar = ((SugarPatch) neighbor).getState();
				if (neighborSugar > maximumSugarAmongNeighbors) {
					maximumSugarAmongNeighbors = neighborSugar;
					maximumNeighbor = (SugarPatch) neighbor;
				}
			}
			
			((SugarPatch) myPatch).swapCells(maximumNeighbor);
			mySugarAmount += myPatch.getState();
			myPatch.setState(0);
			mySugarAmount -= mySugarMetabolism;
			
			if (mySugarAmount <= 0) {
				myState = DEAD;
			}
		}
	}
	
	public void reset() {
		myUpdated = false;
	}
}