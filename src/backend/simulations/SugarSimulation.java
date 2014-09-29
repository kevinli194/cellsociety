package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import backend.neighborsetters.NeighborSetter;
import backend.neighborsetters.SugarNeighborSetter;
import backend.patches.Patch;
import backend.patches.SugarPatch;
import backend.xml.InitialCell;

public class SugarSimulation extends Simulation {

	@Override
	protected void makeNewPatch(Patch[][] grid,int i, int j, double thresholdValue) {
		grid[i][j] = new SugarPatch(i, j);
	}

	@Override
	protected void setInitialState(Patch[][] grid,List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SugarPatch) grid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid(Patch[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((SugarPatch) grid[i][j]).updateCell();
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				((SugarPatch) grid[i][j]).update();
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].reset();
				((SugarPatch) grid[i][j]).resetCell();

			}
		}
	}

	@Override
	protected void initializeColors() {
		myCellColors = new Color[2];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.RED;
	}
	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new SugarNeighborSetter((int)myThreshold);
		setter.setNeighbors(grid, boundaryType, gridShape);
		
	}

}
