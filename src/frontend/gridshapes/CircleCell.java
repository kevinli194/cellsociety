package frontend.gridshapes;
import javafx.scene.shape.Circle;


public class CircleCell extends ShapeCell {

	public CircleCell(int windowHeight, int numCells) {
		myShape = new Circle();
		((Circle) myShape).setRadius(windowHeight/(numCells*2.2));
	}
}
