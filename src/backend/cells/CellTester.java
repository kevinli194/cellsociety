package backend.cells;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CellTester {

	@Test
	public void testSharkDeath() {
		EcoCell cell = new EcoCell(0, 0, false, 0, 3);
		cell.myTurnsStarved+=5;
		assertEquals(cell.checkSharkDeath(),true);
	
		
	}
}
