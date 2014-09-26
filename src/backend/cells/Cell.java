package backend.cells;

import backend.patches.Patch;

public abstract class Cell {
	protected int myState;
	protected int myPreviousState;
	protected Patch myPatch;
	
	public int getState(){
		return myState;
	}
	public void setState(int state){
		myState = state;
	}

	public void setPatch(Patch patch){
		myPatch = patch;
	}
	public int getPrevState(){
		return myPreviousState;
	}
	public abstract void update();
	

}
