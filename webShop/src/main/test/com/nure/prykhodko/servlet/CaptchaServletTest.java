package com.nure.prykhodko.servlet;

import static java.lang.System.currentTimeMillis;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nure.prykhodko.captchakeepers.captchakeeperimpl.CookieKeeper;
import com.nure.prykhodko.captchakeepers.captchakeeperimpl.HiddenFieldKeeper;
import com.nure.prykhodko.captchakeepers.captchakeeperimpl.SessionKeeper;
import com.nure.prykhodko.constants.ApplicationConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CaptchaServletTest {

    @Mock
    private ServletOutputStream outputStream;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession httpSession;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetForSessionKeeper() throws IOException {
        HashMap map = new HashMap();
        map.put("session", new SessionKeeper());
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("captcha")).thenReturn("session");
        when(servletContext.getAttribute("keepers")).thenReturn(map);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("captchaValue")).thenReturn("123456");
        String uuid = String.valueOf(currentTimeMillis());
        Map<String, String> keyMap = new HashMap<>();
        when(httpSession.getAttribute("captchaKey")).thenReturn(uuid);
        when(servletContext.getAttribute("captchaKeys")).thenReturn(keyMap);
        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);
        when(servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEEPER)).thenReturn(new SessionKeeper());
        CaptchaServlet captchaServlet = new CaptchaServlet();
        captchaServlet.doGet(httpServletRequest, httpServletResponse);
    }
    @Test
    public void doGetForCookieKeeper() throws IOException {
        HashMap map = new HashMap();
        map.put("cookie", new CookieKeeper());
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("captcha")).thenReturn("cookie");
        when(servletContext.getAttribute("keepers")).thenReturn(map);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("captchaValue")).thenReturn("123456");
        String uuid = String.valueOf(currentTimeMillis());
        Map<String, String> keyMap = new HashMap<>();
        when(httpSession.getAttribute("captchaKey")).thenReturn(uuid);
        when(servletContext.getAttribute("captchaKeys")).thenReturn(keyMap);
        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);
        when(servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEEPER)).thenReturn(new SessionKeeper());
        CaptchaServlet captchaServlet = new CaptchaServlet();
        captchaServlet.doGet(httpServletRequest, httpServletResponse);
    }

    @Test
    public void doGetForHiddenFieldKeeper() throws IOException {
        HashMap map = new HashMap();
        map.put("hiddenField", new HiddenFieldKeeper());
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("captcha")).thenReturn("hiddenField");
        when(servletContext.getAttribute("keepers")).thenReturn(map);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("captchaValue")).thenReturn("123456");
        String uuid = String.valueOf(currentTimeMillis());
        Map<String, String> keyMap = new HashMap<>();
        when(httpSession.getAttribute("captchaKey")).thenReturn(uuid);
        when(servletContext.getAttribute("captchaKeys")).thenReturn(keyMap);
        when(httpServletResponse.getOutputStream()).thenReturn(outputStream);
        when(servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEEPER)).thenReturn(new SessionKeeper());
        CaptchaServlet captchaServlet = new CaptchaServlet();
        captchaServlet.doGet(httpServletRequest, httpServletResponse);
    }
}