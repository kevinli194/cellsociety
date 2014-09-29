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
	protected void makeNewPatch(Patch[][] grid, int i, int j, double thresholdValue) {
		grid[i][j] = new SegPatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(Patch[][] grid, List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegPatch) grid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid(Patch[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				SegPatch emptyPatch = (SegPatch) selectRandomEmptyPatch(grid);
				((SegPatch) grid[i][j]).setEmptyPatch(emptyPatch);
				((SegPatch) grid[i][j]).update();
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].reset();
			}
		}
	}

	/**
	 * finds a random empty patch to move the contents of the cell to.
	 * 
	 * @return returns a random empty patch
	 */

	public Patch selectRandomEmptyPatch(Patch[][]grid) {
		List<Patch> emptyPatches = findEmptyPatch(grid);

		if (emptyPatches.isEmpty())
			return null;
		else {
			int randomIndex = (int) Math.floor(Math.random()
					* emptyPatches.size());
			return emptyPatches.get(randomIndex);
		}
	}

	private List<Patch> findEmptyPatch(Patch[][]grid) {
		List<Patch> emptyPatches = new ArrayList<Patch>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				Patch patch = grid[i][j];
				if (patch.getCell().getState() == 0) {
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
