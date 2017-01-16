package com.example.hubertfabisiak.checkbox_treetable;

/**
 * Created by Damian on 15.01.2017.
 */

import android.content.Context;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import java.io.IOException;
import java.io.InputStream;


public class XMLReader {

    public XMLReader(Context context) {
        readXML(context);
    }

    public void readXML(Context context) {

        InputStream is = null;
        try {
            is = context.getAssets().open("plik.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc=null;

        try {
            doc = dBuilder.parse(is);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("column");

        for (int i = 0; i < nList.getLength(); i++) {
            Node n = nList.item(i);
            Settings.addVariableToDisplay(n.getTextContent().trim());
        }

    }
}

