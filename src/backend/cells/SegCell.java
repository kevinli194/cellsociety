package backend.cells;

import java.util.List;
import backend.patches.Patch;
import backend.patches.SegPatch;

public class SegCell extends Cell {
	public static int EMPTY = 0;
	public static int X = 1;
	public static int O = 2;
	public double myThresholdValue;

	public SegCell(int state, SegPatch patch, double thresholdValue) {
		myState = state;
		myThresholdValue = thresholdValue;
		myPatch = patch;
	}

	@Override
	public void update() {
		if (myPatch.myUpdated == false && myState != EMPTY) {
			myPatch.myUpdated = true;
			int satisfiedNeighbors = 0;
			int totalNeighbors = 0;
			List<Cell> neighborsList = myPatch.getCellNeighbors();
			for (Cell neighbor : neighborsList) {
				if (neighbor.myState == myState)
					satisfiedNeighbors++;
				if (neighbor.myState != 0)
					totalNeighbors++;
			}

			double percentageOfNeighborsSatisfied = (totalNeighbors != 0) ? satisfiedNeighbors
					/ totalNeighbors
					: 0;
			if (percentageOfNeighborsSatisfied < myThresholdValue)
				moveToBeSatisfied();
		}
	}

	/*
	 * Moves a cell to an empty cell if dissatisfied.
	 */
	private void moveToBeSatisfied() {
		SegPatch emptyPatch = ((SegPatch) myPatch).getEmptyPatch();
		if (emptyPatch != null) {
			swapCellsInPatches(emptyPatch);
		}
	}

	private void swapCellsInPatches(SegPatch targetPatch) {
		targetPatch.myCell = this;
		myPatch.myCell = new SegCell(EMPTY, (SegPatch) myPatch, myThresholdValue);
		myPatch = targetPatch;
	}

}