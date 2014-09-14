package parent;

public class FireCell extends Cell {
	private static final int EMPTY = 0;
	private static final int TREE = 1;
	private static final int BURNING = 2;
	private double myThresholdValue;
	
	public FireCell(int xCoord, int yCoord,
			boolean update, int state, double thresholdValue) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
		myThresholdValue = thresholdValue;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		myUpdate = true;
		if(myState == BURNING)
		{
			myState = EMPTY;
			return;
		}

		if(myState == TREE & anyNeighborIsBurning())
		{
			double probabilityValue = Math.random();
			if(probabilityValue < myThresholdValue)
			{
				myState = BURNING;
			}
		}
	}
	
	private boolean anyNeighborIsBurning()
	{
		for(int i = 0; i < myNeighbor.size(); i++)
		{
			if(myNeighbor.get(i).myState == BURNING)
				return true;
		}
		
		return false;
	}
	
	public void setState(String state){
		if (state.equals("EMPTY")){
			myState = EMPTY;
		}
		if (state.equals("TREE")){
			myState = TREE;
		}
		if (state.equals("BURNING")){
			myState = BURNING;
		}
	}

}
