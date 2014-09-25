package backend.neighborsetters;

import java.util.Random;

import backend.cells.Cell;

public class SugarNeighborSetter extends NeighborSetter {
	private int num;

	public SugarNeighborSetter() {
		Random rn = new Random();
		num = rn.nextInt(5) + 0;
	}

	@Override
	public void recToroidal(Cell[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j, num);

	}

	@Override
	public void triToroidal(Cell[][] grid, int i, int j) {
		triBounded(grid, i, j);
		addCardinalEdges(grid, i, j, num);

	}

	@Override
	public void hexToroidal(Cell[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addCardinalEdges(grid, i, j, num);

	}

	@Override
	public void recBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, num);
	}

	@Override
	public void triBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, num);
	}

	@Override
	public void hexBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, num);

	}

}