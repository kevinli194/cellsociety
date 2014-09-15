package parent;

import java.util.ArrayList;

public abstract class Cell {
	// instance variables shared by all cell classes.
	protected ArrayList<Cell> myNeighbor;
	protected int[] myCoordinates = new int[2];
	protected boolean myUpdate;

	// adding cell classes to myNeighbor Arraylist.
	public void addNeighbor(Cell neighbor) {
		myNeighbor.add(neighbor);
	}

	// abstract method implemented by subclasses that determines the rules for
	// updating each cell each turn.
	public abstract void update();

	// abstract method for taking a string of the state and converting it into a
	// numerical value to store as myState.
	public abstract void setState(String state);

	public void setUpdate(boolean update) {
		myUpdate = update;

	}
}