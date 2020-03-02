package com.epam.prykhodko.jsp_tag;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_TIME;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_VALUE;
import static com.epam.prykhodko.constants.Constants.TIMER;
import static com.epam.prykhodko.constants.Constants.USER_KEY;

import com.epam.prykhodko.util.TimerThread;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CaptchaTag extends SimpleTagSupport {

    private String hiddenField = "<input type=\"hidden\" value=\"userKey\" name=\"captchaKey\">";
    private String imageTag = "<img id=\"captcha\" src=\"/captcha\">";
    private String nameField = "<input type=\"text\" name=\"regCaptcha\"><br>";

    @Override
    public void doTag() throws IOException {
        PageContext context = (PageContext) getJspContext();
        HttpSession session = context.getSession();
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(9));
        }
        String key = UUID.randomUUID().toString();
        startTimer(session, key);
        session.setAttribute(CAPTCHA_KEY, key);
        session.setAttribute(CAPTCHA_VALUE, sb.toString());
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.print(imageTag);
        jspWriter.print(nameField);
        hiddenField = hiddenField.replace(USER_KEY, key);
        jspWriter.print(hiddenField);
    }

    private void startTimer(HttpSession session, String key) {
        session.setAttribute(TIMER, true);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new TimerThread(session, key), CAPTCHA_TIME, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
