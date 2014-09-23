package backend.xml;
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

	public InitialGameParameters parseInitialCellsFromFile(File file) throws ParserConfigurationException, SAXException, IOException
	{	
		Document doc = createDocumentFromFile(file);
		InitialGameParameters igp = new InitialGameParameters();
		igp.simulationMode = getTagValueFromDoc(doc, "simulationMode");
		igp.gridXSize = Integer.parseInt(getTagValueFromDoc(doc, "gridXSize"));
		igp.gridYSize = Integer.parseInt(getTagValueFromDoc(doc, "gridYSize"));
		igp.thresholdValue = Double.parseDouble(getTagValueFromDoc(doc, "thresholdValue"));

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
		initialCell.myState = getTagValueFromElement("state", eElement);
		initialCell.myX = Integer.parseInt(getTagValueFromElement("x", eElement));
		initialCell.myY = Integer.parseInt(getTagValueFromElement("y", eElement));
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

	private String getTagValueFromDoc(Document doc, String tagName)
	{
		return doc.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue().replaceAll("\\s", "");
	}
	
	private String getTagValueFromElement(String sTag, Element eElement) {
		return eElement.getElementsByTagName(sTag).item(0).getChildNodes().item(0).getNodeValue().replaceAll("\\s", "");
	}
}