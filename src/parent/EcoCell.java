package parent;

public class EcoCell extends Cell {
	private static final int EMPTY = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private int myTurnsAlive;
	
	public EcoCell(int xCoord, int yCoord, boolean update, int state, int breedingTime) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdated = update;
		myState = state;
		myThresholdValue = breedingTime;
		myTurnsAlive = 0;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see parent.Cell#update()
	 * This method is for updating the sharks
	 */
	public void update() {
		// TODO Auto-generated method stub
		if(myState == SHARK && myUpdated == false)
		{
			myUpdated = true;
			Cell fishNeighbor = findNeighborOfType(FISH);
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if(fishNeighbor != null)
			{
				updateNeighbor(fishNeighbor, EMPTY);
			}
			else if(emptyNeighbor != null)
			{
				updateNeighbor(emptyNeighbor, SHARK);
			}
		}
	}
	
	/*
	 * This method is for updating the fish
	 */
	public void update2() {
		if(myState == FISH && myUpdated == false)
		{
			myUpdated = true;
			Cell emptyNeighbor = findNeighborOfType(EMPTY);
			if(emptyNeighbor != null)
			{
				updateNeighbor(emptyNeighbor, FISH);
			}
		}
	}
	
	private void updateNeighbor(Cell neighbor, int state)
	{
		neighbor.myPreviousState = neighbor.myState;
		neighbor.myState = state;
	}
	
	private Cell findNeighborOfType(int stateID)
	{
		for(Cell neighbor : myNeighbors)
		{
			if(neighbor.myState == stateID)
			{
				return neighbor;
			}
		}
		
		return null;
	}
	
	public void setState(String state){
		if (state.equals("EMPTY")){
			myState = EMPTY;
		}
		if (state.equals("FISH")){
			myState = FISH;
		}
		if (state.equals("SHARK")){
			myState = SHARK;
		}
	}

}
