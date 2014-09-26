package frontend.gridshapes;

/**
 * This factory class creates cells of the appropriate grid type based on 
 * the type of grid shape queried by the user.
 */
public class ShapeFactory {
	private static final String HEXAGON = "HEXAGON";
	private static final String SQUARE = "SQUARE";
	private static final String TRIANGLE = "TRIANGLE";
	private ShapeCell[][] myCellShapes;
	private double mySideLength;
	private double myCenterToVerticesDistance;


	public ShapeFactory(String shape, int rowSize, int colSize, double sideLength, double centerToVertices) {
		mySideLength = sideLength;
		myCenterToVerticesDistance = centerToVertices;
		chooseShape(shape, rowSize, colSize);
		populateCellShapes(shape, rowSize, colSize);
	}

	/**
	 * Selects the appropriate type of ShapeCell to implement and allocates space for this [][]
	 * @param shape - string indicating shape name
	 * @param rowSize - number of rows of the shape
	 * @param colSize - number of columns of the shape
	 */
	private void chooseShape(String shape, int rowSize, int colSize) {

		if (shape.equals(HEXAGON))
			myCellShapes = new HexagonCell[rowSize][colSize];

		if (shape.equals(SQUARE))  
			myCellShapes = new SquareCell[rowSize][colSize];

		if (shape.equals(TRIANGLE)) 
			myCellShapes = new TriangleCell[rowSize][colSize];
	}

	/**
	 * Creates individual shapes of the view for the cells by iterating through the rows and columns 
	 * of the grid.
	 * @param shape - string indicating shape name
	 * @param rowSize - number of rows of the shape
	 * @param colSize - number of columns of the shape
	 */
	private void populateCellShapes(String shape, int rowSize, int colSize) {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (shape.equals(HEXAGON))
					myCellShapes[row][col] = new HexagonCell(((row%2)*myCenterToVerticesDistance) + (2*col*myCenterToVerticesDistance),
							(row*((3*mySideLength)/2)), mySideLength, 0);

				if (shape.equals(SQUARE))
					myCellShapes[row][col] = new SquareCell(col*mySideLength, row*mySideLength, mySideLength, 0);

				if (shape.equals(TRIANGLE))
					myCellShapes[row][col] = new TriangleCell(((mySideLength*Math.sqrt(3))/2)*col, 
							mySideLength*(3*row/2.0) - (((row+col+1)%2))*(mySideLength/2), mySideLength, ((row+col+1)%2));
			}
		}
	}

	/**
	 * Returns the array of grid cell shapes that are used to physically control the individual states
	 * of these shapes.
	 */
	public ShapeCell[][] getShapes() {
		return myCellShapes; 
	}

}