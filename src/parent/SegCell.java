package parent;

public class SegCell extends Cell {
	private static final int EMPTY = 0;
	private static final int X = 1;
	private static final int O = 2;

	public SegCell(int xCoord, int yCoord, boolean update, int state) {
		myCoordinates[0] = xCoord;
		myCoordinates[1] = yCoord;
		myUpdate = update;
		myState = state;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
