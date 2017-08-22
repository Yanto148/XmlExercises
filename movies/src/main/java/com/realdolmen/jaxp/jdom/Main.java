package com.realdolmen.jaxp.jdom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Main {

	public static void main(String[] args) throws Exception 
	{
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc = saxBuilder.build("target/classes/movies2.xml");
				
		List<Element> elements = doc.getRootElement().getChildren();
		elements.forEach(Main::addComments);
		
		Format format = Format.getPrettyFormat();
		XMLOutputter outputter = new XMLOutputter(format);
		FileOutputStream output = new FileOutputStream("src/main/resources/movies3.xml");
		outputter.output(doc, output);
		System.out.println("File saved");
	}
	
	public static void addComments(Element element)
	{
		Element comment = new Element("comment");
		Element user = new Element("user");
		user.setText("Yanto");
		Element content = new Element("content");
		content.setText("Mega awesome movie, must watch!!!");
		Element date = new Element("date");
		date.setText("1989-05-21");
		
		comment.addContent(user);
		comment.addContent(content);
		comment.addContent(date);
		element.addContent(comment);
	}

}
