package com.epam.prykhodko.jsptag;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.USER_KEY;
import static java.lang.System.currentTimeMillis;

import java.io.IOException;
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
        String key = String.valueOf(currentTimeMillis());
        session.setAttribute(CAPTCHA_KEY, key);
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.print(imageTag);
        jspWriter.print(nameField);
        hiddenField = hiddenField.replace(USER_KEY, key);
        jspWriter.print(hiddenField);
    }
}
