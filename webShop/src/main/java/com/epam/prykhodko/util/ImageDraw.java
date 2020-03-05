package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.AVATARS_PATH;
import static com.epam.prykhodko.constants.ApplicationConstants.AVATAR_FORMAT;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_DRAW_AVATAR;

import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class ImageDraw {

    private static final Logger LOGGER = Logger.getLogger(ImageDraw.class);

    public void saveUploadedFile(FileItem item, String fileName) {
        File uploadedFile;

        try {
            String path = AVATARS_PATH + fileName + AVATAR_FORMAT;
            uploadedFile = new File(path);
            if (uploadedFile.exists()) {
                uploadedFile.delete();
                uploadedFile = new File(path);
            }
            item.write(uploadedFile);
        } catch (Exception e) {
            LOGGER.error(ERR_CANNOT_DRAW_AVATAR);
        }
    }
}
