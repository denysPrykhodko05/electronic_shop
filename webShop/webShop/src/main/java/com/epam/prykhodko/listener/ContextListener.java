package com.epam.prykhodko.listener;

import com.epam.prykhodko.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        User user1 = new User("Ivan", "Ivanov", "ivan@gmail.com", "login", "Aadaf@12");
        User user2 = new User("Peter", "Petrov", "peter@gmail.com", "peterPeter", "Asaba_33");
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        servletContext.setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
