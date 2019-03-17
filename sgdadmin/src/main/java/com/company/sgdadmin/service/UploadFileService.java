package com.company.sgdadmin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    


    String rootPath = System.getProperty("catalina.home");
    private String upload_folder = "rootPath + File.separator + \"almacendocumental\\\\docunicos\\\\DocLegal\\\\escrituras\\\\actaconstitutiva\" + File.separator";

    public void saveFile(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_folder + file.getOriginalFilename());
            Files.write(path,bytes);
        }
    }
}