package com.epam.prykhodko.servlet;

import com.epam.prykhodko.entity.CaptchaKeeper;
import com.epam.prykhodko.util.CaptchaImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        String keeper = servletContext.getInitParameter("captcha");
        Map<String, CaptchaKeeper> captchaKeeper = (Map<String, CaptchaKeeper>) servletContext.getAttribute("keepers");
        CaptchaImage obj = new CaptchaImage();
        BufferedImage ima = obj.getCaptchaImage();
        ImageIO.write(ima, "jpg", httpServletResponse.getOutputStream());
        String captchaStr = obj.getCaptchaString();
        captchaKeeper.get(keeper).save(httpServletRequest, UUID.randomUUID().toString(), captchaStr);
    }
}
