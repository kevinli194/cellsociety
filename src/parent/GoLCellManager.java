package parent;

import java.util.ArrayList;

public class GoLCellManager extends CellManager {

	@Override
	protected void makeNewCell(int i, int j) {
		// TODO Auto-generated method stub
		myGrid[i][j] = new GoLCell(i, j, true, 1); // last parameter should
													// be the threshold
	}

	@Override
	protected void setNeighbors(int i, int j) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		for (InitialCell c : initialState) {
			// System.out.println(c.myState.compareTo("BURNING"));
			((GoLCell) myGrid[c.myX][c.myY]).setState(c.myState.replaceAll(
					"\\s", ""));
		}
	}

	@Override
	public void updateGrid() {
		// TODO Auto-generated method stub
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
}