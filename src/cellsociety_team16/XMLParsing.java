package cellsociety_team16;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

/*
 * Based on code from http://javafxportal.blogspot.com/2012/03/how-to-read-xml-file-in-java-dom-parser.html
 */

public class XMLParsing {
	
	public InitialGameParameters parseInitialCellsFromFile() throws ParserConfigurationException, SAXException, IOException
	{	
		Document doc = createDocumentFromFile();
		InitialGameParameters igp = new InitialGameParameters();
		igp.simulationMode = doc.getElementById("simulationMode").getNodeValue();
		igp.gridXSize = Integer.parseInt(doc.getElementById("gridXSize").getNodeValue());
		igp.gridYSize = Integer.parseInt(doc.getElementById("gridYSize").getNodeValue());
		
		NodeList nList = doc.getElementsByTagName("cell");
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element eElement = (Element) nNode;
				igp.initialCells.add(createNewCellFromFileData(eElement));
			}
		}
		return igp;
	}
	
	private InitialCell createNewCellFromFileData(Element eElement)
	{
		InitialCell initialCell = new InitialCell();
		initialCell.myState = getTagValue("state", eElement);
		initialCell.myX = Integer.parseInt(getTagValue("x", eElement));
		initialCell.myY = Integer.parseInt(getTagValue("y", eElement));
		return initialCell;
	}
	
	private Document createDocumentFromFile() throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("C:\\Users\\Abhishek B\\Documents\\workspace\\cellsociety_team16\\src\\cellsociety_team16\\xml\\initialGameParameters.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
}