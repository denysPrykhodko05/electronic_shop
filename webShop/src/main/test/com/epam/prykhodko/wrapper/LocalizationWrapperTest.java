package com.epam.prykhodko.wrapper;

import static com.epam.prykhodko.constants.ApplicationConstants.LOCALE_SAVE_TIME;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import com.epam.prykhodko.localekeepers.localekeeperimpl.SessionLocaleKeeper;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LocalizationWrapperTest {

    @Mock
    ServletContext servletContext;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLocaleShouldReturnSuccessfullyLocaleFromCookies() {
        LocaleKeeper localeKeeper = new SessionLocaleKeeper();
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("localization", "de")});
        when(httpServletRequest.getSession()).thenReturn(session);
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(LOCALE_SAVE_TIME)).thenReturn("300");
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void getLocaleShouldReturnSuccessfullyLocaleFromSession() {
        LocaleKeeper localeKeeper = new SessionLocaleKeeper();
        when(httpServletRequest.getCookies()).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute("localization")).thenReturn("de");
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter(LOCALE_SAVE_TIME)).thenReturn("300");
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void getLocaleShouldReturnLocaleFromBrowser() {
        LocaleKeeper localeKeeper = new SessionLocaleKeeper();
        when(httpServletRequest.getCookies()).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("applicationLocale")).thenReturn("en_de_ru");
        when(session.getAttribute("localization")).thenReturn(null);
        when(httpServletRequest.getLocales()).thenReturn(new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return true;
            }

            @Override
            public Locale nextElement() {
                return new Locale("de");
            }
        });
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void getLocaleShouldReturnLocaleFromBrowserWhenLocaleDoesNotMatchAnyFromBrowser() {
        LocaleKeeper localeKeeper = new SessionLocaleKeeper();
        when(httpServletRequest.getCookies()).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(httpServletRequest.getServletContext()).thenReturn(servletContext);
        when(servletContext.getInitParameter("applicationLocale")).thenReturn("en_de_ru");
        when(session.getAttribute("localization")).thenReturn(null);
        when(httpServletRequest.getLocales()).thenReturn(new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public Locale nextElement() {
                return new Locale("de");
            }
        });
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void getLocales() {
        LocaleKeeper localeKeeper = new SessionLocaleKeeper();
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("localization", "de")});
        when(httpServletRequest.getSession()).thenReturn(session);
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        assertNotNull(localizationWrapper.getLocales());
    }
}