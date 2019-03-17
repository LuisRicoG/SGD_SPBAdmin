/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.service;


import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import java.io.IOException;

/**
 *
 * @author JEPPLAP
 */
public interface FileManager {
public String uploading(FileManagerDTO dto) throws IOException;
public void downloadFile(DocumentosActivosEntity entidad) throws IOException;
}
