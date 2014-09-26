package backend.xml;
import java.util.ArrayList;

public class InitialGameParameters
{
	public String simulationMode;
	public String unitShape;
	public String edgeType;
	public int gridXSize;
	public int gridYSize;
	public double thresholdValue;
	public ArrayList<InitialCell> initialCells;
	
	public InitialGameParameters()
	{
		initialCells = new ArrayList<InitialCell>();
	}
}
