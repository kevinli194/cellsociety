package backend.neighborsetters;

import backend.patches.Patch;

public class SugarNeighborSetter extends NeighborSetter {
	private int myVision;
	
	public SugarNeighborSetter(int vision) {
		myVision = vision;
	}

	@Override
	public void recAndTriToroidal(Patch[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addCardinalEdges(grid, i, j, myVision);

	}

	@Override
	public void recAndTriBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, myVision);
	}

	@Override
	public void hexToroidal(Patch[][] grid, int i, int j) {
		hexBounded (grid, i, j);
		addCardinalEdges(grid, i, j, myVision);
	}
	
	
	@Override
	public void hexBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, myVision);

	}

}