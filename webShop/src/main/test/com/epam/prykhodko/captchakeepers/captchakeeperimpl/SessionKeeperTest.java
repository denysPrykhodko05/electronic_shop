package com.epam.prykhodko.captchakeepers.captchakeeperimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SessionKeeperTest {

    private SessionKeeper sessionKeeper = new SessionKeeper();

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getShouldReturnStringThatWasInSession() {
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute("captchaKey")).thenReturn("1234");
        assertEquals(sessionKeeper.get(httpServletRequest), Long.valueOf(1234));
    }
}