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
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new EcoPatch(i, j, thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoPatch) myGrid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoPatch) myGrid[i][j]).updateShark();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoPatch) myGrid[i][j]).updateFish();
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
