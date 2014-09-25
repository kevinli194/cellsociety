package backend.simulations;

import java.util.ArrayList;

import backend.cells.SharkCell;
import backend.patch.EcoPatch;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new EcoPatch(i, j/*, true, 0, (int) thresholdValue*/);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j]);
		if (j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j - 1]);
		if (i < myPatchGrid.length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j]);
		if (j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j + 1]);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialStates, double thresholdValue) {
		for (InitialCell c : initialStates) {
			((EcoPatch) myPatchGrid[c.myX][c.myY]).initializeCell(c.myState, thresholdValue);
		}
	}


	@Override
	public void updateGrid() {
		/*for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SharkCell) myPatchGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SharkCell) myPatchGrid[i][j]).updateFish();
			}
		}*/

		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].reset();
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
