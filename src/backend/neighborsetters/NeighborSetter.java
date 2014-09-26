package backend.neighborsetters;

import backend.cells.Cell;

public abstract class NeighborSetter {

	public void setNeighbors(Cell[][] grid, String boundaryType,
			String gridShape) {
		if (boundaryType == "finite") {
			if (gridShape == "rectangle") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recBounded(grid, i, j);
					}
				}
			}
			if (gridShape == "triangle") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						triBounded(grid, i, j);
					}
				}
			}
			if (gridShape == "hexagon") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexBounded(grid, i, j);
					}
				}
			}

		}
		if (boundaryType == "toroidal") {
			if (gridShape == "rectangle") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recToroidal(grid, i, j);
					}
				}
			}
			if (gridShape == "triangle") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						triToroidal(grid, i, j);
					}
				}
			}
			if (gridShape == "hexagon") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexToroidal(grid, i, j);
					}
				}
			}

		}

	}

	public void recToroidal(Cell[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j);

	}

	public void triToroidal(Cell[][] grid, int i, int j) {
		triBounded(grid, i, j);
		addCardinalEdges(grid, i, j);
		addDiagonalEdges(grid, i, j);

		// how to add appropriate edges

	}

	public void hexToroidal(Cell[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addHexEdges(grid, i, j);

	}

	public void recBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
	}

	public void triBounded(Cell[][] grid, int i, int j) {
		/*
		 * the mod 2 determines which direction the triangle is facing. This is
		 * the code for 3 neighbors. if (i > 0 && (i + j) % 2 == 0)
		 * grid[i][j].addNeighbor(grid[i - 1][j]); if (j > 0)
		 * grid[i][j].addNeighbor(grid[i][j - 1]); if (i < grid.length - 1 && (i
		 * + j) % 2 == 1) grid[i][j].addNeighbor(grid[i + 1][j]); if (j <
		 * grid[0].length - 1) grid[i][j].addNeighbor(grid[i][j + 1]);
		 */
		addCardinalNeighbors(grid, i, j);
		addDiagonalNeighbors(grid, i, j);
	}

	public void hexBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
		addHexDiags(grid, i, j);

	}

	protected void addCardinalNeighbors(Cell[][] grid, int i, int j) {
		if (i > 0)
			grid[i][j].addNeighbor(grid[i - 1][j]);
		if (j > 0)
			grid[i][j].addNeighbor(grid[i][j - 1]);
		if (i < grid.length - 1)
			grid[i][j].addNeighbor(grid[i + 1][j]);
		if (j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][j + 1]);
	}

	protected void addDiagonalNeighbors(Cell[][] grid, int i, int j) {
		if (i > 0 && j > 0)
			grid[i][j].addNeighbor(grid[i - 1][j - 1]);
		if (i > 0 && j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i - 1][j + 1]);
		if (i < grid.length - 1 && j > 0)
			grid[i][j].addNeighbor(grid[i + 1][j - 1]);
		if (i < grid.length - 1 && j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i + 1][j + 1]);
	}

	protected void addCardinalEdges(Cell[][] grid, int i, int j) {
		if (i == 0)
			grid[i][j].addNeighbor(grid[grid.length - 1][j]);
		if (j == 0)
			grid[i][j].addNeighbor(grid[i][grid.length - 1]);
		if (i == grid.length - 1)
			grid[i][j].addNeighbor(grid[0][j]);
		if (j == grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][0]);
	}

	protected void addDiagonalEdges(Cell[][] grid, int i, int j) {
		if (i == 0) {
			if (j != 0)
				grid[i][j].addNeighbor(grid[grid.length - 1][j - 1]);
			if (j != grid[0].length - 1)
				grid[i][j].addNeighbor(grid[grid.length - 1][j + 1]);
		}
		if (i == grid.length - 1) {
			if (j != 0)
				grid[i][j].addNeighbor(grid[0][j - 1]);
			if (j != grid[0].length - 1)
				grid[i][j].addNeighbor(grid[0][j + 1]);
		}
		if (j == 0) {
			if (i != 0)
				grid[i][j].addNeighbor(grid[i - 1][grid[0].length - 1]);
			if (i != grid.length - 1)
				grid[i][j].addNeighbor(grid[i + 1][grid[0].length - 1]);
		}
		if (j == grid[0].length - 1) {
			if (i != 0)
				grid[i][j].addNeighbor(grid[i - 1][0]);
			if (i != grid.length - 1)
				grid[i][j].addNeighbor(grid[i + 1][0]);
		}

	}

	protected void addHexDiags(Cell[][] grid, int i, int j) {
		if (i % 2 == 0) {
			if (i > 0 && j > 0)
				grid[i][j].addNeighbor(grid[i - 1][j - 1]);
			if (i < grid.length - 1 && j > 0)
				grid[i][j].addNeighbor(grid[i + 1][j - 1]);
		}
		if (i % 2 == 1) {
			if (i > 0 && j < grid[0].length - 1)
				grid[i][j].addNeighbor(grid[i - 1][j + 1]);

			if (i < grid.length - 1 && j < grid[0].length - 1)
				grid[i][j].addNeighbor(grid[i + 1][j + 1]);
		}
	}

	private void addHexEdges(Cell[][] grid, int i, int j) {
		if (i == 0) {
			grid[i][j].addNeighbor(grid[grid.length - 1][j]);
			if (j != 0)
				grid[i][j].addNeighbor(grid[grid.length - 1][j - 1]);
		}
		if (i == grid.length - 1) {
			grid[i][j].addNeighbor(grid[0][j]);
			if (j != grid[0].length)
				grid[i][j].addNeighbor(grid[0][j + 1]);
		}
		if (j == 0)
			grid[i][j].addNeighbor(grid[i][grid[0].length - 1]);
		if (j == grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][0]);
		// how to set edges without confusion
	}

}