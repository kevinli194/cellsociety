package backend.simulations;

import java.util.List;

import backend.patches.SugarPatch;
import backend.xml.InitialCell;

public class SugarSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new SugarPatch(i, j);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {};

	@Override
	protected void initializeColors() {
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

			}
		}
	}

	@Override
	public void updateGrid() {
		
	}

}
