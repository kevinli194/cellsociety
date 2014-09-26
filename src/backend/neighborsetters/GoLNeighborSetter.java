package backend.neighborsetters;

import backend.patches.Patch;

public class GoLNeighborSetter extends NeighborSetter {
	@Override
	public void recAndTriBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
		addDiagonalNeighbors(grid, i, j);
	}
	@Override
	public void recAndTriToroidal(Patch[][] grid, int i, int j) {
		recAndTriBounded(grid, i, j);
		addCardinalEdges(grid, i, j);
		addDiagonalEdges(grid, i, j);

	}
}