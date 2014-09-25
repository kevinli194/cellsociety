package backend.patch;

import backend.cells.SugarAgentCell;

public class SugarPatch extends Patch {

	private int myMaximumCapacity;
	private int mySugarGrowBackInterval;
	private int mySugarGrowBackRate;
	private int mySugarTicks;
	private int mySugarAmount;
	
	public SugarPatch(int xCoord, int yCoord, int vision, int sugarMetabolism)
	{
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myMaximumCapacity = 4;
		mySugarAmount = myMaximumCapacity;
		mySugarGrowBackInterval = 3;
		mySugarGrowBackRate = 1;
		mySugarTicks = 0;
		
		myCell = new SugarAgentCell(this);
	}
	
	@Override
	public void setPatchState(String state) {};

	@Override
	public void setCellState(String state) {};

	@Override
	public void updatePatch() {
		if(myUpdated == false)
		{
			myUpdated = true;
			mySugarTicks++;
			if(mySugarTicks >= mySugarGrowBackInterval)
			{
				mySugarAmount += mySugarGrowBackRate;
				if(mySugarAmount > myMaximumCapacity)
					mySugarAmount = myMaximumCapacity;
			}
		}
	}

	@Override
	public void updateCell() {
		myCell.updateCell();
	}
	
	public void reassignCell(SugarAgentCell cell) {
		myCell = cell;
	}
}