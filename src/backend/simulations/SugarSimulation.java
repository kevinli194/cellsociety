package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import backend.patches.SugarPatch;
import backend.xml.InitialCell;

public class SugarSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new SugarPatch(i, j);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for(InitialCell c : initialState)
		{
			((SugarPatch) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((SugarPatch) myGrid[i][j]).updateCell();
			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((SugarPatch) myGrid[i][j]).update();
			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();
				((SugarPatch) myGrid[i][j]).resetCell();

			}
		}
	}

	@Override
	protected void initializeColors() {
		myCellColors = new Color[2];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.RED;
	}

}
