package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.PREVIOUS_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_PARSE_FILE;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_INCORRECT_CONFIGURATION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_INCORRECT_FILE_PATH;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private Map<String, List<String>> urlMap;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = httpServletRequest.getServletContext();
        String url = httpServletRequest.getRequestURI();
        String userLogin = (String) session.getAttribute(USER_LOGIN);
        boolean pass = false;

        if (Objects.isNull(userLogin)) {
            userLogin = StringUtils.EMPTY;
        }

        Optional<Entry<String, List<String>>> key = urlMap.entrySet().stream()
            .filter(e -> {
            boolean res = false;
            Pattern pattern = Pattern.compile("^(/)(.+)((/\\*)|$)");
            Matcher matcher = pattern.matcher(e.getKey().trim());

            if (matcher.find()) {
                res = url.contains(matcher.group(2));
            }

            return res;
        })
            .findFirst();

        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        User user = userService.getByLogin(userLogin);

        if (!key.isPresent()) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if (Objects.nonNull(user)) {
            List<String> roles = key.get().getValue();
            pass = roles.contains(user.getRole().toString().toLowerCase());
        }

        if (!pass && StringUtils.EMPTY.equals(userLogin)) {
            session.setAttribute(PREVIOUS_URL, url);
            ((HttpServletResponse) response).sendRedirect("/login.do");
            return;
        }

        if (!pass) {
            httpServletRequest.getRequestDispatcher("jsp/403Error.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urlMap = xmlParse();
    }

    @Override
    public void destroy() {

    }

    private Map<String, List<String>> xmlParse() {
        Map<String, List<String>> urlMap = new HashMap<>();
        try {
            File file = new File("src/main/webapp/WEB-INF/security/Security.xml");
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
