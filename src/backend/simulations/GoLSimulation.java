package backend.simulations;

import java.util.List;
import javafx.scene.paint.Color;
import backend.cells.GoLCell;
import backend.patch.GoLPatch;
import backend.xml.InitialCell;

public class GoLSimulation extends Simulation {

	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new GoLPatch(i, j);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			GoLPatch patch = (GoLPatch) myPatchGrid[c.myX][c.myY];
			patch.setCellState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].updatePatch();
			}
		}
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].reset();
			}
		}
	}
	
	@Override
	protected void initializeColors() {
		myColors = new Color[3];
		myColors[0] = Color.BLUE;
		myColors[1] = Color.WHITE;
		myColors[2] = Color.BLACK;	
	}
}
