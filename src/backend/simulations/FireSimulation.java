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
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new TreePatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((TreePatch) myGrid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid() {

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((TreePatch) myGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((TreePatch) myGrid[i][j]).updateCell();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();

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
