package backend.simulations;

import java.util.List;
import javafx.scene.paint.Color;
import backend.patch.EcoPatch;
import backend.xml.InitialCell;

public class EcoSimulation extends Simulation {
	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new EcoPatch(i, j, (int) thresholdValue /*, true, 0, (int) thresholdValue*/);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((EcoPatch) myPatchGrid[c.myX][c.myY]).initializeCell(c.myState);
		}
	}


	@Override
	public void updateGrid() {
		/*for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SharkCell) myPatchGrid[i][j]).update();
			}
		}
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SharkCell) myPatchGrid[i][j]).updateFish();
			}
		}*/

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
		myColors[1] = Color.YELLOW;
		myColors[2] = Color.GRAY;
	}
}
