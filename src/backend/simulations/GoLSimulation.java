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
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new GoLPatch(i, j);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((GoLPatch) myGrid[c.myX][c.myY]).setInitialState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].updateCell();
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
		myCellColors = new Color[2];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.BLACK;
	}
	@Override
	protected void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		NeighborSetter setter = new GoLNeighborSetter();
		setter.setNeighbors(grid, boundaryType, gridShape);
		
	}
}
