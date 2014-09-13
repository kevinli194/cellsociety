package parent;

public class SegCellManager extends CellManager {

	@Override
	protected void makeNewCell(int i, int j) {
		// TODO Auto-generated method stub
		myGrid[i][j] = new SegCell(i, j, true, 0);
	}

	@Override
	protected void setNeighbors(int i, int j) {
		// TODO Auto-generated method stub
		if (i > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j]);
		}
		if (j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i][j - 1]);
		}
		if (i < myGrid.length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j]);
		}
		if (j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i][j + 1]);
		}
		if (i > 0 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j - 1]);
		}
		if (i > 0 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i - 1][j + 1]);
		}
		if (i < myGrid.length - 1 && j > 0) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j - 1]);
		}
		if (i < myGrid.length - 1 && j < myGrid[0].length - 1) {
			myGrid[i][j].addNeighbor(myGrid[i + 1][j + 1]);
		}

	}

}
