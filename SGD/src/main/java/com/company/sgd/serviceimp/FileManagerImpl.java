/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgd.serviceimp;

import com.company.sgd.dto.filemanager.FileManagerDTO;
import com.company.sgd.service.FileManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import org.springframework.web.multipart.MultipartFile;

@ManagedBean
public class FileManagerImpl implements FileManager {

    //private static final Logger LOGGER = Logger.getLogger(FileManagerImpl.class);
    public String uploading(FileManagerDTO dto) throws IOException {

        String message = "";

        MultipartFile file = dto.getFile();

        try {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "tmpFiles");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create the file on server
            System.out.println("file name:" + file.getOriginalFilename());
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            message = "You successfully uploaded file=" + file.getOriginalFilename();
        } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
        }
        return message;
    }
}
