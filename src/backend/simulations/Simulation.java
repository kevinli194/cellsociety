package backend.simulations;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Paint;
import backend.patches.Patch;
import backend.xml.InitialCell;

/**
 * 
 * @author CS308 Team16
 *
 */
public abstract class Simulation {
	protected Patch[][] myGrid;
	public Paint[] myCellColors;
	double myThreshold;

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

	public Patch[][] initialize(String modelType, String unitShape,
			String boundaryType, int xDimension, int yDimension,
			double thresholdValue, ArrayList<InitialCell> initialCells) {
		myGrid = new Patch[xDimension][yDimension];
		for (int i = 0; i < xDimension; i++) {
			for (int j = 0; j < yDimension; j++) {
				makeNewCell(i, j, thresholdValue);
			}
		}
		myThreshold = thresholdValue;
		setNeighbors(myGrid, boundaryType.toUpperCase(),
				unitShape.toUpperCase());
		setInitialState(initialCells);
		initializeColors();
		return myGrid;
	}

	/**
	 * makes new empty cells based on parameters
	 * 
	 * @param i
	 *            feeds in the x location of cell
	 * @param j
	 *            feeds in y location of cell
	 * @param thresholdValue
	 *            feeds in threshold value to be used in cell
	 */

	protected abstract void makeNewCell(int i, int j, double thresholdValue);

	/**
	 * sets the inital state in the grid
	 * 
	 * @param initialState
	 *            an array of cell like structures that contain the state and
	 *            location of each cell.
	 */

	protected abstract void setInitialState(List<InitialCell> initialState);

	/**
	 * Initializes the colors to be used for each state. Used when dynamically
	 * editing each shape.
	 */

	protected abstract void initializeColors();

	/**
	 * iterates through the cell grid calling the update function on each cell
	 * appropriately. resets myUpdate in each cell after each cycle. This is
	 * called every frame.
	 */
	public abstract void updateGrid();

	/**
	 * Calls the appropriate neighborsetter to set the neighbors for a given
	 * grid
	 * 
	 * @param grid
	 *            the grid you want to define neighborhoods for
	 * @param boundaryType
	 *            the type of boundary
	 * @param gridShape
	 *            the type of shape making up the grid.
	 */

	protected abstract void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape);
}