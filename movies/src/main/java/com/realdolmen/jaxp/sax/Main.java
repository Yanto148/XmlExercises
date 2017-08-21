package com.realdolmen.jaxp.sax;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

public class Main {
	public static void main(String[] args)
	{
		try
		{
			XMLReader parser;
			SAXParserFactory sf = SAXParserFactory.newInstance();
			sf.setNamespaceAware(false);
			sf.setValidating(false);
			parser = sf.newSAXParser().getXMLReader();
			parser.setContentHandler(new MovieHandler());
			parser.parse("src/main/resources/movies.xml");
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
