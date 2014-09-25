package backend.simulations;

import java.util.ArrayList;

import backend.patch.TreePatch;
import backend.xml.InitialCell;

public class FireSimulation extends Simulation {
	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new TreePatch(i, j, thresholdValue); 
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0) {
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j]);
		}
		if (j > 0) {
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j - 1]);
		}
		if (i < myPatchGrid.length - 1) {
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j]);
		}
		if (j < myPatchGrid[0].length - 1) {
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j + 1]);
		}
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			TreePatch patch = (TreePatch) myPatchGrid[c.myX][c.myY];
			if(c.myState == "FIRE")
				patch.setCellState(c.myState);
			else
				patch.setPatchState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((TreePatch) myPatchGrid[i][j]).updateCell();
			}
		}
		
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((TreePatch) myPatchGrid[i][j]).updatePatch();
			}
		}
		
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].reset();

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
