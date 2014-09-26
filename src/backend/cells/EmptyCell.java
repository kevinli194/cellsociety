package backend.cells;

import backend.patches.Patch;
public class EmptyCell extends Cell{
	private int EMPTY = 0;

	public EmptyCell(Patch patch){
		myPatch = patch;
		myState = EMPTY;
	}
	public void update() {	
	}

}
