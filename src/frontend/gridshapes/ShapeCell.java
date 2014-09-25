package frontend.gridshapes;

import javafx.scene.shape.Polygon;

/**
 * This abstract class holds the generic ShapeCell object, of which Hexagon, Triangle, and 
 * Square grid cell types are a part.  
 */

public abstract class ShapeCell {
	public Polygon myShape;
	public double myXCenter;
	public double myYCenter;
	public double mySideLength;
	public double myRadius;
	public int myInvertShape;


	/**
	 * Returns the appropriate shape based on what grid cell type is instantiated
	 */
	public Polygon getShape() {
		return myShape;
	}

	/**
	 * Method that sets the properties and states of the shape in question. Calls
	 * calculateVertices() which creates the polygon containing the correct number 
	 * of vertices.
	 * @param xCenter - x-coordinate of the shape's center
	 * @param yCenter - y-coordinate of the shape's center
	 * @param sideLength - length of a side of a shape
	 * @param invertShape - whether shape to be inverted, such as in the case of a triangle 
	 * where two neighboring cells are inverted versions of one another.      
	 */
	public void setShapeParams(double xCenter, double yCenter, double sideLength, int invertShape) {
		myXCenter = xCenter;
		myYCenter =  yCenter;
		mySideLength = sideLength;
		myRadius = sideLength/1.5;
		myInvertShape = invertShape;
		calculateVertices();


	}

	/**
	 * Returns x-coordinate of the center, so that we can display cells
	 * of the appropriate size on the patches.
	 */
	public double getX() {
		return myXCenter;
	}

	/**
	 * Returns y-coordinate of the center, so that we can display cells of the 
	 * appropriate size on the patches.
	 */
	public double getY() {
		return myYCenter;
	}

	/**
	 * Abstract method that calculates the vertices of the shape in question.  Each 
	 * additional shape would need to implement this feature.
	 */
	public abstract void calculateVertices();

}
