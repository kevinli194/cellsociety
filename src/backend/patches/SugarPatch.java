package backend.patches;

import backend.cells.SugarAgentCell;
import javafx.scene.paint.Color;

/**
 * 
 * @author CS308 Team16
 * SugarPatch is the Patch for the Sugarscape simulation.
 * SugarPatches contain SugarAgentCells.
 * SugarPatch has 2 variables: the amount of sugar and the maximum sugar capacity.
 * Initially, the amount of sugar is equal to the maximum capacity (patch is full).
 * The patch is displayed as different hues based on how much sugar is in it.
 */
public class SugarPatch extends Patch {

	/**
	 * States for the SugarAgentCell
	 */
	private final static int DEAD = 0;
	private final static int ALIVE = 1;

	private double myMaximumCapacity;
	private int mySugarGrowBackInterval;
	private int mySugarGrowBackRate;
	private int mySugarTicks;

	/**
	 * Constructor for SugarPatch
	 * @param xCoord
	 * @param yCoord
	 */
	public SugarPatch(int xCoord, int yCoord) {
		super(xCoord, yCoord, 0);
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
		if (myCell.getState() == DEAD) {
			return Color.web("0x0000FF", ((double) myState) / myMaximumCapacity);
		} else
			return Color.RED;
	}

	@Override
	public void setInitialState(String state) {
		if (state.equals("ALIVE"))
			myCell.setState(ALIVE);
		else
			myCell.setState(DEAD);
	};

	/**
	 * Update for the Patch involves waiting for a sugar ticker
	 * After some time interval, the SugarPatch will grow back some sugar.
	 */
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
}
