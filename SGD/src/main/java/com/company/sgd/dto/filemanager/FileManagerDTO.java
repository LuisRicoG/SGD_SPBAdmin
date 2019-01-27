/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgd.dto.filemanager;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JEPPLAP
 */
public class FileManagerDTO {

    private String name;
    private MultipartFile file;

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
