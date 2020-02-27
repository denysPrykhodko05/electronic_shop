package com.epam.prykhodko.jsp_tag;

import com.epam.prykhodko.util.TimerThread;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CaptchaTag extends SimpleTagSupport {

    private String hiddenField = "<input type=\"hidden\" value=\"userKey\" name=\"captchaKey\">";
    private String imageTag = "<img id=\"captcha\" src=\"/captcha\">";

    @Override
    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) getJspContext();
        HttpSession session = context.getSession();
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(9));
        }
        String key = UUID.randomUUID().toString();
        startTimer(session, key);
        session.setAttribute("captchaKey", key);
        session.setAttribute("captchaValue", sb.toString());
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.print(imageTag);
        jspWriter.print("<input type=\"text\" name=\"regCaptcha\"><br>");
        hiddenField = hiddenField.replace("userKey", key);
        jspWriter.print(hiddenField);
    }

    private void startTimer(HttpSession session, String key) {
        session.setAttribute("timer", true);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(new TimerThread(session, key), 120, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
