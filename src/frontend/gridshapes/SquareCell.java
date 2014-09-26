package frontend.gridshapes;

import javafx.scene.shape.Polygon;
public class SquareCell extends ShapeCell {

	public SquareCell(double xCenter, double yCenter, double sideLength, int invertShape) {
		super.setShapeParams(xCenter, yCenter, sideLength, invertShape);

	}
	
	@Override
	public void calculateVertices() {
		myShape =  new Polygon(myXCenter-(mySideLength/2), (myYCenter + (mySideLength/2)), 
				myXCenter-(mySideLength/2), myYCenter - (mySideLength/2), 
				myXCenter+(mySideLength/2), myYCenter - (mySideLength/2), 
				myXCenter+(mySideLength/2), myYCenter + (mySideLength/2));
	}	

}
