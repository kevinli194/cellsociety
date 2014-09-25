package frontend.gridshapes;

public class ShapeFactory {
	private static final String HEXAGON = "HEXAGON";
	private static final String SQUARE = "SQUARE";
	private static final String TRIANGLE = "TRIANGLE";
	private ShapeCell[][] myCellShapes;
	private double mySideLength;
	private double myCenterToVerticesDistance;




	public ShapeFactory(String shape, double sideLength, double centerToVertices) {
		mySideLength = sideLength;
		myCenterToVerticesDistance = centerToVertices;
		chooseShape(shape);

	}

	private void chooseShape(String shape) {

		if (shape.equals(HEXAGON))
			myCellShapes = new HexagonCell[4][4];

		if (shape.equals(SQUARE))  
			myCellShapes = new SquareCell[4][4];

		if (shape.equals(TRIANGLE)) 
			myCellShapes = new TriangleCell[4][4];


	}


	private void populateCellShapes(int rowSize, int colSize) {

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {

				// grid[row][col] = new HexagonCell((grid[0][0].getX() + ((row%2)*centerToVertices) + (2*col*centerToVertices)), grid[0][0].getY() + (row*((3*sideLength)/2)), sideLength);
				// grid[row][col] = new SquareCell(grid[0][0].getX() + row*s, grid[0][0].getY()+ col*s, s);
				//grid[row][col] = new TriangleCell(((sideLength*Math.sqrt(3))/2)*col, sideLength*(3*row/2.0) - (((row+col+1)%2))*(sideLength/2), sideLength, ((row+col+1)%2));
			}
		}
	}

}
