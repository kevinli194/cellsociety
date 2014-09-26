package frontend.gridshapes;
import javafx.scene.shape.Polygon;


public class TriangleCell extends ShapeCell {

	public TriangleCell(double xCenter, double yCenter, double radius, int invertShape) {		
		super.setShapeParams(xCenter, yCenter, radius, invertShape);
	}

	@Override
	public void calculateVertices() {
		if (myInvertShape == 1) { 
			myShape =  new Polygon(myXCenter, myYCenter + mySideLength, myXCenter + ((Math.sqrt(3)*mySideLength)/2), 
					(myYCenter - (mySideLength/2)), (myXCenter - (Math.sqrt(3)*mySideLength)/2),
					(myYCenter - (mySideLength/2)));
		}
		else {
			myShape =  new Polygon(myXCenter, myYCenter - mySideLength, myXCenter + ((Math.sqrt(3)*mySideLength)/2), 
					(myYCenter + (mySideLength/2)), (myXCenter - (Math.sqrt(3)*mySideLength)/2),
					(myYCenter + (mySideLength/2)));
		}

		/*myShape =  new Polygon(myXCenter, myYCenter - mySideLength, 
				myXCenter + centerToVertices, myYCenter - (mySideLength/2), 
				myXCenter + centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter, myYCenter + mySideLength,

				myXCenter - centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter - centerToVertices, myYCenter - (mySideLength/2));	
		 */
	}
}