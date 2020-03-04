package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.CO_PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.MAILS;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.POLICY;
import static com.epam.prykhodko.constants.ApplicationConstants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class RegFormBean {

    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String policy;
    private String mails;
    private String captcha;

    public static RegFormBean setRegFormBean(HttpServletRequest httpServletRequest) {
        //TODO remove bean
        RegFormBean regFormBean = new RegFormBean();
        Object avatar = httpServletRequest.getParameter("avatar");
        regFormBean.name = httpServletRequest.getParameter(NAME);
        regFormBean.surname = httpServletRequest.getParameter(SURNAME);
        regFormBean.login = httpServletRequest.getParameter(LOGIN);
        regFormBean.email = httpServletRequest.getParameter(EMAIL);
        regFormBean.password = httpServletRequest.getParameter(PASSWORD);
        regFormBean.confirmPassword = httpServletRequest.getParameter(CO_PASSWORD);
        regFormBean.policy = httpServletRequest.getParameter(POLICY);
        regFormBean.mails = httpServletRequest.getParameter(MAILS);
        regFormBean.captcha = httpServletRequest.getParameter(REG_CAPTCHA);
        return regFormBean;
    }

    //TODO create upload captcha
    public void upload(HttpServletRequest httpServletRequest) throws FileUploadException, FileNotFoundException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        FileOutputStream fos;
        ServletContext servletContext = httpServletRequest.getServletContext();
        File repository = new File("C:\\task1\\git pracrice I\\webShop\\src\\main\\webapp\\images\\avatars");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(httpServletRequest);
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (item.isFormField()) {
                // Достаём поле формы
                System.out.println(item);
            } else {
                // Достаём файл
                fos = new FileOutputStream(repository);
                System.out.println(item);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPolicy() {
        return policy;
    }

    public String getMails() {
        return mails;
    }

    public String getCaptcha() {
        return captcha;
    }
}
