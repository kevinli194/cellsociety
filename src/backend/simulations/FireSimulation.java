package backend.simulations;

import java.util.ArrayList;

import backend.cells.FireCell;
import backend.xml.InitialCell;

public class FireSimulation extends Simulation {
	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myCellGrid[i][j] = new FireCell(i, j, true, 0, thresholdValue); 
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0) {
			myCellGrid[i][j].addNeighbor(myCellGrid[i - 1][j]);
		}
		if (j > 0) {
			myCellGrid[i][j].addNeighbor(myCellGrid[i][j - 1]);
		}
		if (i < myCellGrid.length - 1) {
			myCellGrid[i][j].addNeighbor(myCellGrid[i + 1][j]);
		}
		if (j < myCellGrid[0].length - 1) {
			myCellGrid[i][j].addNeighbor(myCellGrid[i][j + 1]);
		}
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((FireCell) myCellGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				((FireCell) myCellGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				((FireCell) myCellGrid[i][j]).updateFire();
			}
		}
		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				if (myCellGrid[i][j].getState() == 2) {
				}
			}
		}

		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				myCellGrid[i][j].reset();

			}
		}
	}

	@Override
	protected void initializeColor() {
		myColors = new String[3];
		myColors[0] = "white";
		myColors[1] = "green";
		myColors[2] = "red";
		
	}
}
