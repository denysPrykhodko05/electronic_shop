package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.AVATARS_PATH;
import static com.epam.prykhodko.constants.ApplicationConstants.JPG_FORMAT;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/avatarDraw.do")
public class AvatarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(USER_LOGIN);
        String path = AVATARS_PATH + login + JPG_FORMAT;
        File file = new File(path);
        BufferedImage avatar = ImageIO.read(file);
        ImageIO.write(avatar, "jpg", resp.getOutputStream());
    }
}
