package backend.simulations;
import backend.cells.Cell;
import backend.cells.SegCell;
import backend.xml.InitialCell;

import java.util.ArrayList;


public class SegSimulation extends Simulation {

	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myCellGrid[i][j] = new SegCell(i, j, true, 0, this, thresholdValue);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0)
			myCellGrid[i][j].addNeighbor(myCellGrid[i - 1][j]);
		if (j > 0)
			myCellGrid[i][j].addNeighbor(myCellGrid[i][j - 1]);
		if (i < myCellGrid.length - 1)
			myCellGrid[i][j].addNeighbor(myCellGrid[i + 1][j]);
		if (j < myCellGrid[0].length - 1)
			myCellGrid[i][j].addNeighbor(myCellGrid[i][j + 1]);
		if (i > 0 && j > 0)
			myCellGrid[i][j].addNeighbor(myCellGrid[i - 1][j - 1]);
		if (i > 0 && j < myCellGrid[0].length - 1)
			myCellGrid[i][j].addNeighbor(myCellGrid[i - 1][j + 1]);
		if (i < myCellGrid.length - 1 && j > 0)
			myCellGrid[i][j].addNeighbor(myCellGrid[i + 1][j - 1]);
		if (i < myCellGrid.length - 1 && j < myCellGrid[0].length - 1)
			myCellGrid[i][j].addNeighbor(myCellGrid[i + 1][j + 1]);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegCell) myCellGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				((SegCell) myCellGrid[i][j]).updateCell();
			}
		}

		for (int i = 0; i < myCellGrid.length; i++) {
			for (int j = 0; j < myCellGrid[0].length; j++) {
				myCellGrid[i][j].reset();
			}
		}
	}
	/**
	 *  finds a random empty cell to move the contents of the cell to.
	 * @return returns a random empty cell
	 */
	
	public Cell selectRandomEmptyCell()
	{
		ArrayList<Cell> emptyCells = findEmptyCells();
		
		if(emptyCells.isEmpty())
			return null;
		else
		{
			int randomIndex = (int) Math.floor(Math.random() * emptyCells.size());
			return emptyCells.get(randomIndex);
		}
	}
	
	private ArrayList<Cell> findEmptyCells()
	{
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		for(int i = 0; i < myCellGrid.length; i++)
		{
			for(int j = 0; j < myCellGrid[i].length; j++)
			{
				Cell currentCell = myCellGrid[i][j];
				if(currentCell.getState() == 0)
				{
					emptyCells.add(currentCell);
				}
			}
		}
		return emptyCells;
	}
	
	@Override
	protected void initializeColor() {
		myColors = new String[3];
		myColors[0] = "white";
		myColors[1] = "black";
		myColors[2] = "red";
		
	}
}
