// This entire file is part of my masterpiece.
// Kevin Li

package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import backend.neighborsetters.GoLNeighborSetter;
import backend.neighborsetters.NeighborSetter;
import backend.patches.GoLPatch;
import backend.patches.Patch;
import backend.xml.InitialCell;

public class GoLSimulation extends Simulation {

	@Override
	protected void makeNewPatch(Patch[][] grid, int i, int j, double thresholdValue) {
		grid[i][j] = new GoLPatch(i, j);
	}

	@Override
	protected void setInitialState(Patch[][] grid,List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((GoLPatch) grid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new GoLNeighborSetter();
		setter.setNeighbors(grid, boundaryType, gridShape);

	}

	@Override
	protected void initializeColors() {
		myCellColors = new Color[2];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.BLACK;
	}

	@Override
	public void updateGrid(Patch[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].updateCell();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].reset();
			}
		}
	}
}
