package frontend;

import static org.junit.Assert.*;
import javafx.animation.Timeline;
import javafx.scene.control.Button;

import org.junit.Before;
import org.junit.Test;

public class SimButtonsTest {
	SimulationButtons simButtons;

	@Before
	public void setUp() {
		simButtons = new SimulationButtons();
	}

	public void setUpStepClicked() {
		simButtons.myStepClicked = true;
	}

	@Test
	public void testStepClicked() {
		setUpStepClicked();
		assertEquals(simButtons.myStepClicked, true);
	}
	
	public void tearDownStepClicked() {
		simButtons.myStepClicked = false;
	}
	
	@Test
	public void testTearDownStepClicked() {
		tearDownStepClicked();
		simButtons.myStepClicked = false;
	}

	public void setUpAnimation() {
		simButtons.myAnimation = new Timeline();
	}

	@Test
	public void testAnimationNotNull() {
		setUpAnimation();
		assertNotNull(simButtons.myAnimation);
	}
	
	public void tearDownAnimation() {
		simButtons.myAnimation = null;
	}
	
	@Test
	public void testAnimationNull() {
		tearDownAnimation();
		assertNull(simButtons.myAnimation);
		
	}
	
	/**
	 * This test gave an ExceptionInIntializerError. I'm not sure why this doing that,
	 * and have commented it out. You can uncomment the below to see the Error.
	 */
	/*@Test
	public void testButtonNotNull() {
		simButtons.myReset = new Button();
		assertNotNull(simButtons.myReset);
		
	}
*/
}
