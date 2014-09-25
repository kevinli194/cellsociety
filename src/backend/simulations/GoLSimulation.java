package backend.simulations;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import backend.cells.GoLCell;
import backend.xml.InitialCell;

public class GoLSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new GoLCell(i, j, false, 1);
	}


	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((GoLCell) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].update();
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
		myColors[1] = Color.WHITE;
		myColors[2] = Color.BLACK;	
	}
}
