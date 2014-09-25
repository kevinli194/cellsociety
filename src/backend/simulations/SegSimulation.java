/*package backend.simulations;

import backend.cells.Cell;
import backend.cells.SegCell;
import backend.patch.Patch;
import backend.xml.InitialCell;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class SegSimulation extends Simulation {

	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new SegPatch(i, j, this, thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegPatch) myPatchGrid[c.myX][c.myY]).setCellState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SegCell) myPatchGrid[i][j]).updateCell();
			}
		}

		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].reset();
			}
		}
	}

	*//**
	 * finds a random empty cell to move the contents of the cell to.
	 * 
	 * @return returns a random empty cell
	 *//*

	public Cell selectRandomEmptyCell() {
		List<Cell> emptyPatches = findemptyPatches();

		if (emptyPatches.isEmpty())
			return null;
		else {
			int randomIndex = (int) Math.floor(Math.random()
					* emptyPatches.size());
			return emptyPatches.get(randomIndex);
		}
	}

	private List<Patch> findemptyPatches() {
		List<Patch> emptyPatches = new ArrayList<Patch>();

		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[i].length; j++) {
				Patch currentPatch = myPatchGrid[i][j];
				if (currentPatch.getState() == 0) {
					emptyPatches.add(currentPatch);
				}
			}
		}
		return emptyPatches;
	}

	@Override
	protected void initializeColors() {
		myColors = new Color[3];
		myColors[0] = Color.WHITE;
		myColors[1] = Color.BLACK;
		myColors[2] = Color.RED;
	}
}
*/