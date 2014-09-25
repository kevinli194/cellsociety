package frontend.gridshapes;

import javafx.scene.shape.Polygon;
public class SquareCell extends ShapeCell {
	private double mySideLength;

	public SquareCell(double xCenter, double yCenter, double sideLength) {
		mySideLength = sideLength;
		super.setCenter(xCenter, yCenter);
		calculateVertices();

	}
	
	@Override
	public void calculateVertices() {
		myShape =  new Polygon(myXCenter-(mySideLength/2), (myYCenter + (mySideLength/2)), 
				myXCenter-(mySideLength/2), myYCenter - (mySideLength/2), 
				myXCenter+(mySideLength/2), myYCenter - (mySideLength/2), 
				myXCenter+(mySideLength/2), myYCenter + (mySideLength/2));
	}	



}

