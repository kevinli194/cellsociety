package backend.neighborsetters;

import java.util.Random;

import backend.patches.Patch;

public class SugarNeighborSetter extends NeighborSetter {
	private int num;

	public SugarNeighborSetter() {
		Random rn = new Random();
		num = rn.nextInt(5) + 0;
		System.out.println(num);
	}

	@Override
	public void recAndTriToroidal(Patch[][] grid, int i, int j) {
		hexBounded(grid, i, j);
		addCardinalEdges(grid, i, j, num);

	}

	@Override
	public void recAndTriBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, num);
	}

	@Override
	public void hexToroidal(Patch[][] grid, int i, int j) {
		hexBounded (grid, i, j);
		addCardinalEdges(grid, i, j, num);
	}
	
	
	@Override
	public void hexBounded(Patch[][] grid, int i, int j) {
		addCardinalNeighbors(grid, i, j, num);

	}

}