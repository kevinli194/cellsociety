package unittesting;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import backend.xml.InitialGameParameters;
import backend.xml.XMLParsing;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestXMLParsing {
	
	@Test
	public void testParticularParameterExtraction() throws ParserConfigurationException, SAXException, IOException
	{
		XMLParsing xmlParser = new XMLParsing();
		File fXmlFile = new File("src\\testfiles\\UnitTest1.xml");
		InitialGameParameters igp = xmlParser.getInitialGameParametersFromXMLFile(fXmlFile);
		assertEquals(igp.edgeType, "TOROIDAL");
	}
	
	@Test
	public void testNumberOfCells() throws ParserConfigurationException, SAXException, IOException
	{
		XMLParsing xmlParser = new XMLParsing();
		File fXmlFile = new File("src\\testfiles\\UnitTest1.xml");
		InitialGameParameters igp = xmlParser.getInitialGameParametersFromXMLFile(fXmlFile);
		assertEquals(4, igp.initialCells.size());
	}
	
	@Test
	public void testBadInputs() throws ParserConfigurationException, SAXException, IOException
	{
		XMLParsing xmlParser = new XMLParsing();
		File fXmlFile = new File("src\\testfiles\\UnitTest1.xml");
		InitialGameParameters igp = xmlParser.getInitialGameParametersFromXMLFile(fXmlFile);
		assertEquals(null, igp.unitShape);
	}
}
