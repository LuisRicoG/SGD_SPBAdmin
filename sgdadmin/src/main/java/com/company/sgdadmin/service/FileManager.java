
/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author JEPPLAP
 */
public interface FileManager {
public void downloadFile(DocumentosActivosEntity entidad, Boolean isEncripted) throws IOException, FileNotFoundException;
public void downloadFile(DocumentosAcumuladosEntity entidad, Boolean isEncripted) throws IOException, FileNotFoundException;
public void zipFile(String ruta, String nombre) throws IOException;
}
