package backend.patches;


import backend.cells.GoLCell;
import javafx.scene.paint.Color;

public class GoLPatch extends Patch {

	private static int DEAD = 0;
	private static int ALIVE = 1;

	public GoLPatch(int xCoord, int yCoord) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = false;
		myCell = new GoLCell(DEAD, this);
		myPossibleStates = 2;
	}

	@Override
	public Color getColor() {
		if (myCell.myState == ALIVE) {
			return Color.BLACK;
		} else
			return Color.WHITE;
	}

	@Override
	public void setState(String state) {
		if (state.equals("ALIVE"))
			myCell.myState = ALIVE;
	}

	@Override
	public void update() {
	}

	public int getAliveCount() {
		int count = 0;
		for (int i = 0; i < myNeighbors.size(); i++) {
			if (myNeighbors.get(i).myCell.myState == ALIVE
					&& myNeighbors.get(i).myUpdated == false)
				count++;
			if (myNeighbors.get(i).myCell.myPreviousState == ALIVE
					&& myNeighbors.get(i).myUpdated == true)
				count++;
		}
		return count;
	}

}
