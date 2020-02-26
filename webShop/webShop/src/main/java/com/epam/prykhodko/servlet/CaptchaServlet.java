package com.epam.prykhodko.servlet;

import com.epam.prykhodko.entity.CaptchaKeeper;
import com.epam.prykhodko.entity.captcha_keeper_impl.SessionKeeper;
import com.epam.prykhodko.util.CaptchaImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        String keeper = servletContext.getInitParameter("captcha");
        Map<String, CaptchaKeeper> captchaKeeper = (Map<String, CaptchaKeeper>) servletContext.getAttribute("keepers");
        HttpSession session = httpServletRequest.getSession();
        CaptchaImage obj = new CaptchaImage();
        BufferedImage ima = obj.getCaptchaImage(session.getAttribute("captchaValue").toString());
        String captchaStr = obj.getCaptchaString();
        captchaKeeper.getOrDefault(keeper, new SessionKeeper())
            .save(httpServletRequest, httpServletResponse, session.getAttribute("captchaKey").toString(), captchaStr);
        ImageIO.write(ima, "jpg", httpServletResponse.getOutputStream());
    }
}
