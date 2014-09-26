package backend.patches;

import backend.cells.Cell;
import backend.cells.SugarAgentCell;
import javafx.scene.paint.Color;

public class SugarPatch extends Patch {

	private final static int DEAD = 0;
	private final static int ALIVE = 1;

	private double myMaximumCapacity;
	private int mySugarGrowBackInterval;
	private int mySugarGrowBackRate;
	private int mySugarTicks;

	public SugarPatch(int xCoord, int yCoord) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myMaximumCapacity = 4;
		myState = (int) myMaximumCapacity;
		mySugarGrowBackInterval = 3;
		mySugarGrowBackRate = 1;
		mySugarTicks = 0;
		myPossibleStates = 2;
		myCell = new SugarAgentCell(this);
	}

	@Override
	public Color getColor() {
		if (myCell.myState == DEAD) {
			return Color.web("0x0000FF", ((double) myState) / myMaximumCapacity);
		} else
			return Color.RED;
	}

	@Override
	public void setState(String state) {
		if (state.equals("ALIVE"))
			myCell.setState(ALIVE);
		else
			myCell.setState(DEAD);
	};

	@Override
	public void update() {
		if (myUpdated == false) {
			myUpdated = true;
			mySugarTicks++;
			if (mySugarTicks >= mySugarGrowBackInterval) {
				mySugarTicks = 0;
				myState += mySugarGrowBackRate;
				if (myState > myMaximumCapacity) {
					myState = (int) myMaximumCapacity;
				}
			}
		}
	}
	
	public void resetCell() {
		((SugarAgentCell) myCell).reset();
	}

	public void swapCells(Patch patch) {
		Cell tempCell = patch.myCell;
		patch.myCell = myCell;
		myCell.myPatch = patch;
		myCell = tempCell;
	}
}
