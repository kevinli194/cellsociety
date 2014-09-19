package backend.simulations;

import java.util.ArrayList;
import backend.cells.Cell;
import backend.xml.InitialCell;

public abstract class Simulation {
	protected Cell[][] myGrid;
	public String[] myColors;

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
		initializeColor();
		return myGrid;
	}

	protected abstract void makeNewCell(int i, int j, double thresholdValue);

	protected abstract void setInitialState(ArrayList<InitialCell> initialState);

	protected abstract void setNeighbors(int i, int j);

	protected abstract void initializeColor();

	public abstract void updateGrid();

}