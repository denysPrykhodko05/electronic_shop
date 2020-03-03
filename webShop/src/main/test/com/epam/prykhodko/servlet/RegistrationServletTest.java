package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEEPER;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.REG_FORM;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_UTILS;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import com.epam.prykhodko.captchakeepers.captchakeeperimpl.SessionKeeper;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.util.UserUtils;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistrationServletTest {

    @Mock
    RegFormBean regFormBean;
    private RegistrationServlet registrationServlet = new RegistrationServlet();
    @Mock
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RegFormBean formBean;
    @Mock
    private Validator validator;
    @Mock
    private UserService userService;
    @Mock
    private User user;
    @Mock
    private UserUtils userUtils;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetShouldRedirectToTheRegistrationPage() throws ServletException, IOException {
        when(httpServletRequest.getSession()).thenReturn(session);
        when(httpServletRequest.getRequestDispatcher("jsp/registration.jsp")).thenReturn(dispatcher);
        registrationServlet.doGet(httpServletRequest, httpServletResponse);
    }

    private void init(Map<String, String> captchaKeys, Map<String, CaptchaKeeper> keepers) {
        User user1 = new User("Ivan", "Ivanov", "ivan@gmail.com", "login", "Aadaf@12");
        User user2 = new User("Peter", "Petrov", "peter@gmail.com", "peterPeter", "Asaba_33");
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        when(servletContext.getAttribute(VALIDATOR)).thenReturn(validator);
        when(servletContext.getAttribute(CAPTCHA_KEEPER)).thenReturn(new SessionKeeper());
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
        when(servletContext.getAttribute(REG_FORM)).thenReturn(formBean);
        when(servletContext.getAttribute(USER_UTILS)).thenReturn(userUtils);
        when(formBean.getName()).thenReturn("Peter");
        when(formBean.getSurname()).thenReturn("Petrov");
        when(formBean.getLogin()).thenReturn("peter1");
        when(formBean.getConfirmPassword()).thenReturn("Sadvds@1");
        when(formBean.getPassword()).thenReturn("Sadvds@1");
        when(formBean.getPolicy()).thenReturn("on");
        when(formBean.getMails()).thenReturn("on");
        when(formBean.getCaptcha()).thenReturn("1234");
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute("timer")).thenReturn(true);
        when(httpServletRequest.getSession().getAttribute("captchaKey")).thenReturn("1");
        when(servletContext.getAttribute("captchaKeys")).thenReturn(captchaKeys);
        when(servletContext.getAttribute("keepers")).thenReturn(keepers);
        when(servletContext.getInitParameter("captcha")).thenReturn("session");
        when(servletContext.getAttribute("users")).thenReturn(users);
    }

    @Test
    public void doPostRegistrationFormShouldBeValid() throws ServletException, IOException {
        Map<String, String> captchaKeys = new HashMap<>();
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        captchaKeys.put("1", "1234");
        keepers.put("session", new SessionKeeper());
        init(captchaKeys, keepers);
        when(userService.createUser(formBean)).thenReturn(user);
        when(userService.isContains(user)).thenReturn(false);
        when(httpServletRequest.getParameter(EMAIL)).thenReturn("peter1@asd.com");
        registrationServlet.doPost(httpServletRequest, httpServletResponse);
    }

    @Test
    public void doPostRegistrationFormShouldBeInvalid() throws ServletException, IOException {
        Map<String, String> captchaKeys = new HashMap<>();
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        captchaKeys.put("1", "234");
        keepers.put("session", new SessionKeeper());
        init(captchaKeys, keepers);
        when(httpServletRequest.getParameter(EMAIL)).thenReturn("peter1@asd.com");
        when(httpServletRequest.getRequestDispatcher("jsp/registration.jsp")).thenReturn(dispatcher);
        registrationServlet.doPost(httpServletRequest, httpServletResponse);
    }

    @Test
    public void doPostUserShouldBeExists() throws ServletException, IOException {
        Map<String, String> captchaKeys = new HashMap<>();
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        captchaKeys.put("1", "1234");
        keepers.put("session", new SessionKeeper());
        init(captchaKeys, keepers);
        when(userService.createUser(formBean)).thenReturn(user);
        when(userService.isContains(user)).thenReturn(true);
        when(httpServletRequest.getParameter(EMAIL)).thenReturn("peter@gmail.com");
        when(httpServletRequest.getRequestDispatcher("jsp/registration.jsp")).thenReturn(dispatcher);
        registrationServlet.doPost(httpServletRequest, httpServletResponse);
    }

}