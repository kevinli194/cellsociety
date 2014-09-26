package backend.patches;

import javafx.scene.paint.Color;
import backend.cells.SegCell;

/**
 * 
 * @author CS308 Team16
 * SegPatch is the Patch for segregation simulation.
 * SegPatch holds a SegCell, which will be looking at neighbors and
 * moving around the grid until satisfied.
 */
public class SegPatch extends Patch {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int O = 2;
	private SegPatch myEmptyPatch;
	
	/**
	 * Constructor for SegPatch
	 * @param xCoord
	 * @param yCoord
	 * @param thresholdValue
	 * 		thresholdValue is passed to the Cell for its update logic.
	 */
	public SegPatch(int xCoord, int yCoord, double thresholdValue) { 
		super(xCoord, yCoord, thresholdValue);
		myUpdated = false;
		myCell = new SegCell(EMPTY, this, thresholdValue);
		myPossibleStates = 3;
	}

	@Override
	public Color getColor() {
		if (myCell.getState() == X)
			return Color.BLACK;
		else if (myCell.getState() == O)
			return Color.RED;
		else 
			return Color.WHITE;
			
	}
	
	@Override
	public void setInitialState(String state) {
		if(state.equals("X"))
			myCell.setState(X);
		
		if (state.equals("O"))
			myCell.setState(O);	
	}

	/**
	 * Store an empty patch from the Simulation/grid to send to the cell.
	 * @param segPatch
	 */
	public void setEmptyPatch(SegPatch segPatch) {
		myEmptyPatch = segPatch;		
	}
	
	public SegPatch getEmptyPatch() {
		return myEmptyPatch;
	}
	
	@Override
	public void update() {
		updateCell();
	}

}
