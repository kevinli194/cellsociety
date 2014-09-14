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

/*	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		XMLParsing xmlp = new XMLParsing();
		InitialGameParameters igp = xmlp.parseInitialCellsFromFile();
		System.out.println("done");
	}*/

	public InitialGameParameters parseInitialCellsFromFile(File file) throws ParserConfigurationException, SAXException, IOException
	{	
		Document doc = createDocumentFromFile(file);
		InitialGameParameters igp = new InitialGameParameters();
		igp.simulationMode = valueFromTagElement(doc, "simulationMode");
		igp.gridXSize = Integer.parseInt(valueFromTagElement(doc, "gridXSize").replaceAll("\\s", ""));
		igp.gridYSize = Integer.parseInt(valueFromTagElement(doc, "gridYSize").replaceAll("\\s", ""));

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
	
	private String valueFromTagElement(Document doc, String tagName)
	{
		return doc.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
	}

	private InitialCell createNewCellFromFileData(Element eElement)
	{
		InitialCell initialCell = new InitialCell();
		initialCell.myState = getTagValue("state", eElement);
		initialCell.myX = Integer.parseInt(getTagValue("x", eElement).replaceAll("\\s", ""));
		initialCell.myY = Integer.parseInt(getTagValue("y", eElement).replaceAll("\\s", ""));
		return initialCell;
	}

	private Document createDocumentFromFile(File file) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = file;
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