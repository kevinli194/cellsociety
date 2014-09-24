package backend.simulations;

import java.util.ArrayList;

import backend.cells.Cell;
import backend.patch.Patch;
import backend.xml.InitialCell;

public abstract class Simulation {
	protected Patch[][] myPatchGrid;
	public String[] myColors;

	/**
	 * Initializes the grid based on initial dimensions and threshhold values,
	 * and initialCells.
	 * 
	 * @param modelType
	 *            the type of simulation we are running
	 * @param xDimension
	 *            the x dimension of the grid
	 * @param yDimension
	 *            the y dimension of the grid
	 * @param thresholdvalue
	 *            a certain value that maybe used by the cells to determine how
	 *            to update.
	 * @param initialCell
	 *            contains the location and inital states to be initalized in
	 *            the grid.
	 * 
	 * @return returns an array of array of cells in a grid.
	 */

	public Patch[][] initialize(String modelType, int xDimension,
			int yDimension, double thresholdValue,
			ArrayList<InitialCell> initialCells) {
		myPatchGrid = new Patch[xDimension][yDimension];
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
		return myPatchGrid;
	}

	/**
	 * makes new empty cells based on parameters
	 * 
	 * @param i
	 *            feeds in the x location of cell
	 * @param j
	 *            feeds in y location of cell
	 * @param thresholdValue
	 *            feeds in thresholdvalue to be used in cell
	 */
	protected abstract void makeNewCell(int i, int j, double thresholdValue);

	/**
	 * sets the inital state in the grid
	 * 
	 * @param initialState
	 *            an array of cell like structures that contain the state and
	 *            location of each cell.
	 */
	protected abstract void setInitialState(ArrayList<InitialCell> initialState);

	/**
	 * iterates through and sets neighbors for each cell.
	 * 
	 * @param i
	 *            location of the cell in grid (x coord)
	 * @param j
	 *            location of the cell in grid (y coord)
	 */
	protected abstract void setNeighbors(int i, int j);

	/**
	 * Initializes the colors to be used for each state.
	 */

	protected abstract void initializeColor();

	/**
	 * iterates through the cell grid calling the update function on each cell
	 * appropriately. resets myUpdate in each cell after each cycle. This is
	 * called every frame.
	 */
	public abstract void updateGrid();

}