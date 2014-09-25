package frontend.gridshapes;

import javafx.scene.shape.Polygon;

public class HexagonCell extends ShapeCell {
	
	
	public HexagonCell(double xCenter, double yCenter, double sideLength) {
		mySideLength = sideLength;
		super.setCenter(xCenter, yCenter);
		calculateVertices();
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
