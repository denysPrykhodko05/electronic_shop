package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.Constants.CAPTCHA;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_VALUE;
import static com.epam.prykhodko.constants.Constants.KEEPERS;

import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.SessionKeeper;
import com.epam.prykhodko.util.CaptchaImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/captcha")
@SuppressWarnings("unchecked")
public class CaptchaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        String keeper = servletContext.getInitParameter(CAPTCHA);
        Map<String, CaptchaKeeper> captchaKeepers = (Map<String, CaptchaKeeper>) servletContext.getAttribute(KEEPERS);
        HttpSession session = httpServletRequest.getSession();
        CaptchaImage obj = new CaptchaImage();
        BufferedImage ima = obj.getCaptchaImage(session.getAttribute(CAPTCHA_VALUE).toString());
        String captchaStr = obj.getCaptchaString();
        String ck= (String) session.getAttribute(CAPTCHA_KEY);
        captchaKeepers.getOrDefault(keeper, new SessionKeeper())
            .save(httpServletRequest, httpServletResponse, ck, captchaStr);
        ImageIO.write(ima, "jpg", httpServletResponse.getOutputStream());
    }
}
