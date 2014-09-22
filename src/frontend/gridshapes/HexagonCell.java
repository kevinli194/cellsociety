package frontend.gridshapes;

import javafx.scene.shape.Polygon;

public class HexagonCell extends ShapeCell {
	private Polygon myHexagon;
	private double myXCenter;
	private double myYCenter;
	private double mySideLength;
	
	public HexagonCell(double xCenter, double yCenter, double sideLength) {
		mySideLength = sideLength;
		setCenter(xCenter, yCenter);
		calculateVertices();
	}
	
	
	private void setCenter(double xCenter, double yCenter) {
		myXCenter = xCenter;
		myYCenter =  yCenter;
	}
	
	private void calculateVertices() {
		double centerToVertices = (Math.sqrt(3)*(mySideLength/2)); 
				
				
		myHexagon =  new Polygon(myXCenter, myYCenter - mySideLength, 
				myXCenter + centerToVertices, myYCenter - (mySideLength/2), 
				myXCenter + centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter, myYCenter + mySideLength, 
				myXCenter - centerToVertices, myYCenter + (mySideLength/2), 
				myXCenter - centerToVertices, myYCenter - (mySideLength/2));
		
	}
	
	public Polygon getHexagon() {
		return myHexagon;
	}


	public double getX() {
		return myXCenter;
	}
	
	public double getY() {
		return myYCenter;
	}
	
	
	/*public HexagonCell(int height, int numXCells, int numYCells) {
	Polygon polyTop = new Polygon();
	polyTop.getPoints().addAll(new Double[]{
	        0.0, 0.0,
	        20.0, (double) (height/(numXCells)),
	        40.0, 0.0 });
	Polygon polyBot = new Polygon();
	polyBot.getPoints().addAll(new Double[]{
			20.0, (double) (height/numXCells),
			40.0, 0.0,
	        60.0, (double) (height/(numXCells)) });
	
	Polygon poly3 = new Polygon();
	poly3.getPoints().addAll(new Double[]{
	        0.0, 0.0,
	        20.0, (double) (myHeight/(myGameParams.gridXSize)),
	        40.0, 0.0 });
	Polygon poly4 = new Polygon();
	poly4.getPoints().addAll(new Double[]{
			20.0, (double) (myHeight/(myGameParams.gridXSize)),
			40.0, 0.0,
	        60.0, (double) (myHeight/(myGameParams.gridXSize)) });
	
	
	poly.setFill(Color.GREEN);
	poly2.setFill(Color.BROWN);
	poly3.setFill(Color.BLUE);
	poly4.setFill(Color.RED);

	shape.setStyle("-fx-background-color: black");// black; fx-hgap: 10;-fx-vgap: 10;");
	myGridPane.add(shape, col, row);
	shape.add(poly,  0,  0);
	shape.add(poly2, 0, 1);
	shape.add(poly3, 1, 1);
	shape.add(poly4, 1, 0);
	}*/


}
