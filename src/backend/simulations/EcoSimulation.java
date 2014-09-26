/*package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import backend.patches.EcoPatch;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new EcoPatch(i, j, true, 0, (int) thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoPatch) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoPatch) myGrid[i][j]).update();
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
}
*/