package backend.patch;

import backend.cells.GoLCell;

public class GoLPatch extends Patch {
	public GoLPatch(int xCoord, int yCoord) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myCell = new GoLCell(this);
	}
	
	@Override
	public void setPatchState(String state){};
	
	@Override
	public void setCellState(String cellState) {
		myCell.setState(cellState);
	}

	@Override
	public void updatePatch() {
		updateCell();
	}
	
	@Override
	public void updateCell() {
		myCell.updateCell();
	}

}