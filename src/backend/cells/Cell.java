package backend.cells;

import backend.patches.Patch;

public abstract class Cell {
	public int myState;
	protected Patch myPatch;
	
	public void setState(int state){
		myState = state;
	}
	public abstract void update();
	

}
