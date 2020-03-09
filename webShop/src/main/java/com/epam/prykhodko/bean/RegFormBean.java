package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.AVATAR;
import static com.epam.prykhodko.constants.ApplicationConstants.CO_PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.MAILS;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.POLICY;
import static com.epam.prykhodko.constants.ApplicationConstants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;
import static com.epam.prykhodko.constants.ApplicationConstants.TEMPORARY_STORAGE;

import java.io.File;
import java.util.List;
import java.util.Objects;
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
    private FileItem avatar;
    private String avatarPath;

    private static RegFormBean setRegFormBean(List<FileItem> items) {
        RegFormBean regFormBean = new RegFormBean();
        regFormBean.name = parseItemsList(items, NAME);
        regFormBean.surname = parseItemsList(items, SURNAME);
        regFormBean.login = parseItemsList(items, LOGIN);
        regFormBean.email = parseItemsList(items, EMAIL);
        regFormBean.password = parseItemsList(items, PASSWORD);
        regFormBean.confirmPassword = parseItemsList(items, CO_PASSWORD);
        regFormBean.policy = parseItemsList(items, POLICY);
        regFormBean.mails = parseItemsList(items, MAILS);
        regFormBean.captcha = parseItemsList(items, REG_CAPTCHA);
        return regFormBean;
    }

    private static String parseItemsList(List<FileItem> items, String parameter) {
        for (FileItem e : items) {
            if (Objects.equals(e.getFieldName().toLowerCase(), parameter.toLowerCase())) {
                return e.getString();
            }
        }
        return null;
    }

    private static FileItem getAvatarFromRequest(List<FileItem> items) {
        return items.stream().filter(e -> e.getFieldName().equals(AVATAR)).findFirst().orElse(null);
    }

    public static RegFormBean fromRequestToRegFormBean(HttpServletRequest httpServletRequest) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        File tempDir = (File) httpServletRequest.getServletContext().getAttribute(TEMPORARY_STORAGE);
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 10);
        List<FileItem> items = upload.parseRequest(httpServletRequest);
        RegFormBean regFormBean;
        regFormBean = setRegFormBean(items);
        regFormBean.avatar = getAvatarFromRequest(items);

        return regFormBean;
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

    public FileItem getAvatar() {
        return avatar;
    }

    public void setAvatar(FileItem avatar) {
        this.avatar = avatar;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
