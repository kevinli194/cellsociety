package backend.patches;

import javafx.scene.paint.Color;
import backend.cells.FireCell;

/**
 * 
 * @author CS308 Team16
 * TreePatch is the Patch for the Spreading of Fire simulation.
 * TreePatch stores an amount of wood that dictates how long it takes
 * for a tree to burn. From the Patch perspective, the tree is either
 * there or is not. The cell's state of burning will take precedence 
 * if the tree is burning.
 */
public class TreePatch extends Patch {

	/**
	 * TreePatch states.
	 */
	private static final int NOT_BURNING = 0;
	private static final int BURNING = 1;
	private int myWoodAmount;

	/**
	 * Constructor for TreePatch
	 * @param xCoord
	 * @param yCoord
	 * @param thresholdValue
	 * 		This thresholdValue relates to the probability of fire spreading.
	 */
	public TreePatch(int xCoord, int yCoord, double thresholdValue) {
		super(xCoord, yCoord, thresholdValue);
		myUpdated = false;
		myState = 0;
		myCell = new FireCell(NOT_BURNING, this);
		myPossibleStates = 2;
	}

	@Override
	/**
	 * Updates the trees, which has higher priority than fires.
	 */
	public void update() {
		if (myWoodAmount > 0 && myUpdated == false) {
			myUpdated = true;
			if (anyNeighborIsBurning()) {
				double probabilityValue = Math.random();
				if (probabilityValue > myThresholdValue) {
					startFire();
				}
			}
		}
	}

	/**
	 * Changes the cell state to Burning and affects the tree in this patch.
	 */
	public void startFire() {
		myWoodAmount = 0;
		myCell.setState(BURNING);
	}

	/**
	 * Method that checks if neighbors are in the state of burning.
	 * 
	 * @return returns whether any neighbors are burning.
	 */
	private boolean anyNeighborIsBurning() {
		for (Patch neighbor : myNeighbors) {
			FireCell cellNeighbor = (FireCell) neighbor.myCell;
			if (cellNeighbor.getState() == BURNING) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setInitialState(String state) {
		if (state.equals("TREE")) {
			myWoodAmount = 1;
		}
		if (state.equals("BURNING")) {
			myCell.setState(BURNING);
		}
	}

	@Override
	public Color getColor() {
		if (myCell.getState() == BURNING) {
			return Color.RED;
		} else if ((myCell.getState() == NOT_BURNING) && myWoodAmount > 0) {
			return Color.GREEN;
		} else
			return Color.WHITE;
	}
}
