package com.realdolmen.jaxp.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MovieHandler extends DefaultHandler {
	private String title = "";
	private String type = "";
	private String format = "";
	
	boolean foundMovie = false;
	boolean foundTitle = false;
	boolean foundType = false;
	boolean foundFormat = false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		if (qName.equals("movie"))
			foundMovie = true;
		else if (foundMovie && qName.equals("title"))
			foundTitle = true;
		else if (foundMovie && qName.equals("type"))
			foundType = true;
		else if (foundMovie && qName.equals("format"))
			foundFormat = true;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException 
	{
		if (foundTitle)
			title += new String(ch, start, length).trim();
		else if (foundType)
			type += new String(ch, start, length).trim();
		else if (foundFormat)
			format += new String(ch, start, length).trim();
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		if (qName.equals("title"))
			foundTitle = false;
		else if (qName.equals("type"))
			foundType = false;
		else if (qName.equals("format"))
			foundFormat = false;
		else if (qName.equals("movie"))
		{
			foundMovie = false;
			System.out.println("Title: " + title + " - Type: " + type + " - Format: " + format);
			title = "";
			type = "";
			format = "";
		}
	}
}
