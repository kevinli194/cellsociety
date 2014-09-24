package backend.patch;

import backend.cells.Cell;
import backend.cells.GoLCell;

public class GoLPatch extends Patch {
	
	public GoLPatch(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myPreviousState = state;
		myState = state;
		myCell = new GoLCell(this, update);
	}
	
	@Override
	public void setPatchState(String state){};
	
	@Override
	public void setCellState(String cellState) {
		myCell.setState(cellState);
	}

	@Override
	public void updatePatch() {
		myCell.update();
	}

}