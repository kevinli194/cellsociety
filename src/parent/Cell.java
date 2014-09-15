package parent;

import java.util.ArrayList;

public abstract class Cell {
	protected ArrayList<Cell> myNeighbor = new ArrayList<Cell>();
	protected int[] myCoordinates;
	protected boolean myUpdate;
	protected int myState;

	public void addNeighbor(Cell neighbor){
		myNeighbor.add(neighbor);
	}
	public abstract void update();
	
	public int getState() {
		return myState;
	}
	
}