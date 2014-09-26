package backend.patches;

import javafx.scene.paint.Color;
import backend.cells.FireCell;

public class TreePatch extends Patch {

	private static final int NOT_BURNING = 0;
	private static final int BURNING = 1;
	private int myWoodAmount;

	public TreePatch(int xCoord, int yCoord, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = false;
		myState = 0;
		myThresholdValue = thresholdValue;
		myCell = new FireCell(NOT_BURNING, this);
		myPossibleStates = 2;
	}

	@Override
	/**
	 * Updates the trees. 
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
			if (cellNeighbor.myState == BURNING) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setState(String state) {
		if (state.equals("TREE")) {
			myWoodAmount = 1;
		}
		if (state.equals("BURNING")) {
			myCell.setState(BURNING);
		}
	}


	@Override
	public Color getColor() {
		if (myCell.myState == BURNING) {
			return Color.RED;
		} else if ((myCell.myState == NOT_BURNING) && myWoodAmount > 0) {
			return Color.GREEN;
		} else
			return Color.WHITE;
	}
}
