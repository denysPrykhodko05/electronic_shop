package com.epam.prykhodko.mananger;

import static com.epam.prykhodko.constants.ApplicationConstants.PREVIOUS_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;

import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

public class AccessManager {

    private static final Pattern PATTERN = Pattern.compile("^(/)(.+)((/\\*)|$)");
    private HttpSession session;
    private Map<String, List<String>> urlMap;
    private ServletContext servletContext;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private String url;

    public AccessManager(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Map<String, List<String>> urlMap) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.urlMap = urlMap;
        session = httpServletRequest.getSession();
        servletContext = httpServletRequest.getServletContext();
        url = httpServletRequest.getRequestURI();
    }

    public boolean checkAccess() throws IOException, ServletException {
        boolean pass = false;

        if (url.matches("^(.+)(\\..+)$")){
            return true;
        }

        String userLogin = (String) session.getAttribute(USER_LOGIN);

        if (Objects.isNull(userLogin)) {
            userLogin = StringUtils.EMPTY;
        }

        Optional<Entry<String, List<String>>> key = urlMap.entrySet().stream()
            .filter(e -> {
                boolean res = false;
                Matcher matcher = PATTERN.matcher(e.getKey().trim());

                if (matcher.find()) {
                    res = url.contains(matcher.group(2));
                }

                return res;
            })
            .findFirst();

        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        User user = userService.getByLogin(userLogin);

        if (!key.isPresent()) {
            return true;
        }

        if (Objects.nonNull(user)) {
            List<String> roles = key.get().getValue();
            pass = roles.contains(user.getRole().toString().toLowerCase());
        }

        if (!pass && StringUtils.EMPTY.equals(userLogin)) {
            session.setAttribute(PREVIOUS_URL, url);
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        if (!pass) {
            httpServletRequest.getRequestDispatcher("jsp/403Error.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        return true;
    }
}
