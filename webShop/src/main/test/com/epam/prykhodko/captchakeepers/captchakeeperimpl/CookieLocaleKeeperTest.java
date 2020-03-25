package com.epam.prykhodko.captchakeepers.captchakeeperimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CookieLocaleKeeperTest {

    private CookieKeeper cookieKeeper = new CookieKeeper();
    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private ServletContext servletContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveShouldAddDataToCookie() {
        Map<Long, String> keyMap = new HashMap<>();
        long key = System.currentTimeMillis();
        String value = "1234";
        keyMap.put(key, value);
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("captchaKeys")).thenReturn(keyMap);
        cookieKeeper.save(httpServletRequest, httpServletResponse, key, value);
    }

    @Test
    public void getShouldReturnDataFromCookie() {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("captchaKey","1234")});
        assertEquals(cookieKeeper.get(httpServletRequest),Long.valueOf(1234));
    }
}