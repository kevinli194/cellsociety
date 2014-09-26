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
	};

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

			}
		}
	}

	@Override
	protected void initializeColors() {
		// TODO Auto-generated method stub
		
	}

}
