package backend.neighborsetters;

import backend.cells.Cell;

public class SegNeighborSetter extends NeighborSetter {
	@Override
	public void recBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
		addDiagonalNeighbors(grid, i, j);
	}

	@Override
	public void recToroidal(Cell[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j);
		addDiagonalEdges(grid, i, j);

	}
}
