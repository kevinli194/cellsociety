package backend.simulations;


import backend.neighborsetters.NeighborSetter;
import backend.neighborsetters.SegNeighborSetter;
import backend.patches.Patch;
import backend.patches.SegPatch;
import backend.xml.InitialCell;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class SegSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new SegPatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegPatch) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				SegPatch emptyPatch = (SegPatch) selectRandomEmptyPatch();
				((SegPatch) myGrid[i][j]).setEmptyPatch(emptyPatch);
				((SegPatch) myGrid[i][j]).update();
			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();
			}
		}
	}

	/**
	 * finds a random empty patch to move the contents of the cell to.
	 * 
	 * @return returns a random empty patch
	 */

	public Patch selectRandomEmptyPatch() {
		List<Patch> emptyPatches = findEmptyPatch();

		if (emptyPatches.isEmpty())
			return null;
		else {
			int randomIndex = (int) Math.floor(Math.random()
					* emptyPatches.size());
			return emptyPatches.get(randomIndex);
		}
	}

	private List<Patch> findEmptyPatch() {
		List<Patch> emptyPatches = new ArrayList<Patch>();
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				Patch patch = myGrid[i][j];
				if (patch.myCell.myState == 0) {
					emptyPatches.add(patch);
				}
			}
		}
		return emptyPatches;
	}

	@Override
	protected void initializeColors() {
		myCellColors = new Color[3];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.BLACK;
		myCellColors[2] = Color.RED;

	}

	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new SegNeighborSetter();
		setter.setNeighbors(grid, boundaryType, gridShape);
		
	}
}
