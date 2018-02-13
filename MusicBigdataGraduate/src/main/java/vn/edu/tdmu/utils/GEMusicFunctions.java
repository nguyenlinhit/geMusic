package vn.edu.tdmu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by NguyenLinh on 2/7/2018.
 *
 */
public class GEMusicFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(GEMusicFunctions.class);

    public static String uploadFile(MultipartFile file, String location) throws IOException {
        LOGGER.info("Upload Image...");

        //checking directory exists
        if (!new File(location).isDirectory()){
            LOGGER.info("Creating directory: {}", location);
            new File(location).mkdir();
        }

        //Split file name ans it's extension
        String filename = file.getOriginalFilename();
        String extension = "";

        int pos = filename.lastIndexOf(".");
        if (pos > 0){
            extension = filename.substring(pos, filename.length());
            filename.substring(0, pos);
        }

        File f = new File(location + filename + extension);
        int i = 1;

        while (f.exists()){
            LOGGER.info("File name is exists: {}", f.getName());
            String newName = filename + "_" + i;
            f = new File(location + newName + extension);
            i++;
        }

        FileCopyUtils.copy(file.getBytes(), f);

        LOGGER.info("Uploaded successfully.");

        return f.getName();
    }
}
