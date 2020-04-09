package com.nure.prykhodko.util;

import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    private static final Logger LOGGER = Logger.getLogger(XMLParser.class);

    public static Map<String, List<String>> securityXMLParse(String path) {
        Map<String, List<String>> urlMap = new HashMap<>();
        try {
            File file = new File(path);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("constraint");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    urlMap.put(getUrlPattern(element), getRoles(element));
                }
            }
        } catch (ParserConfigurationException ex) {
            LOGGER.error(LoggerMessagesConstants.ERR_INCORRECT_CONFIGURATION);
        } catch (IOException ex) {
            LOGGER.error(LoggerMessagesConstants.ERR_INCORRECT_FILE_PATH);
        } catch (SAXException ex) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_PARSE_FILE);
        }
        return urlMap;
    }

    private static String getUrlPattern(Element element) {
        return element
            .getElementsByTagName("url-pattern")
            .item(0)
            .getTextContent();
    }

    private static List<String> getRoles(Element element) {
        List<String> roleList = new ArrayList<>();

        NodeList roleNodeList = element.getElementsByTagName("role");

        for (int i = 0; i < roleNodeList.getLength(); i++) {
            Node node = roleNodeList.item(i);
            Element roleElement = (Element) node;
            String role = roleElement.getTextContent();
            roleList.add(role);
        }
        return roleList;
    }
}
