package backend.cells;

import java.util.List;

import backend.patches.SegPatch;

/**
 * 
 * @author CS308 Team16
 * SegCell is the Cell for the Segregation simulation.
 * SegCell has 3 states: empty, X type, or O type.
 */
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

	/**
	 * The update method for the SegCell involves looking at all of the neighbors
	 * and then moving to another cell if not satisfied.
	 */
	@Override
	public void update() {
		if (myPatch.getUpdated() == false && myState != EMPTY) {
			myPatch.setUpdated(true);
			double percentageOfNeighborsSatisfied = computeFractionOfNeighborsSatisfied();
			if (percentageOfNeighborsSatisfied < myThresholdValue)
				moveToBeSatisfied();
		}
	}
	
	/**
	 * Looks at all neighbors that are not empty and calculates
	 * the fraction of neighbors that are the same state/type
	 * to determine satisfaction.
	 * @return
	 * 		double value for fraction of neighbors of the same state
	 */
	private double computeFractionOfNeighborsSatisfied(){
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

		return percentageOfNeighborsSatisfied;
	}

	/**
	 * Moves a cell to an empty cell if dissatisfied via swap method.
	 */
	private void moveToBeSatisfied() {
		SegPatch emptyPatch = ((SegPatch) myPatch).getEmptyPatch();
		if (emptyPatch != null) {
			((SegPatch) myPatch).swapCells(emptyPatch);
		}
	}
}