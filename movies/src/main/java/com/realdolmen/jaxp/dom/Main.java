package com.realdolmen.jaxp.dom;

import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main 
{
    public static void main( String[] args ) throws Exception
    {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document document = builder.parse("target/classes/movies.xml");
    	
    	NodeList movies = document.getElementsByTagName("movie");
    	Node node = movies.item(0);
    	
    	Random randomGenerator = new Random();
    	Integer random;
    	for (int i = 0; i < movies.getLength(); i++)
    	{
    		random = randomGenerator.nextInt(5) + 1;
    		String randomStr = random.toString();
    		
    		Element score = document.createElement("score");
    		score.setTextContent(randomStr);
    		
    		Element description = document.createElement("description");
    		description.setTextContent("nsectetur adipiscing elit. Donec et odio suscipit ex fermentum consequat. Ut et felis vel purus imperdiet bibendum non vitae lorem. Quisque nisi lorem, dapibus id risus non, pretium venenatis massa. Aenean at hendrerit metus. Sed semper euismod arcu eget mattis. Fusce a ligula ");
    		
    		movies.item(i).appendChild(score);
    		movies.item(i).appendChild(description);
    		
    	}
    	System.out.println(movies.getLength());
    	
    	
    	TransformerFactory tFactory = TransformerFactory.newInstance();
    	Transformer transformer = tFactory.newTransformer();
    	DOMSource domSource = new DOMSource(document);
    	StreamResult streamResult = new StreamResult("src/main/resources/movies2.xml");
    	transformer.transform(domSource, streamResult);
    }
}