package backend.simulations;

import java.util.ArrayList;

import backend.cells.EcoCell;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new EcoCell(i, j, true, 0, (int) thresholdValue);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0)
			myGrid[i][j].addNeighbor(myGrid[i - 1][j]);
		if (j > 0)
			myGrid[i][j].addNeighbor(myGrid[i][j - 1]);
		if (i < myGrid.length - 1)
			myGrid[i][j].addNeighbor(myGrid[i + 1][j]);
		if (j < myGrid[0].length - 1)
			myGrid[i][j].addNeighbor(myGrid[i][j + 1]);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoCell) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoCell) myGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoCell) myGrid[i][j]).updateFish();
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
		myColors[0] = "blue";
		myColors[1] = "yellow";
		myColors[2] = "gray";
	}
}
