package main.java.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hasmik.Ghazaryan on 3/31/2016.
 */
public class SaxParserDemo extends DefaultHandler {

    private List<Book> books;
    private String bookXmlFileName;
    private String tmpValue;
    private Book book;

    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yy-MM-dd");

    public SaxParserDemo(String bookXmlFileName) {
        this.bookXmlFileName = bookXmlFileName;
        books = new ArrayList<Book>();
        parseDocument();
        print();
    }

    private void parseDocument() {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(bookXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    private void print() {
        System.out.println("Output: ");
        for (Book book : books) {
            System.out.println(book.toString() );
            //Do something with this list
        }
    }
    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book
        if (elementName.equalsIgnoreCase("book")) {
            book = new Book();
            book.setId(attributes.getValue("id"));
            book.setLang(attributes.getValue("lang"));
        }
        // if current element is publisher
        if (elementName.equalsIgnoreCase("publisher")) {
            book.setPublisher(attributes.getValue("country"));
        }
    }
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        if (element.equals("book")) {
            books.add(book);
        }
        if (element.equalsIgnoreCase("isbn")) {
            book.setIsbn(tmpValue);
        }
        if (element.equalsIgnoreCase("title")) {
            book.setTitle(tmpValue);
        }
        if(element.equalsIgnoreCase("author")){
            book.getAuthors().add(tmpValue);
        }
        if(element.equalsIgnoreCase("price")){
            book.setPrice(Integer.parseInt(tmpValue));
        }
        if(element.equalsIgnoreCase("regDate")){
            try {
                book.setRegDate(simpleDateFormat.parse(tmpValue));
            } catch (ParseException e) {
                System.out.println("date parsing error");
            }
        }
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }
    public static void main(String[] args) {
        new SaxParserDemo("src/main/resources/catalog.xml");
    }

}
