package backend.patch;

import java.util.Collections;

import backend.cells.Cell;
import backend.cells.FishCell;
import backend.cells.SharkCell;

public class EcoPatch extends Patch {
	public EcoPatch(int xCoord, int yCoord, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myThresholdValue = thresholdValue;
	}

	public void initializeCell(String state) {
		if (state.equals("FISH"))
			myCell = new FishCell(this, 1);
		if (state.equals("SHARK"))
			myCell = new SharkCell(this, 2);
	}

	public void setCell(Cell cell) {
		myCell = cell;
	}

	@Override
	public void setPatchState(String state) {
	};

	@Override
	public void setCellState(String state) {
		myCell.setState(state);
	}

	@Override
	public void updatePatch() {
		updateCell();
	}
	
	@Override
	public void updateCell() {
		myCell.updateCell();
	}

	public EcoPatch findEmptyNeighborPatch() {
		Collections.shuffle(myNeighbors);
		for (Patch neighbor : myNeighbors) {
			if(!neighbor.containsCell)
			{
				return (EcoPatch) neighbor;
			}
		}	
		return null;
	}
}
