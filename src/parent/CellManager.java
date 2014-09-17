package parent;

import java.util.ArrayList;

//double-check to see if boundaries are correct
public abstract class CellManager {
	protected Cell[][] myGrid;
	private static double myThresholdValue;

	public Cell[][] initialize(String modelType, int xDimension,
			int yDimension, double thresholdValue,
			ArrayList<InitialCell> initialCells) {
		myGrid = new Cell[xDimension][yDimension];
		for (int i = 0; i < xDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				makeNewCell(i, j, thresholdValue);
			}
		}
		for (int i = 0; i < xDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				setNeighbors(i, j);
			}
		}
		setInitialState(initialCells);

		return myGrid;
	}

	protected abstract void makeNewCell(int i, int j, double thresholdValue);

	protected abstract void setInitialState(ArrayList<InitialCell> initialState);

	protected abstract void setNeighbors(int i, int j);

	public abstract void updateGrid();
}
