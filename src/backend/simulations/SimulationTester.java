package backend.simulations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.patches.Patch;

public class SimulationTester {
	@Test
	public void setNeighborTest() {
		// Creating and filling a 2x2 grid with empty patches.
		Patch[][] testGrid = new Patch[2][2];
		GoLSimulation testClass = new GoLSimulation();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				testClass.makeNewPatch(testGrid, i, j, 0);
			}
		}
		// Running setNeighbors on the class
		testClass.setNeighbors(testGrid, "FINITE", "SQUARE");
		// Getting the coordinates of Had to change the myNeighbors and
		// myCoordinates variables in patches to be public. Would change back
		// after testing.

		// First test is to determine whether the first patch has the correct
		// number of neighbors.
		assertEquals("First patch must have 3 neighbors", 3,
				testGrid[0][0].myNeighbors.size());
		// Second test is to test whether the coordinates are correct.
		for (int i = 0; i < testGrid[0][0].myNeighbors.size(); i++) {
			int x = testGrid[0][0].myNeighbors.get(i).myCoordinates[0];
			int y = testGrid[0][0].myNeighbors.get(i).myCoordinates[1];
			if (i == 0) {
				assertEquals("X coordinates of first neighbor must be 1", 1, x);
				assertEquals("Y coordinates of first neighbor must be 0", 0, y);
			}
			if (i == 1) {
				assertEquals("X coordinates of first neighbor must be 0", 0, x);
				assertEquals("Y coordinates of first neighbor must be 1", 1, y);
			}
			if (i == 2) {
				assertEquals("X coordinates of first neighbor must be 1", 1, x);
				assertEquals("Y coordinates of first neighbor must be 1", 1, y);
			}
		}

	}
}
