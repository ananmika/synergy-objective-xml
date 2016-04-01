package main.java.StAXParser;

import main.java.SAX.Book;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class ExampleStaxParser {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        Book book = new Book();

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yy-MM-dd");

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("main/resources/catalog.xml"));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("book")) {
                            Attribute id = startElement.getAttributeByName(new QName("id"));
                            Attribute lang = startElement.getAttributeByName(new QName("lang"));
                            book = new Book(id.getValue(), lang.getValue());
                        } else if (qName.equalsIgnoreCase("isbn")) {
                            event = eventReader.nextEvent();
                            book.setIsbn(event.asCharacters().getData());
                        } else if (qName.equalsIgnoreCase("regDate")) {
                            event = eventReader.nextEvent();
                            try {
                                book.setRegDate(simpleDateFormat.parse(event.asCharacters().getData()));
                            } catch (ParseException e) {
                                System.out.println("date parsing exception");
                            }
                        } else if (qName.equalsIgnoreCase("title")) {
                            event = eventReader.nextEvent();
                            book.setTitle(event.asCharacters().getData());
                        }
                        else if (qName.equalsIgnoreCase("publisher")) {
                            event = eventReader.nextEvent();
                            Attribute country = startElement.getAttributeByName(new QName("country"));
                            book.setPublisher(event.asCharacters().getData() + "/" + country.getValue());
                        } else if (qName.equalsIgnoreCase("price")) {
                            event = eventReader.nextEvent();
                            book.setPrice(Integer.parseInt(event.asCharacters().getData()));
                        }
                        else if (qName.equalsIgnoreCase("author")) {
                            event = eventReader.nextEvent();
                            book.getAuthors().add(event.asCharacters().getData());
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("book")){
                            books.add(book);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        /////// Do something with this list :)))
    }
}
