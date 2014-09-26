package backend.patches;

import java.util.Collections;

import backend.cells.Cell;
import backend.cells.EmptyCell;
import backend.cells.FishCell;
import backend.cells.SharkCell;
import javafx.scene.paint.Color;

public class EcoPatch extends Patch {
	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;

	public EcoPatch(int xCoord, int yCoord, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = false;
		myCell = new EmptyCell(this);
		myThresholdValue = thresholdValue;
		myPossibleStates = 3;
	}

	@Override
	public Color getColor() {
		if (myCell.getState() == FISH)
			return Color.YELLOW;
		if (myCell.getState() == SHARK)
			return Color.GRAY;
		else{
			return Color.BLUE;
		}

	}

	@Override
	public void setInitialState(String state) {
		// TODO Auto-generated method stub
		if (state.equals("EMPTY")) {
			myCell = new EmptyCell(this);
		}
		if (state.equals("FISH")) {
			myCell = new FishCell(this, myThresholdValue);

		}
		if (state.equals("SHARK")) {
			myCell = new SharkCell(this, myThresholdValue);
		}

	}

	public void setState(int type) {
		// TODO Auto-generated method stub
		if (type == EMPTY) {
			myCell = new EmptyCell(this);
		}
		if (type == FISH) {
			myCell = new FishCell(this, myThresholdValue);

		}
		if (type == SHARK) {
			myCell = new SharkCell(this, myThresholdValue);
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	public void updateShark(){
		if (myCell.getState() ==SHARK)
		myCell.update();
	}
	public void updateFish(){
		if (myCell.getState() == FISH)
			myCell.update();
		
	}

	public Patch findNeighborOfType(int stateID) {
		Collections.shuffle(myNeighbors);
		for (Patch neighbor : myNeighbors) {
			if (neighbor.myCell.getState() == stateID) {
				return neighbor;
			}
		}

		return null;
	}

	public void swapCells(Patch patch) {
		Cell tempCell = patch.myCell;
		patch.myCell = myCell;
		myCell.setPatch(patch);
		myCell = tempCell;

	}

	public void clearCell() {
		myCell = new EmptyCell(this);
		myUpdated = true;
	}

}
