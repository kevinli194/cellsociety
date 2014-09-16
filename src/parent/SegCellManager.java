package parent;

import java.util.ArrayList;

public class SegCellManager extends CellManager {

	@Override
	protected void makeNewCell(int i, int j) {
		// TODO Auto-generated method stub
		myGrid[i][j] = new SegCell(i, j, true, 0, this, 0);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		// TODO Auto-generated method stub
		if (i > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j]);
		}
		if (j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i][j - 1]);
		}
		if (i < myGrid.length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j]);
		}
		if (j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i][j + 1]);
		}
		if (i > 0 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j - 1]);
		}
		if (i > 0 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j + 1]);
		}
		if (i < myGrid.length - 1 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j - 1]);
		}
		if (i < myGrid.length - 1 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j + 1]);
		}

	}

	@Override
	protected void setInitialState(ArrayList<InitialCell> initialState) {
		// TODO Auto-generated method stub
		for (InitialCell c : initialState) {
			((SegCell) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		// TODO Auto-generated method stub
		
	}
	
	public Cell findFirstEmptyCell()
	{
		for(int i = 0; i < myGrid.length; i++)
		{
			for(int j = 0; j < myGrid[i].length; j++)
			{
				Cell currentCell = myGrid[i][j];
				if(currentCell.myState == 0) // EMPTY
				{
					return currentCell;
				}
			}
		}
		
		return null;
	}
}
