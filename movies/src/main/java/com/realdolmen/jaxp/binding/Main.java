package com.realdolmen.jaxp.binding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.realdolmen.movies.*;

public class Main {
	private static final String FILENAME = "movies4.xml";
	private static final String PATH = "src/main/resources/";
	private static final String SCHEMA = "src/main/resources/movies.xsd";
	private static final String SOURCEFILE = "target/classes/movies3.xml";

	public static void main(String[] args) throws Exception 
	{
		JAXBContext context = JAXBContext.newInstance("com.realdolmen.movies");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(SCHEMA));
		unmarshaller.setSchema(schema);
		
		Movies movies = (Movies) unmarshaller.unmarshal(new File(SOURCEFILE));
		
		movies.getMovie().forEach((movie) -> movie.setScore(0));
		
//		for(Movie movie : movies.getMovie())
//		{
//			movie.setScore(0);
//		}
//		
		Marshaller marshaller = context.createMarshaller();
		marshaller.setSchema(schema);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(movies, new FileOutputStream(PATH + FILENAME));
		System.out.println("Succesfulle saved files as " + FILENAME + " in " + PATH);

	}

}
