package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import backend.neighborsetters.FireNeighborSetter;
import backend.neighborsetters.NeighborSetter;
import backend.patches.Patch;
import backend.patches.TreePatch;
import backend.xml.InitialCell;

public class FireSimulation extends Simulation {
	@Override
	protected void makeNewPatch(Patch[][] grid, int i, int j, double thresholdValue) {
		grid[i][j] = new TreePatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(Patch[][] grid, List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((TreePatch) grid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid(Patch[][] grid) {

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((TreePatch) grid[i][j]).update();
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((TreePatch) grid[i][j]).updateCell();
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
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.GREEN;
		myCellColors[2] = Color.RED;

	}

	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new FireNeighborSetter();
		setter.setNeighbors(grid, boundaryType, gridShape);

	}
}
