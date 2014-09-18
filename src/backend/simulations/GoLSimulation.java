package backend.simulations;

import java.util.ArrayList;

import backend.cells.GoLCell;
import backend.xml.InitialCell;

public class GoLSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new GoLCell(i, j, false, 1); // we should try to remove thresholdValue from here
		// think of a better way
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j]);
		}
		if (j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i][j - 1]);
		}
		if (i < myGrid.length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j]);
		}
		if (j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i][j + 1]);
		}
		if (i > 0 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j - 1]);
		}
		if (i > 0 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j + 1]);
		}
		if (i < myGrid.length - 1 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j - 1]);
		}
		if (i < myGrid.length - 1 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j + 1]);
		}

	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			// System.out.println(c.myState.compareTo("BURNING"));
			((GoLCell) myGrid[c.myX][c.myY]).setState(c.myState.replaceAll(
					"\\s", ""));
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].update();

			}

		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();

			}
		}

	}

	@Override
	protected void initializeColor() {
		myColors = new String[3];
		myColors[0] = "BLUE";
		myColors[1] = "WHITE";
		myColors[2] = "BLACK";
		
	}
}
