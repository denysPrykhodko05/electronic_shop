package com.nure.prykhodko.servlet;

import static com.nure.prykhodko.constants.ApplicationConstants.CAPTCHA_KEEPER;
import static com.nure.prykhodko.constants.ApplicationConstants.CAPTCHA_KEY;
import static java.lang.System.currentTimeMillis;

import com.nure.prykhodko.captchakeepers.CaptchaKeeper;
import com.nure.prykhodko.util.CaptchaImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        CaptchaKeeper captchaKeepers = (CaptchaKeeper) servletContext.getAttribute(CAPTCHA_KEEPER);
        HttpSession session = httpServletRequest.getSession();
        String key = String.valueOf(currentTimeMillis());
        session.setAttribute(CAPTCHA_KEY, key);
        CaptchaImage obj = new CaptchaImage();
        BufferedImage ima = obj.getCaptchaImage();
        String captchaStr = obj.getCaptchaString();
        Long captchaKey = Long.valueOf((String) session.getAttribute(CAPTCHA_KEY));
        captchaKeepers.save(httpServletRequest, httpServletResponse, captchaKey, captchaStr);
        ImageIO.write(ima, "jpg", httpServletResponse.getOutputStream());
    }
}
