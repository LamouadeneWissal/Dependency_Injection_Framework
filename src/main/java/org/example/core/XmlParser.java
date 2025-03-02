package org.example.core;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XmlParser {
    public static void parse(String filePath) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(filePath);
            document.getDocumentElement().normalize();
            NodeList beans = document.getElementsByTagName("bean");

            for (int i = 0; i < beans.getLength(); i++) {
                Element bean = (Element) beans.item(i);
                String className = bean.getAttribute("class");
                System.out.println("Loading bean: " + className);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
