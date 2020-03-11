package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.CATEGORY;
import static com.epam.prykhodko.constants.ApplicationConstants.JPG_FORMAT;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PRODUCTS_PATH;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/productImageDraw")
public class ProductImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME);
        String category = req.getParameter(CATEGORY);
        String path = PRODUCTS_PATH + category + "\\" + name + JPG_FORMAT;
        File file = new File(path);
        BufferedImage avatar = ImageIO.read(file);
        ImageIO.write(avatar, "jpg", resp.getOutputStream());
    }
}
