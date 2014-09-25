package backend.patch;

import backend.cells.FireCell;

public class TreePatch extends Patch {

	protected static final int EMPTY = 0;
	protected static final int TREE = 1;
	protected static final int BURNING = 2;
	
	public TreePatch(int xCoord, int yCoord, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myState = EMPTY;
		myUpdated = false;
		myThresholdValue = thresholdValue;
		
		myCell = new FireCell(thresholdValue);
	}
	
	@Override
	public void setPatchState(String state) {
		if(state.equals("TREE"))
			myState = 1;
	}

	@Override
	public void setCellState(String state) {
		myCell.setState(state);
	}

	@Override
	public void updatePatch() {
		if (myState == TREE && myUpdated == false) {
			myUpdated = true;
			if (anyNeighborIsBurning()) {
				double probabilityValue = Math.random();
				if (probabilityValue < myThresholdValue) {
					myCell.setState("BURNING");
				}
			}
		}
	}
	
	@Override
	public void updateCell() {
		myCell.updateCell();
	}
	
	/**
	 * Method that checks if neighbors are in the state of burning.
	 * 
	 * @return returns whether any neighbors are burning.
	 */
	private boolean anyNeighborIsBurning() {
		for (Patch neighbor : myNeighbors)
		{
			FireCell cellNeighbor = (FireCell) neighbor.myCell;
			if(cellNeighbor.getState() == 1) {
				return true;
			}
		}
		return false;
	}

}
