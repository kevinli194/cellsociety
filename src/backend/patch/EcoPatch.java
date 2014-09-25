package backend.patch;

import backend.cells.FishCell;
import backend.cells.SharkCell;

public class EcoPatch extends Patch {

	public EcoPatch(int xCoord, int yCoord)
	{
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
	}
	
	public void initializeCell(String state, double thresholdValue)
	{
		if(state.equals("FISH"))
			myCell = new FishCell(this, 1, (int) thresholdValue);
		else if(state.equals("SHARK"))
			myCell = new SharkCell(this, 2, (int) thresholdValue);
	}
	
	@Override
	public void setPatchState(String state) {};

	@Override
	public void setCellState(String state) {
		myCell.setState(state);
	}

	@Override
	public void updatePatch() {
		myCell.updateCell();
	}
}
