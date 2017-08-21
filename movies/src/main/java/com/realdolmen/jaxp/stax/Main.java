package com.realdolmen.jaxp.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;

public class Main {

	public static void main(String[] args) throws Exception 
	{
		FileInputStream input = new FileInputStream("target/classes/movies.xml");
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(input);
		
		Map<String, Integer> moviesPertype = new HashMap<>();
		
		while(reader.hasNext())
		{
			XMLEvent event =  reader.nextEvent();
			int movieTeller = 0;
			
			if (event.isStartElement())
			{
				String element = event.asStartElement().getName().getLocalPart();
				if (element.equals("movie"))
				{
					movieTeller++;
				}
				
				else if (element.equals("type"))
				{
					String key =reader.nextEvent().asCharacters().getData();
					if (moviesPertype.containsKey(key))
					{
						moviesPertype.put(key, moviesPertype.get(key).intValue() + 1);
					}
					else 
					{
						moviesPertype.put(key, 1);
					}
				}
			}
		}
		System.out.println(moviesPertype);
		
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter("rc/main/resources/movies3.xml"));
		
		//TODO write...
	}
}