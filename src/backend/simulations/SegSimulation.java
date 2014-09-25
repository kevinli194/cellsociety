package backend.simulations;
import backend.cells.Cell;
import backend.cells.SegCell;
import backend.xml.InitialCell;

import java.util.ArrayList;


public class SegSimulation extends Simulation {

	@Override
	protected void makeNewPatch(int i, int j, double thresholdValue) {
		myPatchGrid[i][j] = new SegPatch(i, j, this, thresholdValue);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		if (i > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j]);
		if (j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j - 1]);
		if (i < myPatchGrid.length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j]);
		if (j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i][j + 1]);
		if (i > 0 && j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j - 1]);
		if (i > 0 && j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i - 1][j + 1]);
		if (i < myPatchGrid.length - 1 && j > 0)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j - 1]);
		if (i < myPatchGrid.length - 1 && j < myPatchGrid[0].length - 1)
			myPatchGrid[i][j].addNeighbor(myPatchGrid[i + 1][j + 1]);
	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegPatch) myPatchGrid[c.myX][c.myY]).setCellState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				((SegCell) myPatchGrid[i][j]).updateCell();
			}
		}

		for (int i = 0; i < myPatchGrid.length; i++) {
			for (int j = 0; j < myPatchGrid[0].length; j++) {
				myPatchGrid[i][j].reset();
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
		for(int i = 0; i < myPatchGrid.length; i++)
		{
			for(int j = 0; j < myPatchGrid[i].length; j++)
			{
				Cell currentCell = myPatchGrid[i][j];
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
