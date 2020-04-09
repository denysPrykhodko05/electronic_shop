package com.nure.prykhodko.util;

import static com.nure.prykhodko.constants.ApplicationConstants.AVATARS_PATH;
import static com.nure.prykhodko.constants.ApplicationConstants.JPG_FORMAT;
import static com.nure.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_DRAW_AVATAR;

import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class ImageDraw {

    private static final Logger LOGGER = Logger.getLogger(ImageDraw.class);

    public String saveUploadedFile(FileItem item, String fileName) {
        File uploadedFile;
        String path = AVATARS_PATH + fileName + JPG_FORMAT;
        try {
            uploadedFile = new File(path);
            if (uploadedFile.exists()) {
                uploadedFile.delete();
                uploadedFile = new File(path);
            }
            item.write(uploadedFile);
        } catch (Exception e) {
            LOGGER.error(ERR_CANNOT_DRAW_AVATAR);
        }
        return path;
    }
}
