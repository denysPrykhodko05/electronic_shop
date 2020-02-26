package com.epam.prykhodko.jsp_tag;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.UUID;
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
        session.setAttribute("captchaKey", key);
        session.setAttribute("captchaValue", sb.toString());
        JspWriter jspWriter = getJspContext().getOut();
        jspWriter.print(imageTag);
        jspWriter.print("<input type=\"text\" name=\"regCaptcha\"><br>");
        hiddenField = hiddenField.replace("userKey", key);
        jspWriter.print(hiddenField);
    }
}
