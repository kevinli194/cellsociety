package backend.neighborsetters;

import backend.patches.Patch;

public abstract class NeighborSetter {

	public void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		if (boundaryType.equals("FINITE")) {
			if (gridShape == "SQUARE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recBounded(grid, i, j);
					}
				}
			}
			if (gridShape == "TRIANGLE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						triBounded(grid, i, j);
					}
				}
			}
			if (gridShape.equals("HEXAGON")) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexBounded(grid, i, j);
					}
				}
			}

		}
		if (boundaryType.equals("TOROIDAL")) {
			if (gridShape.equals("SQUARE")) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recToroidal(grid, i, j);
					}
				}
			}
			if (gridShape.equals("TRIANGLE")) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						triToroidal(grid, i, j);
					}
				}
			}
			if (gridShape.equals("HEXAGON")) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexToroidal(grid, i, j);
					}
				}
			}

		}

	}

	public void recToroidal(Patch[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j);

	}

	public void triToroidal(Patch[][] grid, int i, int j) {
		triBounded(grid, i, j);
		addCardinalEdges(grid, i, j);
		addDiagonalEdges(grid, i, j);

		// how to add appropriate edges

	}

	public void hexToroidal(Patch[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addHexEdges(grid, i, j);

	}

	public void recBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
	}

	public void triBounded(Patch[][] grid, int i, int j) {
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

	public void hexBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j);
		addHexDiags(grid, i, j);

	}

	protected void addCardinalNeighbors(Patch[][] grid, int i, int j) {
		if (i > 0)
			grid[i][j].addNeighbor(grid[i - 1][j]);
		if (j > 0)
			grid[i][j].addNeighbor(grid[i][j - 1]);
		if (i < grid.length - 1)
			grid[i][j].addNeighbor(grid[i + 1][j]);
		if (j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][j + 1]);
	}

	protected void addDiagonalNeighbors(Patch[][] grid, int i, int j) {
		if (i > 0 && j > 0)
			grid[i][j].addNeighbor(grid[i - 1][j - 1]);
		if (i > 0 && j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i - 1][j + 1]);
		if (i < grid.length - 1 && j > 0)
			grid[i][j].addNeighbor(grid[i + 1][j - 1]);
		if (i < grid.length - 1 && j < grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i + 1][j + 1]);
	}

	protected void addCardinalEdges(Patch[][] grid, int i, int j) {
		if (i == 0)
			grid[i][j].addNeighbor(grid[grid.length - 1][j]);
		if (j == 0)
			grid[i][j].addNeighbor(grid[i][grid.length - 1]);
		if (i == grid.length - 1)
			grid[i][j].addNeighbor(grid[0][j]);
		if (j == grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][0]);
	}

	protected void addDiagonalEdges(Patch[][] grid, int i, int j) {
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

	protected void addHexDiags(Patch[][] grid, int i, int j) {
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

	private void addHexEdges(Patch[][] grid, int i, int j) {
		if (i == 0) {
			grid[i][j].addNeighbor(grid[grid.length - 1][j]);
			if (j != 0)
				grid[i][j].addNeighbor(grid[grid.length - 1][j - 1]);
		}
		if (i == grid.length - 1) {
			grid[i][j].addNeighbor(grid[0][j]);
			if (j != grid[0].length-1)
				grid[i][j].addNeighbor(grid[0][j + 1]);
		}
		if (j == 0)
			grid[i][j].addNeighbor(grid[i][grid[0].length - 1]);
		if (j == grid[0].length - 1)
			grid[i][j].addNeighbor(grid[i][0]);
		// how to set edges without confusion
	}

}
