package backend.patches;

import backend.cells.Cell;
import backend.cells.SugarAgentCell;
import javafx.scene.paint.Color;

public class SugarPatch extends Patch {

	private int myMaximumCapacity;
	private int mySugarGrowBackInterval;
	private int mySugarGrowBackRate;
	private int mySugarTicks;
	private int mySugarAmount;

	public SugarPatch(int xCoord, int yCoord) {
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
	public Color getColor() {
		return null;
	}

	@Override
	public void setState(String state) {
	};

	@Override
	public void update() {
		if (myUpdated == false) {
			myUpdated = true;
			mySugarTicks++;
			if (mySugarTicks > mySugarGrowBackInterval) {
				mySugarAmount += mySugarGrowBackRate;
				if (mySugarAmount > myMaximumCapacity) {
					mySugarAmount = myMaximumCapacity;
				}
			}
		}
	}
	
	public void swapCells(Patch patch) {
		Cell tempCell = patch.myCell;
		patch.myCell = myCell;
		myCell.myPatch = patch;
		myCell = tempCell;
	}
}
