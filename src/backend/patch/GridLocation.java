package backend.patch;

import java.util.List;

public abstract class GridLocation {
	protected List<GridLocation> myNeighbors;
	protected int[] myCoordinates = new int[2];
}
