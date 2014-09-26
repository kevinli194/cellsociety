package backend.neighborsetters;

import backend.patches.Patch;

public abstract class NeighborSetter {

	public void setNeighbors(Patch[][] grid, String boundaryType,
			String gridShape) {
		if (boundaryType.equals("FINITE")) {
			if (gridShape == "SQUARE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recAndTriBounded(grid, i, j);
					}
				}
			}
			if (gridShape == "TRIANGLE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recAndTriBounded(grid, i, j);
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
						recAndTriToroidal(grid, i, j);
					}
				}
			}
			if (gridShape.equals("TRIANGLE")) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recAndTriToroidal(grid, i, j);
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

	public void recAndTriToroidal(Patch[][] grid, int i, int j) {
		recAndTriBounded(grid, i, j);
		addCardinalEdges(grid, i, j,1);

	}

	public void hexToroidal(Patch[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addHexEdges(grid, i, j);

	}

	public void recAndTriBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j,1);
	}

	public void hexBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, 1);
		addHexDiags(grid, i, j);

	}

	protected void addCardinalNeighbors(Patch[][] grid, int i, int j, int num) {
		int count = 1;
		while (num > 0) {
			if (i > num - 1)
				grid[i][j].addNeighbor(grid[i - count][j]);
			if (j > num - 1)
				grid[i][j].addNeighbor(grid[i][j - count]);
			if (i < grid.length - num)
				grid[i][j].addNeighbor(grid[i + count][j]);
			if (j < grid[0].length - num)
				grid[i][j].addNeighbor(grid[i][j + count]);
			num--;
			count++;
		}

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

	protected void addCardinalEdges(Patch[][] grid, int i, int j, int num) {
		int count = 1;
		while (num > 0) {
			if (i <= num - 1)
				grid[i][j].addNeighbor(grid[grid.length - count][j]);
			if (j <= num - 1)
				grid[i][j].addNeighbor(grid[i][grid.length - count]);
			if (i >= grid.length - num)
				grid[i][j].addNeighbor(grid[count - 1][j]);
			if (j >= grid[0].length - num)
				grid[i][j].addNeighbor(grid[i][count - 1]);
			count++;
			num--;
		}
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
