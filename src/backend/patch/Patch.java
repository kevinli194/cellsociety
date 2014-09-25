package backend.patch;

import java.util.ArrayList;
import java.util.List;

import backend.cells.Cell;

public abstract class Patch {
	protected Cell myCell;
	protected List<Patch> myNeighbors = new ArrayList<Patch>();
	protected int[] myCoordinates = new int[2];
	protected boolean myUpdated;
	protected int myState;
	protected int myPreviousState;

	public void addNeighbor(Patch neighbor) {
		myNeighbors.add(neighbor);
	}

	public int getState() {
		return myState;
	}
	
	public int getCellState() {
		return myCell.getState();
	}

	public abstract void setPatchState(String state);
	
	public abstract void setCellState(String state);

	public void reset() {
		myCell.reset();
	}

	public abstract void updatePatch();

}