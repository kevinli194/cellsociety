package parent;

public class SegCell extends Cell {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int O = 2;
	private SegCellManager mySegCellManager;
	
	public SegCell(int xCoord, int yCoord, boolean update, int state, SegCellManager segCellManager, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
		mySegCellManager = segCellManager;
		myThresholdValue = thresholdValue;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int satisfiedNeighbors = 0;
		int totalNeighbors = 0;
		for(Cell neighbor : myNeighbors)
		{
			if(neighbor.myState != EMPTY)
			{
				if(neighbor.myState == myState)
				{
					satisfiedNeighbors++;
				}
				
				totalNeighbors++;
			}
		}
		
		double percentageOfNeighborsSatisfied = satisfiedNeighbors / totalNeighbors;
		if(percentageOfNeighborsSatisfied < myThresholdValue)
		{
			Cell emptyCell = mySegCellManager.findFirstEmptyCell();
			if(emptyCell != null)
			{
				emptyCell.myPreviousState = emptyCell.myState;
				emptyCell.myState = myState;
				myPreviousState = myState;
				myState = EMPTY;
			}
		}
	}
	
	public void setState(String state){
		if (state.equals("EMPTY")){
			myState = EMPTY;
		}
		if (state.equals("X")){
			myState = X;
		}
		if (state.equals("O")){
			myState = O;
		}
	}

}
