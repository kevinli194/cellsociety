package backend.cells;

import backend.patch.SugarPatch;

public class SugarAgentCell extends Cell {

	private static final int DEAD = 0;
	private static final int ALIVE = 1;
	private int mySugarAmount;
	private int myVision;
	private int mySugarMetabolism;
	
	public SugarAgentCell(SugarPatch patch)
	{
		myPatch = patch;
	}
	
	@Override
	public void setState(String state) {};

	@Override
	public void updateCell() {
		if(myUpdated == false && myState == ALIVE)
		{
			myNeighbors = myPatch.getCellNeighbors();
			int maximumSugarAmongNeighbors = Integer.MIN_VALUE;
			SugarAgentCell maximumNeighbor = null;
			
			for(Cell neighbor : myNeighbors)
			{
				int neighborSugar = ((SugarAgentCell) neighbor).mySugarAmount;
				if(neighborSugar > maximumSugarAmongNeighbors)
				{
					maximumSugarAmongNeighbors = neighborSugar;
					maximumNeighbor = (SugarAgentCell) neighbor;
				}
			}
			
			SugarPatch maximumPatch = (SugarPatch) maximumNeighbor.myPatch;
			maximumPatch.reassignCell(this);
			mySugarAmount += maximumPatch.getState();
			if(mySugarAmount < 0)
			{
				myState = DEAD;
			}
		}
	}	
}
