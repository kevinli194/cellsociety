package backend.simulations;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import backend.cells.EcoCell;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new EcoCell(i, j, true, 0, (int) thresholdValue);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoCell) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoCell) myGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((EcoCell) myGrid[i][j]).updateFish();
			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();
			}
		}
	}

	@Override
	protected void initializeColor() {
		myColors = new Color[3];
		myColors[0] = Color.BLUE;
		myColors[1] = Color.YELLOW;
		myColors[2] = Color.GRAY;
	}
}
