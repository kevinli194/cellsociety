package backend.patches;

import javafx.scene.paint.Color;
import backend.cells.SegCell;

public class SegPatch extends Patch {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int O = 2;
	private SegPatch myEmptyPatch;
	

	public SegPatch(int xCoord, int yCoord, double thresholdValue) { 
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = false;
		myCell = new SegCell(EMPTY, this, thresholdValue);
		myPossibleStates = 3;
	}

	@Override
	public Color getColor() {
		if (myCell.myState == X)
			return Color.BLACK;
		else if (myCell.myState == O)
			return Color.RED;
		else 
			return Color.WHITE;
			
	}

	@Override
	public void updateCell() {
		myCell.update();
	}

	@Override
	public void setState(String state) {
		if(state.equals("X"))
			myCell.myState = X;
		
		if (state.equals("O"))
			myCell.myState = O;
		
	}

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
