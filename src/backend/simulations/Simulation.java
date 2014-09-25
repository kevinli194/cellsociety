package backend.simulations;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import backend.cells.Cell;
import backend.neighborsetters.NeighborSetter;
import backend.neighborsetters.SegNeighborSetter;
import backend.xml.InitialCell;
import backend.patch.Patch;

public abstract class Simulation {
	protected Patch[][] myPatchGrid;
	public Color[] myColors;

	/**
	 * Initializes the grid based on initial dimensions and threshold values,
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
			List<InitialCell> initialCells) {
		myPatchGrid = new Patch[xDimension][yDimension];
		for (int i = 0; i < xDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				makeNewPatch(i, j, thresholdValue);
			}
		}
		NeighborSetter setter = new SegNeighborSetter();
		setter.setNeighbors(myPatchGrid, "toroidal", "rectangle");
		setInitialState(initialCells);

		initializeColors();
		return myPatchGrid;

	}

	/**
	 * Makes new empty cells based on parameters
	 * 
	 * @param i
	 *            feeds in the x location of cell
	 * @param j
	 *            feeds in y location of cell
	 * @param thresholdValue
	 *            feeds in threshold value to be used in cell
	 */
	protected abstract void makeNewPatch(int i, int j, double thresholdValue);

	/**
	 * Sets the initial state in the grid
	 * 
	 * @param initialState
	 *            an array of cell like structures that contain the state and
	 *            location of each cell.
	 * @param thresholdValue 
	 */

	protected abstract void setInitialState(List<InitialCell> initialState);

	
	/**
	 * Initializes the colors to be used for each state.
	 */

	protected abstract void initializeColors();

	/**
	 * Iterates through the cell grid calling the update function on each cell
	 * appropriately. Resets myUpdate in each cell after each cycle. This is
	 * called every frame.
	 */
	public abstract void updateGrid();

}