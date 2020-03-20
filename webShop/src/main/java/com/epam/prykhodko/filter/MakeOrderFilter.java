package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.MAKE_ORDER_USER_CHECK_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.SUCCESS;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;

import com.epam.prykhodko.service.UserService;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(MAKE_ORDER_USER_CHECK_URL)
public class MakeOrderFilter extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext servletContext = request.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(USER_LOGIN);
        JsonObject jsonObject = new JsonObject();
        PrintWriter writer = response.getWriter();

        if (Objects.nonNull(login) && Objects.nonNull(userService.getByLogin(login))) {
            jsonObject.addProperty(SUCCESS, true);
            writer.write(jsonObject.toString());
            return;
        }

        jsonObject.addProperty(SUCCESS, false);
        writer.write(jsonObject.toString());
    }
}
