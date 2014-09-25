package backend.neighborsetters;

import backend.patch.Patch;

public class GoLNeighborSetter extends NeighborSetter {
	@Override
	public void recBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
		addDiagonalNeighbors(grid, i, j);
	}
	@Override
	public void recToroidal(Patch[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j);
		addDiagonalEdges(grid, i, j);

	}
}