package frontend.gridshapes;

import javafx.scene.shape.Polygon;

public abstract class ShapeCell {
	public Polygon myShape;
	public double myXCenter;
	public double myYCenter;
	
	public abstract void calculateVertices();

	public Polygon getShape() {
		return myShape;
	}
	
	public void setCenter(double xCenter, double yCenter) {
	myXCenter = xCenter;
	myYCenter =  yCenter;
	
	}

	public double getX() {
		return myXCenter;
	}

	public double getY() {
		return myYCenter;
	}




}
