package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;

import backend.neighborsetters.EcoNeighborSetter;
import backend.neighborsetters.NeighborSetter;
import backend.patches.EcoPatch;
import backend.patches.Patch;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewPatch(Patch[][] grid, int i, int j, double thresholdValue) {
		grid[i][j] = new EcoPatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(Patch[][] grid, List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoPatch) grid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid(Patch[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((EcoPatch) grid[i][j]).updateShark();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((EcoPatch) grid[i][j]).updateFish();
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].reset();
			}
		}
	}

	@Override
	protected void initializeColors() {
		myCellColors = new Color[3];
		myCellColors[0] = Color.BLUE;
		myCellColors[1] = Color.YELLOW;
		myCellColors[2] = Color.GRAY;
	}

	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new EcoNeighborSetter();
		setter.setNeighbors(grid, boundaryType, gridShape);

	}
}
