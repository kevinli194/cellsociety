package frontend.gridshapes;
import javafx.scene.shape.Polygon;


public class TriangleCell extends ShapeCell {
	private double myRadius;
	private int myIsFlip;

	public TriangleCell(double xCenter, double yCenter, double radius, int flipTriangle) {
		myRadius = radius;
		myIsFlip = flipTriangle;
		super.setCenter(xCenter, yCenter);
		calculateVertices();

	}

	
	@Override
	public void calculateVertices() {
		if (myIsFlip == 1){ 
		myShape =  new Polygon(myXCenter, myYCenter + myRadius, myXCenter + ((Math.sqrt(3)*myRadius)/2), 
				(myYCenter - (myRadius/2)), (myXCenter - (Math.sqrt(3)*myRadius)/2),
				(myYCenter - (myRadius/2)));
		}
		else {
		myShape =  new Polygon(myXCenter, myYCenter - myRadius, myXCenter + ((Math.sqrt(3)*myRadius)/2), 
				(myYCenter + (myRadius/2)), (myXCenter - (Math.sqrt(3)*myRadius)/2),
				(myYCenter + (myRadius/2)));
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
