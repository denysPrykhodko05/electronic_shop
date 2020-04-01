package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_PARSE_FILE;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_INCORRECT_CONFIGURATION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_INCORRECT_FILE_PATH;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                    int roleElement = 0;
                    String url = element.getElementsByTagName("url-pattern").item(INTEGER_ZERO).getTextContent();
                    NodeList roles = element.getElementsByTagName("role");
                    Node role = roles.item(roleElement);
                    List<String> roleList = new ArrayList<>();

                    while (Objects.nonNull(role)) {
                        roleList.add(role.getTextContent());
                        roleElement++;
                        role = roles.item(roleElement);
                    }

                    urlMap.put(url, roleList);
                }
            }
        } catch (ParserConfigurationException ex) {
            LOGGER.error(ERR_INCORRECT_CONFIGURATION);
        } catch (IOException ex) {
            LOGGER.error(ERR_INCORRECT_FILE_PATH);
        } catch (SAXException ex) {
            LOGGER.error(ERR_CANNOT_PARSE_FILE);
        }
        return urlMap;
    }
}
