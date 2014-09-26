/*package backend.simulations;
import backend.patches.Patch;
import backend.patches.SegPatch;
import backend.xml.InitialCell;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;


public class SegSimulation extends Simulation {

	@Override
	protected void makeNewCell(int i, int j, double thresholdValue) {
		myGrid[i][j] = new SegPatch(i, j, true, 0, this, thresholdValue);
	}

	@Override
	protected void setInitialState(List<InitialCell> initialState) {
		for (InitialCell c : initialState) {
			((SegPatch) myGrid[c.myX][c.myY]).setState(c.myState);
		}
	}

	@Override
	public void updateGrid() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				((SegPatch) myGrid[i][j]).update();
			}
		}

		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[0].length; j++) {
				myGrid[i][j].reset();
			}
		}
	}
	*//**
	 *  finds a random empty cell to move the contents of the cell to.
	 * @return returns a random empty cell
	 *//*
	
	public Patch selectRandomEmptyCell()
	{
		List<Patch> emptyCells = findEmptyCells();
		
		if(emptyCells.isEmpty())
			return null;
		else
		{
			int randomIndex = (int) Math.floor(Math.random() * emptyCells.size());
			return emptyCells.get(randomIndex);
		}
	}
	
	private List<Patch> findEmptyCells()
	{
		List<Patch> emptyCells = new ArrayList<Patch>();
		for(int i = 0; i < myGrid.length; i++)
		{
			for(int j = 0; j < myGrid[i].length; j++)
			{
				Patch currentCell = myGrid[i][j];
				if(currentCell.getState() == 0)
				{
					emptyCells.add(currentCell);
				}
			}
		}
		return emptyCells;
	}
	
	@Override
	protected void initializeColors() {
		myCellColors = new Color[3];
		myCellColors[0] = Color.WHITE;
		myCellColors[1] = Color.BLACK;
		myCellColors[2] = Color.RED;
		
	}
}
*/