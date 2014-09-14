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

/*	public static void main(String argv[]) {

		try {
			File fXmlFile = new File("C:\\Users\\Abhishek B\\Documents\\workspace\\cellsociety_team16\\src\\cellsociety_team16\\xml\\initialGameParameters.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			System.out.println("SIMULATION MODE: " + doc.getElementsByTagName("simulationMode").item(0).getChildNodes().item(0).getNodeValue());
			System.out.println("GRID X SIZE: " + doc.getElementsByTagName("gridXSize").item(0).getChildNodes().item(0).getNodeValue());
			System.out.println("GRID Y SIZE: " + doc.getElementsByTagName("gridYSize").item(0).getChildNodes().item(0).getNodeValue());
			NodeList nodeLst = doc.getElementsByTagName("cell");
			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("state");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					System.out.println("State : "  + ((Node) fstNm.item(0)).getNodeValue());
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("x");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					System.out.println("X : " + ((Node) lstNm.item(0)).getNodeValue());
					NodeList l2stNmElmntLst = fstElmnt.getElementsByTagName("y");
					Element l2stNmElmnt = (Element) l2stNmElmntLst.item(0);
					NodeList l2stNm = l2stNmElmnt.getChildNodes();
					System.out.println("Y : " + ((Node) l2stNm.item(0)).getNodeValue());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */

/*	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		XMLParsing xmlp = new XMLParsing();
		InitialGameParameters igp = xmlp.parseInitialCellsFromFile();
		System.out.println("done");
	}*/

	public InitialGameParameters parseInitialCellsFromFile() throws ParserConfigurationException, SAXException, IOException
	{	
		Document doc = createDocumentFromFile();
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