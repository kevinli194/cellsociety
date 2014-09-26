package backend.cells;

import java.util.List;

import backend.patches.Patch;
import backend.patches.SugarPatch;

/**
 * 
 * @author CS308 Team16
 * SugarAgentCell is the Cell for the Sugarscape simulation.
 * SugarAgentCells have 3 primary variables: sugar, sugarMetabolism, and vision.
 * SugarAgentCells consume the sugar on the various SugarPatches.
 */
public class SugarAgentCell extends Cell {

	/**
	 * SugarAgentCell states
	 */
	private static final int DEAD = 0;
	private static final int ALIVE = 1;
	
	/**
	 * The amount of sugar the cell has.
	 */
	private int mySugarAmount;
	
	/**
	 * The amount of sugar that the cell digests each turn.
	 */
	private int mySugarMetabolism;

	private boolean myUpdated;

	/**
	 * Constructor for SugarAgentCell
	 * sugarMetabolism and initial sugar are randomly generated.
	 * @param patch
	 */
	public SugarAgentCell(SugarPatch patch) {
		myPatch = patch;
		mySugarMetabolism = (int) Math.floor(Math.random() * 5) + 1;
		mySugarAmount = (int) Math.floor(Math.random() * 4) + 1;
		myUpdated = false;
	}

	/**
	 * The SugarAgentCell's update involves:
	 * 1) finding the patch with the maximum amount of sugar in its cell
	 * 2) moving to this patch and consuming its sugar
	 * 3) digesting its own sugar (and dying if too low sugar)
	 */
	@Override
	public void update() {
		if (myUpdated == false && myState == ALIVE) {
			myUpdated = true;
			List<Patch> myPatchNeighbors = myPatch.getPatchNeighbors();
			int maximumSugarAmongNeighbors = Integer.MIN_VALUE;
			SugarPatch maximumNeighbor = null;

			for (Patch neighbor : myPatchNeighbors) {
				int neighborSugar = ((SugarPatch) neighbor).getState();
				if (neighborSugar > maximumSugarAmongNeighbors) {
					maximumSugarAmongNeighbors = neighborSugar;
					maximumNeighbor = (SugarPatch) neighbor;
				}
			}
			
			((SugarPatch) myPatch).swapCells(maximumNeighbor);
			mySugarAmount += myPatch.getState();
			myPatch.setState(0);
			mySugarAmount -= mySugarMetabolism;
			
			if (mySugarAmount <= 0) {
				myState = DEAD;
			}
		}
	}
	
	public void reset() {
		myUpdated = false;
	}
}