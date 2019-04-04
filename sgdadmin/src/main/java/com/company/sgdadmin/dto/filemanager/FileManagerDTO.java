/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.dto.filemanager;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JEPPLAP
 */
public class FileManagerDTO {

    private String name;
    private MultipartFile file;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
