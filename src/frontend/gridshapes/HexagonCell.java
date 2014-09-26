package frontend.gridshapes;

import javafx.scene.shape.Polygon;

public class HexagonCell extends ShapeCell {
	
	
	public HexagonCell(double xCenter, double yCenter, double sideLength, int invertShape) {
		mySideLength = sideLength;
		super.setShapeParams(xCenter, yCenter, sideLength, invertShape);
	}
	
	@Override
	public void calculateVertices() {
		double centerToVertices = (Math.sqrt(3)*(mySideLength/2)); 
	
		myShape =  new Polygon(myXCenter, myYCenter - mySideLength, 
				myXCenter + centerToVertices, myYCenter - (mySideLength/2), 
				myXCenter + centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter, myYCenter + mySideLength, 
				myXCenter - centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter - centerToVertices, myYCenter - (mySideLength/2));
	}		
}