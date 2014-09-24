package junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backend.cells.Cell;
import backend.cells.GoLCell;

public class GoLCellTest {
	@Test
	public void TestSomething() {
		Cell testCell = new GoLCell(1, 1, false, 1);
		testCell.setState("ALIVE");
		assertEquals(2, testCell.getState());
	}
}
