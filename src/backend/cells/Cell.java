package backend.cells;

import backend.patches.Patch;

/**
 * 
 * @author CS308 Team16
 * The Cell represents a mobile unit in the grid and overall simulation mode.
 * Cells are bound to a Patch in the grid (and vice versa), such that
 * There is no more than 1 Cell to a Patch, and no more than 1 Patch to a Cell.
 * Cells are intended to move around the grid, which is the primary
 * difference between Cells and Patches.
 */
public abstract class Cell {

	protected int myState;
	protected int myPreviousState;
	protected Patch myPatch;
	
	public int getState(){
		return myState;
	}
	
	/**
	 * Sets the state of the cell
	 * @param state
	 * 		The input state encoded by the static constants in each extension of Cell.
	 */
	public void setState(int state){
		myState = state;
	}

	public void setPatch(Patch patch){
		myPatch = patch;
	}
	public int getPrevState(){
		return myPreviousState;
	}
	/**
	 * On each round/iteration of the simulation, the Cell will run its own logic that 
	 * may potentially update its state.
	 */
	public abstract void update();
	
}
