package com.epam.prykhodko.captcha_keepers.captcha_keeper_impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HiddenTagKeeperTest {

    private HiddenTagKeeper hiddenTagKeeper = new HiddenTagKeeper();

    @Mock
    private HttpServletRequest httpServletRequest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getShouldAddDataToHiddenField() {
        when(httpServletRequest.getParameter("captchaKey")).thenReturn("1234");
        assertEquals(hiddenTagKeeper.get(httpServletRequest), "1234");
    }
}