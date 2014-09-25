package backend.patch;

import backend.cells.Cell;
import backend.cells.GoLCell;

public class GoLPatch extends Patch {
	
	public GoLPatch(int xCoord, int yCoord, boolean update) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myCell = new GoLCell(this, update, 1);
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