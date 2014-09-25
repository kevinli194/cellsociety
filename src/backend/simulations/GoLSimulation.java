package backend.simulations;

import java.util.ArrayList;

import backend.cells.GoLCell;
import backend.patch.GoLPatch;
import backend.xml.InitialCell;

public class GoLSimulation extends Simulation {

	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new GoLPatch(i, j);
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
		if (i > 0 && j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j - 1]);
		if (i > 0 && j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j + 1]);
		if (i < myPatchGrid.length - 1 && j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j - 1]);
		if (i < myPatchGrid.length - 1 && j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j + 1]);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState, double thresholdValue) {
		for (InitialCell c : initialState) {
			GoLPatch patch = (GoLPatch) myPatchGrid[c.myX][c.myY];
			patch.setCellState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].updatePatch();
				System.out.print(myPatchGrid[i][j].getCellState() + " ");
			}
			System.out.println();
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
		myColors[0] = "blue";
		myColors[1] = "white";
		myColors[2] = "black";	
	}
}
