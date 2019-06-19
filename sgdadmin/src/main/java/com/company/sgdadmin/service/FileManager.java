/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import java.io.IOException;

/**
 *
 * @author JEPPLAP
 */
public interface FileManager {

    public void uploading(FileManagerDTO dto) throws IOException;

    public void downloadFile(DocumentosActivosEntity entidad) throws IOException;

    public void downloadFile(DocumentosAcumuladosEntity entidad) throws IOException;
}
