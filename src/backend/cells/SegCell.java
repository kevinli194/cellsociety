/*package backend.cells;

import backend.simulations.SegSimulation;

public class SegCell extends Cell {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int O = 2;
	private SegSimulation mySegCellManager;

	public SegCell(int xCoord, int yCoord, boolean update, int state,
			SegSimulation segCellManager, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		mySegCellManager = segCellManager;
		myThresholdValue = thresholdValue;
		myPossibleStates = 3;
	}

	@Override
	public void updateCell() {
		if (myUpdated == false) {
			myUpdated = true;
			int satisfiedNeighbors = 0;
			int totalNeighbors = 0;
			for (Cell neighbor : myNeighbors) {
				if (neighbor.myState != EMPTY) {
					if (neighbor.myState == myState)
						satisfiedNeighbors++;
					totalNeighbors++;
				}
			}

			double percentageOfNeighborsSatisfied = (totalNeighbors != 0) ? 
					satisfiedNeighbors / totalNeighbors : 0;
			if (percentageOfNeighborsSatisfied < myThresholdValue)
				moveToBeSatisfied();
		}
	}

	*//**
	 *  Moves a cell to a empty cell if dissatisfied.
	 *//*

	private void moveToBeSatisfied() {
		Cell emptyCell = mySegCellManager.selectRandomEmptyCell();
		if (emptyCell != null) {
			emptyCell.myPreviousState = emptyCell.myState;
			emptyCell.myState = myState;
			emptyCell.myUpdated = true;
			myPreviousState = myState;
			myState = EMPTY;
		}
	}

	public void setState(String state) {
		if (state.equals("EMPTY")) {
			myState = EMPTY;
		}
		if (state.equals("X")) {
			myState = X;
			myUpdated = false;
		}
		if (state.equals("O")) {
			myState = O;
			myUpdated = false;
		}
	}

}
*/