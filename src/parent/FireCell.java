package parent;


public class FireCell extends Cell {
	private static final int EMPTY = 0;
	private static final int TREE = 1;
	private static final int BURNING = 2;
	/*
	 * is this array necessary? private int[] myPossibleStates =
	 * {EMPTY,FISH,SHARK};
	 */
	private int myState;

	public FireCell(int xCoord, int yCoord,
			boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	

	}
	@Override
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
