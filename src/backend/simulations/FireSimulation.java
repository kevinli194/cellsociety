package backend.simulations;

import java.util.ArrayList;

import backend.cells.FireCell;
import backend.xml.InitialCell;

public class FireSimulation extends Simulation {
	public String[] myColors = {"white","green","red"};
	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new FireCell(i, j, true, 0, thresholdValue); // last
																	// parameter
																	// should
		// be the threshold
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

	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			// System.out.println(c.myState.compareTo("BURNING"));
			((FireCell) myGrid[c.myX][c.myY]).setState(c.myState.replaceAll(
					"\\s", ""));
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((FireCell) myGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((FireCell) myGrid[i][j]).update2();

			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				if (myGrid[i][j].getState() == 2) {
					// System.out.println(i + " " + j);
				}

			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();

			}
		}
	}
}
