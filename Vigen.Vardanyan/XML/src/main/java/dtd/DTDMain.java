package main.java.dtd;

import com.sun.org.apache.xerces.internal.impl.dtd.DTDGrammar;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader;
import com.sun.org.apache.xerces.internal.util.SAXInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vigen.Vardanyan on 4/1/2016.
 */
public class DTDMain {

    public static void main(String[] args) throws Exception {
        StringWriter sw = new StringWriter();
        sw.write("<!DOCTYPE html [");
        sw.write("  <!ELEMENT html (head, body)>");
        sw.write("  <!ELEMENT head (title)> <!ELEMENT title (#PCDATA)>");
        sw.write("  <!ELEMENT body (p+)> <!ELEMENT p (#PCDATA)>");
        sw.write("]>");

        InputStream dtdStream = new ByteArrayInputStream(sw.toString().getBytes());
        Scanner scanner = new Scanner(dtdStream);
        String dtdText = scanner.useDelimiter("\\z").next();
        scanner.close();

        Pattern dtdPattern = Pattern.compile("^\\s*<!DOCTYPE\\s+(\\S+)\\s*\\[(.*)\\]>\\s*$", Pattern.DOTALL);
        Matcher m = dtdPattern.matcher(dtdText);
        if (m.matches()) {
            String docType = m.group(1);
            InputSource is = new InputSource(new StringReader(m.group(2)));
            XMLInputSource source = new SAXInputSource(is);
            XMLDTDLoader d = new XMLDTDLoader();
            DTDGrammar g = (DTDGrammar) d.loadGrammar(source);
            g.printElements();
        }
    }
}
