package backend.neighborsetters;

import backend.cells.Cell;

public abstract class NeighborSetter {

	/**
	 * The setNeighbors method sets the neighbor for a given grid, depending on
	 * the parameters for boundaryType and gridShape.
	 * 
	 * @param grid
	 *            Grid to assign neighborhoods for
	 * @param boundaryType
	 *            Type of boundary to be used: can be toroidal or finite. More
	 *            boundary types can be implemented
	 * @param gridShape
	 *            Shape of the patches in the grid: can be hexagonal,
	 *            rectangular, or triangular. More boundary types can be
	 *            implemented
	 */
	public void setNeighbors(Cell[][] grid, String boundaryType,
			String gridShape) {
		if (boundaryType == "FINITE") {
			if (gridShape == "RECTANGLE") {
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
			if (gridShape == "HEXAGON") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexBounded(grid, i, j);
					}
				}
			}

		}
		if (boundaryType == "TOROIDAL") {
			if (gridShape == "RECTANGLE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						recToroidal(grid, i, j);
					}
				}
			}
			if (gridShape == "TRIANGLE") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						triToroidal(grid, i, j);
					}
				}
			}
			if (gridShape == "HEXAGON") {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						hexToroidal(grid, i, j);
					}
				}
			}

		}

	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is rectangular and toroidal. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neigbhors for
	 * @param j
	 *            coordinates of specific patch to assign neigbhors for
	 */

	protected void recToroidal(Cell[][] grid, int i, int j) {
		recBounded(grid, i, j);
		addCardinalEdges(grid, i, j, 1);

	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is triangular and toroidal. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void triToroidal(Cell[][] grid, int i, int j) {
		triBounded(grid, i, j);
		addCardinalEdges(grid, i, j, 1);
		addDiagonalEdges(grid, i, j);

		// how to add appropriate edges

	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is hexagonal and toroidal. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void hexToroidal(Cell[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addHexEdges(grid, i, j);

	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is rectangular and finite. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void recBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, 1);
	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is triangular and finite. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void triBounded(Cell[][] grid, int i, int j) {
		/*
		 * the mod 2 determines which direction the triangle is facing. This is
		 * the code for 3 neighbors. if (i > 0 && (i + j) % 2 == 0)
		 * grid[i][j].addNeighbor(grid[i - 1][j]); if (j > 0)
		 * grid[i][j].addNeighbor(grid[i][j - 1]); if (i < grid.length - 1 && (i
		 * + j) % 2 == 1) grid[i][j].addNeighbor(grid[i + 1][j]); if (j <
		 * grid[0].length - 1) grid[i][j].addNeighbor(grid[i][j + 1]);
		 */
		addCardinalNeighbors(grid, i, j, 1);
		addDiagonalNeighbors(grid, i, j);
	}

	/**
	 * Method for determining neighborhoods for a patch given that the shape of
	 * the patch is hexagonal and finite. Can be overridden by subclasses
	 * depending on simulation.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void hexBounded(Cell[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, 1);
		addHexDiags(grid, i, j);

	}

	/*
	 * protected void addCardinalNeighbors(Cell[][] grid, int i, int j) { if (i
	 * > 0) grid[i][j].addNeighbor(grid[i - 1][j]); if (j > 0)
	 * grid[i][j].addNeighbor(grid[i][j - 1]); if (i < grid.length - 1)
	 * grid[i][j].addNeighbor(grid[i + 1][j]); if (j < grid[0].length - 1)
	 * grid[i][j].addNeighbor(grid[i][j + 1]); }
	 */
	/**
	 * Method for adding neighbors to the north, west, south, and east of the
	 * current patch.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 * @param num
	 *            how far the patch can be to still be considered a cardinal
	 *            neighbor. for example, if num is 2, then cardinal neighbors
	 *            constitute as all patches within 2 patches above, below,
	 *            right, and left of the current patch
	 */
	protected void addCardinalNeighbors(Cell[][] grid, int i, int j, int num) {
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

	/**
	 * Method for adding neighbors to the northeast, southwest, northwest, and
	 * southeast of the current patch.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

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

	/*
	 * protected void addDiagonalNeighbors(Cell[][] grid, int i, int j, int num)
	 * { int count = 1; while (num > 0) { if (i > num - 1 && j > num - 1)
	 * grid[i][j].addNeighbor(grid[i - count][j - count]); if (i > num - 1 && j
	 * < grid[0].length - num) grid[i][j].addNeighbor(grid[i - count][j +
	 * count]); if (i < grid.length - num && j > num - 1)
	 * grid[i][j].addNeighbor(grid[i + count][j - count]); if (i < grid.length -
	 * num && j < grid[0].length - num) grid[i][j].addNeighbor(grid[i + count][j
	 * + count]); count++; num--; } }
	 */

	/*
	 * protected void addCardinalEdges(Cell[][] grid, int i, int j) { if (i ==
	 * 0) grid[i][j].addNeighbor(grid[grid.length - 1][j]); if (j == 0)
	 * grid[i][j].addNeighbor(grid[i][grid.length - 1]); if (i == grid.length -
	 * 1) grid[i][j].addNeighbor(grid[0][j]); if (j == grid[0].length - 1)
	 * grid[i][j].addNeighbor(grid[i][0]); }
	 */

	/**
	 * Method for wrapping around the neighbors if its on the edge.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 * @param num
	 *            how far the patch can be to still be considered a cardinal
	 *            neighbor. for example, if num is 2, then cardinal neighbors
	 *            constitute as all patches within 2 patches above, below,
	 *            right, and left of the current patch
	 */

	protected void addCardinalEdges(Cell[][] grid, int i, int j, int num) {
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

	/**
	 * Method for adding the diagonal neighbors near the edges (wrap around).
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

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

	/**
	 * Method for adding neighbors to the northeast, southwest, northwest, and
	 * southeast of the current patch.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	/**
	 * Method for adding the two diagonals of the hexagon.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

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

	/**
	 * Method for adding the edges (wrap around) for a hexagon shape grid.
	 * 
	 * @param grid
	 *            Grid to find neighbors in
	 * @param i
	 *            coordinates of specific patch to assign neighbors for
	 * @param j
	 *            coordinates of specific patch to assign neighbors for
	 */

	protected void addHexEdges(Cell[][] grid, int i, int j) {
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
	}

}
