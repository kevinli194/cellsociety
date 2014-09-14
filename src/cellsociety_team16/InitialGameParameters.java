package cellsociety_team16;
import java.util.ArrayList;

public class InitialGameParameters
{
	public String simulationMode;
	public int gridXSize;
	public int gridYSize;
	public ArrayList<InitialCell> initialCells;
	
	public InitialGameParameters()
	{
		initialCells = new ArrayList<InitialCell>();
	}
}
